package com.robin.sys.service;

import com.robin.sys.VO.experiment.ExperimentFinishRecordViewVO;
import com.robin.sys.VO.experiment.ExperimentVO;
import com.robin.sys.VO.experiment.PreExperimentVO;
import com.robin.sys.dao.ExperimentDao;
import com.robin.sys.dao.ExperimentFinishRecordDao;
import com.robin.sys.dao.ExperimentRecordDao;
import com.robin.sys.domain.*;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExperimentService {
    @Autowired
    private ExperimentDao experimentDao;
    @Autowired
    private MinioService minioService;
    @Autowired
    private ExperimentFinishRecordDao finishRecordDao;
    @Autowired
    private ExperimentRecordDao experimentRecordDao;

    @Transactional
    public List<ExperimentVO> listExperiment() {
        List<Experiment> experiments = experimentDao.listExperiment();
        List<ExperimentVO> experimentVOS = new ArrayList<>();
        if (experiments == null) {
            return experimentVOS;
        }
        for (int i = 0; i < experiments.size(); i++) {
            Experiment experiment = experiments.get(i);
            ExperimentVO experimentVO = new ExperimentVO();
            experimentVO.setId(experiment.getId());
            experimentVO.setName(experiment.getName());
            experimentVO.setNumber(experiment.getNumber());
            experimentVO.setContent(experiment.getContent());
            experimentVO.setTask(experiment.getTask());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(experiment.getCreateDate());
            experimentVO.setCreateDate(createDate);
            experimentVOS.add(experimentVO);
        }
        return experimentVOS;
    }

    @Transactional
    public List<ExperimentFinishRecordViewVO> listExperimentForStudent(User user) {
        List<ExperimentFinishRecordView> experiments = finishRecordDao.listExperimentFinishRecordViewForStudent(user.getId(), user.getClazz());
        List<ExperimentFinishRecordViewVO> experimentVOS = new ArrayList<>();
        if (experiments == null) {
            return experimentVOS;
        }
        for (int i = 0; i < experiments.size(); i++) {
            ExperimentFinishRecordView experiment = experiments.get(i);
            ExperimentFinishRecordViewVO experimentVO = new ExperimentFinishRecordViewVO();
            experimentVO.setId(experiment.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(experiment.getCreateDate());
            experimentVO.setCreateDate(createDate);
            experimentVO.setExperimentName(experiment.getExperimentName());
            experimentVO.setExperimentNumber(experiment.getExperimentNumber());
            experimentVO.setExperimentTask(experiment.getExperimentTask());
            experimentVO.setExprimentId(experiment.getExprimentId());
            experimentVO.setPreview(experiment.getPreview());
            experimentVO.setReport(experiment.getReport());
            experimentVO.setTeacherName(experiment.getTeacherName());
            experimentVO.setTotalScore(experiment.getTotalScore());
            experimentVOS.add(experimentVO);
        }
        return experimentVOS;
    }

    @Transactional
    public ExperimentFinishRecordViewVO getExperimentFinishRecordByExperiment(int experimentId, User user) {
        List<ExperimentFinishRecordView> experiments = finishRecordDao.listExperimentFinishRecordViewForStudent(user.getId(), user.getClazz());
        ExperimentFinishRecordViewVO res = new ExperimentFinishRecordViewVO();
        if (experiments == null) {
            return res;
        }
        for (int i = 0; i < experiments.size(); i++) {
            ExperimentFinishRecordView experiment = experiments.get(i);
            if (experiment.getExprimentId() == experimentId) {
                res.setId(experiment.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createDate = sdf.format(experiment.getCreateDate());
                res.setCreateDate(createDate);
                Date previewDate = experiment.getPreviewDate();
                if (previewDate != null) {
                    res.setPreviewDate(sdf.format(previewDate));
                }
                Date reportDate = experiment.getReportDate();
                if (reportDate != null) {
                    res.setReportDate(sdf.format(reportDate));
                }
                res.setStudentName(experiment.getStudentName());
                res.setStudentNumber(experiment.getStudentNumber());
                res.setReportScore(experiment.getReportScore());
                res.setPreviewScore(res.getPreviewScore());
                res.setPreview(experiment.getPreview());
                res.setReport(experiment.getReport());
                res.setTeacherName(experiment.getTeacherName());
                res.setTotalScore(experiment.getTotalScore());
                res.setExperimentName(experiment.getExperimentName());
                res.setExperimentNumber(experiment.getExperimentNumber());
                res.setExperimentTask(experiment.getExperimentTask());
                res.setExprimentId(experiment.getExprimentId());
                return res;
            }
        }
        return res;
    }

    @Transactional
    public void addExperiment(String task, PreExperimentVO preExperimentVO) {
        if (preExperimentVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String number = preExperimentVO.getNumber();
        String name = preExperimentVO.getName();
        String content = preExperimentVO.getContent();
        if (number == null || number.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_NUMBER_EMPTY);
        }
        if (name == null || name.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_NAME_EMPTY);
        }
        if (content == null || content.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_CONTENT_EMPTY);
        }
        Experiment experiment = new Experiment();
        experiment.setName(name);
        experiment.setNumber(number);
        experiment.setContent(content);
        experiment.setTask(task);
        experiment.setCreateDate(new Date());
        experimentDao.insertExperiment(experiment);
    }

    @Transactional
    public void updateExperiment(MultipartFile file, PreExperimentVO preExperimentVO) {
        if (preExperimentVO.getTask() == null || preExperimentVO.getTask().length() < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        minioService.delete(preExperimentVO.getTask());
        String task = minioService.upload(file);
        if (preExperimentVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String number = preExperimentVO.getNumber();
        String name = preExperimentVO.getName();
        String content = preExperimentVO.getContent();
        int id = preExperimentVO.getId();
        if (id <= 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        if (number == null || number.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_NUMBER_EMPTY);
        }
        if (name == null || name.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_NAME_EMPTY);
        }
        if (content == null || content.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_CONTENT_EMPTY);
        }
        Experiment experiment = new Experiment();
        experiment.setId(id);
        experiment.setName(name);
        experiment.setNumber(number);
        experiment.setContent(content);
        experiment.setTask(task);
        experimentDao.updateExperiment(experiment);
    }

    @Transactional
    public void  updateExperimentWithoutFile(PreExperimentVO preExperimentVO) {
        if (preExperimentVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String number = preExperimentVO.getNumber();
        String name = preExperimentVO.getName();
        String content = preExperimentVO.getContent();
        int id = preExperimentVO.getId();
        if (id <= 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        if (number == null || number.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_NUMBER_EMPTY);
        }
        if (name == null || name.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_NAME_EMPTY);
        }
        if (content == null || content.length() < 1) {
            throw new GlobalException(CodeMsg.EXPERIMENT_CONTENT_EMPTY);
        }
        Experiment experiment = new Experiment();
        experiment.setId(id);
        experiment.setName(name);
        experiment.setNumber(number);
        experiment.setContent(content);
        experimentDao.updateExperimentNotTask(experiment);
    }

    @Transactional
    public void submitExperimentPreview(String preview, int experimentId, User user) {
        if (preview == null || preview.length() < 1) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        if (experimentId < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        String clazzName = user.getClazz();
        int userId = user.getId();
        ExperimentFinishRecord finishRecord = finishRecordDao.getFinishRecordByExperimentIdAndStudentId(experimentId, userId);
        if (finishRecord != null) {
            finishRecord.setPreview(preview);
            finishRecord.setPreviewDate(new Date());
            finishRecordDao.updatePreviewById(finishRecord);
            return;
        }
        ExperimentRecord record = experimentRecordDao.getExperimentRecord(experimentId, clazzName);
        finishRecord = new ExperimentFinishRecord();
        finishRecord.setExperimentId(experimentId);
        finishRecord.setStudentId(userId);
        finishRecord.setExperimentRecordId(record.getId());
        finishRecord.setPreview(preview);
        finishRecord.setPreviewDate(new Date());
        finishRecordDao.insertExperimentFinishRecordPreview(finishRecord);
    }

    @Transactional
    public void submitExperimentReport(String report, int experimentId, User user) {
        if (report == null || report.length() < 1) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        if (experimentId < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        String clazzName = user.getClazz();
        int userId = user.getId();
        ExperimentFinishRecord finishRecord = finishRecordDao.getFinishRecordByExperimentIdAndStudentId(experimentId, userId);
        if (finishRecord != null) {
            finishRecord.setReport(report);
            finishRecord.setReportDate(new Date());
            finishRecordDao.updateReportById(finishRecord);
            return;
        }
        ExperimentRecord record = experimentRecordDao.getExperimentRecord(experimentId, clazzName);
        finishRecord = new ExperimentFinishRecord();
        finishRecord.setExperimentId(experimentId);
        finishRecord.setStudentId(userId);
        finishRecord.setExperimentRecordId(record.getId());
        finishRecord.setReport(report);
        finishRecord.setReportDate(new Date());
        finishRecordDao.insertExperimentFinishRecordReport(finishRecord);
    }

    @Transactional
    public void deleteExperiment(int id) {
        Experiment experiment = experimentDao.getExperimentById(id);
        minioService.delete(experiment.getTask());
        experimentDao.deleteExperimentById(id);
    }

    public Experiment getExperimentById(int id) {
        return experimentDao.getExperimentById(id);
    }

    public boolean finishRecordIsExist(int experimentId, int studentId) {
        ExperimentFinishRecord record = finishRecordDao.getFinishRecordByExperimentIdAndStudentId(experimentId, studentId);
        return record != null;
    }
}
