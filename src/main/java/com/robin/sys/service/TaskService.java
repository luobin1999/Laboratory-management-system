package com.robin.sys.service;

import com.excel.poi.ExcelBoot;
import com.excel.poi.entity.ErrorEntity;
import com.excel.poi.function.ImportFunction;
import com.robin.sys.dao.ExperimentDao;
import com.robin.sys.dao.ExperimentRecordDao;
import com.robin.sys.dao.GroupInfoDao;
import com.robin.sys.domain.Experiment;
import com.robin.sys.domain.GroupInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class TaskService {
    private static Logger logger = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private ExperimentRecordDao experimentRecordDao;
    @Autowired
    private GroupInfoDao groupInfoDao;
    @Autowired
    private ExperimentDao experimentDao;

    @Transactional
    public void updateGroupInfoForClassAndExperiment(MultipartFile groupFile, String experimentName, String clazzName)  {
        //删除原分组信息
        groupInfoDao.deleteGroupInfoByClazzNameAndExperimentName(clazzName, experimentName);
        //添加分组信息
        String path = fileUpload(groupFile);
        File file = new File(path);
        try {
            ExcelBoot.ImportBuilder(new FileInputStream(file), GroupInfo.class)
                    .importExcel(new ImportFunction<GroupInfo>() {
                        /**
                         * @param i  sheetIndex 当前执行的Sheet的索引, 从1开始
                         * @param i1  rowIndex 当前执行的行数, 从1开始
                         * @param o userEntity Excel行数据的实体
                         */
                        @Override
                        public void onProcess(int i, int i1, GroupInfo o) {
                            //将读取到Excel中每一行的userEntity数据进行自定义处理
                            //如果该行数据发生问题,将不会走本方法,而会走onError方法
                            groupInfoDao.insertGroupInfo(o);
                        }

                        /**
                         * @param errorEntity errorEntity 错误信息实体
                         */
                        @Override
                        public void onError(ErrorEntity errorEntity) {
                            //将每条数据非空和正则校验后的错误信息errorEntity进行自定义处理
                            logger.error(errorEntity.getErrorMessage());
                        }
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //更新record表中的分组标识为1
        Experiment experiment = experimentDao.getExperimentByName(experimentName);
        experimentRecordDao.updateIsGroupStatus(experiment.getId(), clazzName);
        //删除本地分组文件
        file.delete();
    }

    public String fileUpload(MultipartFile groupFile) {
        String path = "group.xlsx";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            file = new File(path);
        }
        OutputStream out = null;
        InputStream in = null;
        try {
            out = new FileOutputStream(file);
            in = groupFile.getInputStream();
            byte[] buf = new byte[1024];
            while (in.read(buf) > 0) {
                out.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }
}
