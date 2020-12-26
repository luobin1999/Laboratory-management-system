package com.robin.sys.service;

import com.robin.sys.constant.Constant;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.util.UUIDUtil;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Service
public class MinioService {
    private static final Logger logger = LoggerFactory.getLogger(MinioService.class);
    @Autowired
    private MinioClient minioClient;

    @Transactional
    public String upload(MultipartFile file) {
        if (file == null) {
            throw new GlobalException(CodeMsg.FILE_EMPTY);
        }
        logger.info("getOriginalFilename:{}", file.getOriginalFilename());
        logger.info("getSize{}", file.getSize());
        String name = UUIDUtil.uuid() + "-" + file.getOriginalFilename();
        //检查存储桶是否存在
        try {
            boolean isExist = minioClient.bucketExists(Constant.FILE_BUCKET);
            if (isExist) {
                logger.info("system bucket already exist");
            } else  {
                //创建一个存储桶，用于存储文件
                minioClient.makeBucket(Constant.FILE_BUCKET);
            }
            //使用putObject上传一个文件到存储桶中
            minioClient.putObject(Constant.FILE_BUCKET, name, file.getInputStream(), file.getContentType());
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.FILE_UPLOAD_ERROR);
        }
        return name;
    }

    public void download(String number, String name, String fileName, HttpServletResponse response) {
        InputStream in = null;
        try {
            String suffix = Constant.DEFAULT_SUFFIX;
            int spot = fileName.lastIndexOf(".");
            if (spot > 0) {
                suffix = fileName.substring(spot);
            }
            ObjectStat stat = minioClient.statObject(Constant.FILE_BUCKET, fileName);
            in = minioClient.getObject(Constant.FILE_BUCKET, fileName);
            String downloadName = number + name;
            response.setHeader("Content-Disposition",  "attachment;filename=" + new String(downloadName.getBytes("utf-8"),"ISO8859-1") + suffix);
            response.setContentType(stat.contentType());
            IOUtils.copy(in, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.FILE_DOWNLOAD_ERROR);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new GlobalException(CodeMsg.STREAM_CLOSE_ERROR);
                }
            }
        }
    }

    public void delete(String fileName) {
        try {
            minioClient.removeObject(Constant.FILE_BUCKET, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
