package com.robin.sys.service;

import com.robin.sys.VO.device.DeviceUsageRecordViewVO;
import com.robin.sys.VO.laboratory.LaboratoryUsageRecordVO;
import com.robin.sys.dao.DeviceUsageRecordDao;
import com.robin.sys.dao.LaboratoryUsageRecordDao;
import com.robin.sys.domain.*;
import com.robin.sys.domain.view.DeviceUsageRecordView;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private DeviceUsageRecordDao deviceUsageRecordDao;
    @Autowired
    private LaboratoryUsageRecordDao laboratoryUsageRecordDao;

    @Transactional
    public List<LaboratoryUsageRecordVO> listLaboratoryWaitReview() {
        List<LaboratoryUsageRecord> lurs = laboratoryUsageRecordDao.listLaboratoryWaitReview();
        List<LaboratoryUsageRecordVO> lurVOS = new ArrayList<>();
        if (lurs != null) {
            for (int i = 0; i < lurs.size(); i++) {
                LaboratoryUsageRecord lur = lurs.get(i);
                LaboratoryUsageRecordVO lurVO = new LaboratoryUsageRecordVO();
                lurVO.setId(lur.getId());
                lurVO.setLaboratoryName(lur.getLaboratoryName());
                lurVO.setCampus(lur.getCampus());
                lurVO.setUserName(lur.getUserName());
                lurVO.setPower(lur.getPower());
                lurVO.setUserNumber(lur.getUserNumber());
                lurVO.setTarget(lur.getTarget());
                lurVO.setStatus(lur.getStatus());
                lurVO.setReviewerName(lur.getReviewerName());
                lurVO.setReviewerNumber(lur.getReviewerNumber());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createDate = sdf.format(lur.getCreateDate());
                String startDate = sdf.format(lur.getStartDate());
                String endDate = sdf.format(lur.getEndDate());
                lurVO.setCreateDate(createDate);
                lurVO.setStartDate(startDate);
                lurVO.setEndDate(endDate);
                lurVOS.add(lurVO);
            }
        }
        return lurVOS;
    }

    @Transactional
    public List<LaboratoryUsageRecordVO> listLaboratoryAlreadyHandle() {
        List<LaboratoryUsageRecord> lurs = laboratoryUsageRecordDao.listLaboratoryAlreadyHandle();
        List<LaboratoryUsageRecordVO> lurVOS = new ArrayList<>();
        if (lurs != null) {
            for (int i = 0; i < lurs.size(); i++) {
                LaboratoryUsageRecord lur = lurs.get(i);
                LaboratoryUsageRecordVO lurVO = new LaboratoryUsageRecordVO();
                lurVO.setId(lur.getId());
                lurVO.setLaboratoryName(lur.getLaboratoryName());
                lurVO.setCampus(lur.getCampus());
                lurVO.setUserName(lur.getUserName());
                lurVO.setPower(lur.getPower());
                lurVO.setUserNumber(lur.getUserNumber());
                lurVO.setTarget(lur.getTarget());
                lurVO.setStatus(lur.getStatus());
                lurVO.setReviewerName(lur.getReviewerName());
                lurVO.setReviewerNumber(lur.getReviewerNumber());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createDate = sdf.format(lur.getCreateDate());
                String startDate = sdf.format(lur.getStartDate());
                String endDate = sdf.format(lur.getEndDate());
                lurVO.setCreateDate(createDate);
                lurVO.setStartDate(startDate);
                lurVO.setEndDate(endDate);
                lurVOS.add(lurVO);
            }
        }
        return lurVOS;
    }

    @Transactional
    public void deviceReturnFinish(int id) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        DeviceUsageRecord deviceUsageRecord = new DeviceUsageRecord();
        deviceUsageRecord.setId(id);
        deviceUsageRecord.setStatus(4);
        deviceUsageRecordDao.updateStatusById(deviceUsageRecord);
    }

    @Transactional
    public void reviewDevicePass(int id, User user) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        DeviceUsageRecord deviceUsageRecord = deviceUsageRecordDao.getDeviceUsageRecordById(id);
        if (deviceUsageRecord == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        Date startDate = deviceUsageRecord.getStartDate();
        Date endDate = deviceUsageRecord.getEndDate();
        int deviceId = deviceUsageRecord.getDeviceId();
        deviceUsageRecord.setStatus(2);
        deviceUsageRecord.setReviewerId(user.getId());
        List<DeviceUsageRecord> records = deviceUsageRecordDao.listDeviceUsageRecordByDeviceId(deviceId);
        if (records == null) {
            deviceUsageRecordDao.updateStatusById(deviceUsageRecord);
        }
        boolean flag = true;
        for (int i = 0; i < records.size(); i++) {
            DeviceUsageRecord record = records.get(i);
            if (startDate.getTime() <= record.getStartDate().getTime() && endDate.getTime() >= record.getStartDate().getTime()) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            throw new GlobalException(CodeMsg.BORROW_TIME_CLASH);
        }
        deviceUsageRecordDao.updateStatusById(deviceUsageRecord);
    }

    @Transactional
    public void reviewLaboratoryPass(int id, User user) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        LaboratoryRecord laboratoryRecord = laboratoryUsageRecordDao.getLaboratoryUsageRecordById(id);
        if (laboratoryRecord == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        int laboratoryId = laboratoryRecord.getLaboratoryId();
        Date startDate = laboratoryRecord.getStartDate();
        Date endDate = laboratoryRecord.getEndDate();
        laboratoryRecord.setStatus(2);
        laboratoryRecord.setReviewerId(user.getId());
        List<LaboratoryRecord> records = laboratoryUsageRecordDao.listLaboratoryUsageRecordByLaboratoryId(laboratoryId);
        if (records == null) {
            laboratoryUsageRecordDao.updateStatusById(laboratoryRecord);
        }
        boolean flag = true;
        for (int i = 0; i < records.size(); i++) {
            LaboratoryRecord record = records.get(i);
            if (startDate.getTime() <= record.getStartDate().getTime() && endDate.getTime() >= record.getStartDate().getTime()) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            throw new GlobalException(CodeMsg.BORROW_TIME_CLASH);
        }
        laboratoryUsageRecordDao.updateStatusById(laboratoryRecord);
    }

    @Transactional
    public void reviewDeviceNoPass(int id, User user) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        DeviceUsageRecord deviceUsageRecord = new DeviceUsageRecord();
        deviceUsageRecord.setId(id);
        deviceUsageRecord.setStatus(3);
        deviceUsageRecord.setReviewerId(user.getId());
        deviceUsageRecordDao.updateStatusById(deviceUsageRecord);
    }

    @Transactional
    public void reviewLaboratoryNoPass(int id, User user) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        LaboratoryRecord laboratoryRecord = new LaboratoryRecord();
        laboratoryRecord.setId(id);
        laboratoryRecord.setStatus(3);
        laboratoryRecord.setReviewerId(user.getId());
        laboratoryUsageRecordDao.updateStatusById(laboratoryRecord);
    }

    @Transactional
    public List<DeviceUsageRecordViewVO> listDeviceAlreadyHandle(){
        List<DeviceUsageRecordView> durvs = deviceUsageRecordDao.listDeviceAlreadyHandle();
        List<DeviceUsageRecordViewVO> durvvos = new ArrayList<>();
        if (durvs == null) {
            return durvvos;
        }
        for (int i = 0; i < durvs.size(); i++) {
            DeviceUsageRecordView dur = durvs.get(i);
            DeviceUsageRecordViewVO durvo = new DeviceUsageRecordViewVO();
            durvo.setId(dur.getId());
            durvo.setDeviceName(dur.getDeviceName());
            durvo.setDeviceModel(dur.getDeviceModel());
            durvo.setDeviceNumber(dur.getDeviceNumber());
            durvo.setDeviceStatus(dur.getDeviceStatus());
            durvo.setPower(dur.getPower());
            durvo.setReviewerName(dur.getReviewerName());
            durvo.setReviewerNumber(dur.getReviewerNumber());
            durvo.setStatus(dur.getStatus());
            durvo.setTarget(dur.getTarget());
            durvo.setUserName(dur.getUserName());
            durvo.setUserNumber(dur.getUserNumber());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = dur.getCreateDate();
            durvo.setCreateDate(sdf.format(createDate));
            String startDate = sdf.format(dur.getStartDate());
            String endDate = sdf.format(dur.getEndDate());
            durvo.setStartDate(startDate);
            durvo.setEndDate(endDate);
            durvvos.add(durvo);
        }
        return durvvos;
    }

    @Transactional
    public List<DeviceUsageRecordViewVO> listDeviceWaitReturn(){
        List<DeviceUsageRecordView> durvs = deviceUsageRecordDao.listDeviceWaitReturn();
        List<DeviceUsageRecordViewVO> durvvos = new ArrayList<>();
        if (durvs == null) {
            return durvvos;
        }
        for (int i = 0; i < durvs.size(); i++) {
            DeviceUsageRecordView dur = durvs.get(i);
            DeviceUsageRecordViewVO durvo = new DeviceUsageRecordViewVO();
            durvo.setId(dur.getId());
            durvo.setDeviceName(dur.getDeviceName());
            durvo.setDeviceModel(dur.getDeviceModel());
            durvo.setDeviceNumber(dur.getDeviceNumber());
            durvo.setDeviceStatus(dur.getDeviceStatus());
            durvo.setPower(dur.getPower());
            durvo.setReviewerName(dur.getReviewerName());
            durvo.setReviewerNumber(dur.getReviewerNumber());
            durvo.setStatus(dur.getStatus());
            durvo.setTarget(dur.getTarget());
            durvo.setUserName(dur.getUserName());
            durvo.setUserNumber(dur.getUserNumber());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = dur.getCreateDate();
            durvo.setCreateDate(sdf.format(createDate));
            String startDate = sdf.format(dur.getStartDate());
            String endDate = sdf.format(dur.getEndDate());
            durvo.setStartDate(startDate);
            durvo.setEndDate(endDate);
            durvvos.add(durvo);
        }
        return durvvos;
    }

    @Transactional
    public List<DeviceUsageRecordViewVO> listDeviceWaitReview(){
        List<DeviceUsageRecordView> durvs = deviceUsageRecordDao.listDeviceWaitReview();
        List<DeviceUsageRecordViewVO> durvvos = new ArrayList<>();
        if (durvs == null) {
            return durvvos;
        }
        for (int i = 0; i < durvs.size(); i++) {
            DeviceUsageRecordView dur = durvs.get(i);
            DeviceUsageRecordViewVO durvo = new DeviceUsageRecordViewVO();
            durvo.setId(dur.getId());
            durvo.setDeviceName(dur.getDeviceName());
            durvo.setDeviceModel(dur.getDeviceModel());
            durvo.setDeviceNumber(dur.getDeviceNumber());
            durvo.setDeviceStatus(dur.getDeviceStatus());
            durvo.setPower(dur.getPower());
            durvo.setReviewerName(dur.getReviewerName());
            durvo.setReviewerNumber(dur.getReviewerNumber());
            durvo.setStatus(dur.getStatus());
            durvo.setTarget(dur.getTarget());
            durvo.setUserName(dur.getUserName());
            durvo.setUserNumber(dur.getUserNumber());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createDate = dur.getCreateDate();
            durvo.setCreateDate(sdf.format(createDate));
            String startDate = sdf.format(dur.getStartDate());
            String endDate = sdf.format(dur.getEndDate());
            durvo.setStartDate(startDate);
            durvo.setEndDate(endDate);
            durvvos.add(durvo);
        }
        return durvvos;
    }
}
