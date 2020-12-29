package com.robin.sys.service;

import com.robin.sys.VO.experiment.ExperimentClazzViewVO;
import com.robin.sys.VO.experiment.ExperimentFinishRecordViewVO;
import com.robin.sys.VO.experiment.ExperimentVO;
import com.robin.sys.VO.experiment.PreExperimentVO;
import com.robin.sys.constant.Constant;
import com.robin.sys.dao.ExperimentDao;
import com.robin.sys.dao.ExperimentFinishRecordDao;
import com.robin.sys.dao.ExperimentRecordDao;
import com.robin.sys.domain.*;
import com.robin.sys.domain.view.ExperimentClazzView;
import com.robin.sys.domain.view.ExperimentFinishRecordView;
import com.robin.sys.domain.view.ExperimentRecordView;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

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
            experimentVO.setExperimentId(experiment.getExperimentId());
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
            if (experiment.getExperimentId() == experimentId) {
                res.setId(experiment.getId());
                res.setClazzName(experiment.getClazzName());
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
                res.setExperimentId(experiment.getExperimentId());
                return res;
            }
        }
        return res;
    }

    @Transactional
    public ExperimentFinishRecordViewVO getExperimentFinishRecordByExperiment(int experimentId, int studentId, String clazzName) {
        List<ExperimentFinishRecordView> views = finishRecordDao.listExperimentTaskByClazzName(experimentId, clazzName);
        ExperimentFinishRecordViewVO res = new ExperimentFinishRecordViewVO();
        if (views == null) {
            return res;
        }
        for (int i = 0; i < views.size(); i++) {
            ExperimentFinishRecordView view = views.get(i);
            if (view.getStudentId() == studentId) {
                res.setPreviewScore(view.getPreviewScore());
                res.setTotalScore(view.getTotalScore());
                res.setTeacherName(view.getTeacherName());
                res.setExperimentId(view.getExperimentId());
                res.setStudentNumber(view.getStudentNumber());
                res.setStudentName(view.getStudentName());
                res.setReportScore(view.getReportScore());
                res.setReport(view.getReport());
                res.setId(view.getId());
                res.setClazzName(view.getClazzName());
                res.setPreview(view.getPreview());
                res.setExperimentNumber(view.getExperimentNumber());
                res.setExperimentTask(view.getExperimentTask());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date createDate = view.getCreateDate();
                res.setCreateDate(sdf.format(createDate));
                Date previewDate = view.getPreviewDate();
                if (previewDate != null) {
                    res.setPreviewDate(sdf.format(previewDate));
                }
                res.setStudentId(view.getStudentId());
                Date reportDate = view.getReportDate();
                res.setExperimentName(view.getExperimentName());
                if (reportDate != null) {
                    res.setReportDate(sdf.format(reportDate));
                }
                return res;
            }
        }
        return res;
    }

    public List<ExperimentFinishRecordViewVO> listExperimentTaskByClazzName(int experimentId, String clazzName) {
        if (experimentId <= 0 || clazzName == null || clazzName.length() < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        List<ExperimentFinishRecordView> views = finishRecordDao.listExperimentTaskByClazzName(experimentId, clazzName);
        List<ExperimentFinishRecordViewVO> viewVOS = new ArrayList<>();
        if (views == null) {
            return viewVOS;
        }
        for (int i = 0; i < views.size(); i++) {
            ExperimentFinishRecordView view = views.get(i);
            ExperimentFinishRecordViewVO viewVO = new ExperimentFinishRecordViewVO();
            viewVO.setStudentNumber(view.getStudentNumber());
            viewVO.setId(view.getId());
            viewVO.setClazzName(view.getClazzName());
            viewVO.setStudentName(view.getStudentName());
            viewVO.setReportScore(view.getReportScore());
            viewVO.setReport(view.getReport());
            viewVO.setPreview(view.getPreview());
            viewVO.setPreviewScore(view.getPreviewScore());
            viewVO.setTotalScore(view.getTotalScore());
            viewVO.setTeacherName(view.getTeacherName());
            viewVO.setExperimentId(view.getExperimentId());
            viewVO.setExperimentTask(view.getExperimentTask());
            viewVO.setExperimentNumber(view.getExperimentNumber());
            Date createDate = view.getCreateDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            viewVO.setCreateDate(sdf.format(createDate));
            Date previewDate = view.getPreviewDate();
            if (previewDate != null) {
                viewVO.setPreviewDate(sdf.format(previewDate));
            }
            viewVO.setExperimentName(view.getExperimentName());
            viewVO.setStudentId(view.getStudentId());
            Date reportDate = view.getReportDate();
            if (reportDate != null) {
                viewVO.setReportDate(sdf.format(reportDate));
            }
            viewVOS.add(viewVO);
        }
        return viewVOS;
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

    public void  publishExperimentForClazz(int experimentId, String clazzName, User user) {
        if (experimentId <= 0 || clazzName == null || clazzName.length() < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        ExperimentRecord experimentRecord = new ExperimentRecord();
        experimentRecord.setClazzName(clazzName);
        experimentRecord.setExperimentId(experimentId);
        experimentRecord.setTeacherId(user.getId());
        experimentRecord.setCreateDate(new Date());
        experimentRecordDao.insertExperimentRecord(experimentRecord);
    }

    @Transactional
    public void deleteExperiment(int id) {
        Experiment experiment = experimentDao.getExperimentById(id);
        minioService.delete(experiment.getTask());
        experimentDao.deleteExperimentById(id);
    }

    @Transactional
    public List<ExperimentRecordView> listExperimentRecordView() {
        List<ExperimentRecordView> experimentRecords = experimentRecordDao.listExperimentRecordView();
        for (int i = 0; i < experimentRecords.size(); i++) {
            ExperimentRecordView view = experimentRecords.get(i);
            view.setViewName(view.getExperimentNumber() + view.getExperimentName());
        }
        return experimentRecords;
    }

    @Transactional
    public List<ExperimentClazzViewVO> listClazzForExperiment(int experimentId) {
        if (experimentId <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        List<ExperimentClazzView> views = experimentRecordDao.listClazzForExperiment(experimentId);
        List<ExperimentClazzViewVO> viewVOS = new ArrayList<>();
        if (views == null) {
            return viewVOS;
        }
        for (int i = 0; i < views.size(); i++) {
            ExperimentClazzView view = views.get(i);
            ExperimentClazzViewVO viewVO = new ExperimentClazzViewVO();
            viewVO.setId(view.getId());
            viewVO.setClazzName(view.getClazzName());
            viewVO.setCount(view.getCount());
            viewVO.setExperimentId(view.getExperimentId());
            viewVO.setExperimentName(view.getExperimentName());
            viewVO.setExperimentNumber(view.getExperimentNumber());
            viewVO.setTeacherName(view.getTeacherName());
            Date createDate = view.getCreateDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            viewVO.setCreateDate(sdf.format(createDate));
            viewVOS.add(viewVO);
        }
        return viewVOS;
    }

    @Transactional
    public void scoringExperimentPrevieTask(int studentId,int experimentId,String clazzName,int previewScore) {
        if (previewScore < 0 || previewScore >100) {
            throw new GlobalException(CodeMsg.SCORE_ERROR);
        }
        if (studentId <= 0 || experimentId <= 0 || clazzName == null || clazzName.length() < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        ExperimentFinishRecord finishRecord = finishRecordDao.getFinishRecordByExperimentIdAndStudentId(experimentId,studentId);
        if (finishRecord != null) {
            int reportScore = finishRecord.getReportScore();
            int totalScore = (int)Math.ceil(previewScore * Constant.PREVIE_SCORE_PROPORTION + reportScore * Constant.REPORT_SCORE_PROPORTION);
            finishRecord.setPreviewScore(previewScore);
            finishRecord.setTotalScore(totalScore);
            finishRecordDao.updatePreviewScoreById(finishRecord);
            return;
        }
        finishRecord = new ExperimentFinishRecord();
        ExperimentRecord record = experimentRecordDao.getExperimentRecord(experimentId,clazzName);
        finishRecord.setStudentId(studentId);
        finishRecord.setExperimentId(experimentId);
        finishRecord.setExperimentRecordId(record.getId());
        finishRecord.setPreviewScore(previewScore);
        int totalScore = (int)Math.ceil(previewScore * Constant.PREVIE_SCORE_PROPORTION);
        finishRecord.setTotalScore(totalScore);
        finishRecordDao.insertExperimentFinishRecordPreviewScore(finishRecord);
    }

    @Transactional
    public void scoringExperimentReportTask(int studentId,int experimentId,String clazzName,int reportScore) {
        if (studentId <= 0 || experimentId <= 0 || clazzName == null || clazzName.length() < 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        if (reportScore < 0 || reportScore >100) {
            throw new GlobalException(CodeMsg.SCORE_ERROR);
        }
        ExperimentFinishRecord finishRecord = finishRecordDao.getFinishRecordByExperimentIdAndStudentId(experimentId,studentId);
        if (finishRecord != null) {
            int previewScore = finishRecord.getPreviewScore();
            int totalScore = (int)Math.ceil(previewScore * Constant.PREVIE_SCORE_PROPORTION + reportScore * Constant.REPORT_SCORE_PROPORTION);
            finishRecord.setReportScore(reportScore);
            finishRecord.setTotalScore(totalScore);
            finishRecordDao.updateReportScoreById(finishRecord);
            return;
        }
        finishRecord = new ExperimentFinishRecord();
        ExperimentRecord record = experimentRecordDao.getExperimentRecord(experimentId,clazzName);
        finishRecord.setExperimentRecordId(record.getId());
        finishRecord.setReportScore(reportScore);
        finishRecord.setStudentId(studentId);
        finishRecord.setExperimentId(experimentId);
        int totalScore = (int)Math.ceil(reportScore * Constant.PREVIE_SCORE_PROPORTION);
        finishRecord.setTotalScore(totalScore);
        finishRecordDao.insertExperimentFinishRecordReportScore(finishRecord);
    }

    public Experiment getExperimentById(int id) {
        return experimentDao.getExperimentById(id);
    }

    public boolean finishRecordIsExist(int experimentId, int studentId) {
        ExperimentFinishRecord record = finishRecordDao.getFinishRecordByExperimentIdAndStudentId(experimentId, studentId);
        return record != null;
    }
}
