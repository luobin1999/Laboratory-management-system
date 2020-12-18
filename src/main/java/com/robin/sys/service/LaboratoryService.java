package com.robin.sys.service;

import com.robin.sys.VO.LaboratoryUsageRecordVO;
import com.robin.sys.VO.LaboratoryVO;
import com.robin.sys.VO.PreLaboratoryVO;
import com.robin.sys.dao.LaboratoryDao;
import com.robin.sys.dao.LaboratoryUsageRecordDao;
import com.robin.sys.domain.Laboratory;
import com.robin.sys.domain.LaboratoryUsageRecord;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LaboratoryService {
    @Autowired
    private LaboratoryDao laboratoryDao;
    @Autowired
    private LaboratoryUsageRecordDao laboratoryUsageRecordDao;

    @Transactional
    public List<LaboratoryVO> listLaboratory(){
        List<Laboratory> laboratories = laboratoryDao.listLaboratory();
        List<LaboratoryVO> laboratoryVOS = new ArrayList<>();
        if (laboratories != null) {
            for (int i=0; i<laboratories.size(); i++) {
                Laboratory laboratory = laboratories.get(i);
                LaboratoryVO laboratoryVO = new LaboratoryVO();
                laboratoryVO.setId(laboratory.getId());
                laboratoryVO.setName(laboratory.getName());
                laboratoryVO.setCampus(laboratory.getCampus());
                laboratoryVO.setAdmin(laboratory.getAdmin());
                laboratoryVO.setAdminNumber(laboratory.getAdminNumber());
                laboratoryVO.setContent(laboratory.getContent());
                laboratoryVO.setIsFree(laboratory.getIsFree());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createDate = sdf.format(laboratory.getCreateDate());
                if (laboratory.getUpdateDate() != null){
                    String updateDate = sdf.format(laboratory.getUpdateDate());
                    laboratoryVO.setUpdateDate(updateDate);
                }
                laboratoryVO.setCreateDate(createDate);
                laboratoryVOS.add(laboratoryVO);
            }
        }
        return laboratoryVOS;
    }

    @Transactional
    public void addLaboratory(PreLaboratoryVO preLaboratoryVO){
        if (preLaboratoryVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String name = preLaboratoryVO.getName();
        String campus = preLaboratoryVO.getCampus();
        String admin = preLaboratoryVO.getAdmin();
        String adminNumber = preLaboratoryVO.getAdminNumber();
        String content = preLaboratoryVO.getContent();
        if (name == null || name.length() < 1){
            throw new GlobalException(CodeMsg.LABORATORY_NAME_EMPTY);
        }
        if (campus == null || campus.length() < 1) {
            throw new GlobalException(CodeMsg.LABORATORY_CAMPUS_EMPTY);
        }
        if (admin == null || admin.length() < 1) {
            admin = "无";
        }
        if (adminNumber == null || adminNumber.length() < 1) {
            adminNumber = "无";
        }
        if (content == null || content.length() < 1) {
            content = campus + name;
        }
        Laboratory laboratory = new Laboratory();
        laboratory.setName(name);
        laboratory.setCampus(campus);
        laboratory.setAdmin(admin);
        laboratory.setAdminNumber(adminNumber);
        laboratory.setContent(content);
        laboratory.setCreateDate(new Date());
        laboratory.setIsFree(0);
        laboratoryDao.insertLaboratory(laboratory);
    }

    @Transactional
    public void updateLaboratory(PreLaboratoryVO preLaboratoryVO) {
        if (preLaboratoryVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        int id = preLaboratoryVO.getId();
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        String name = preLaboratoryVO.getName();
        String campus = preLaboratoryVO.getCampus();
        String admin = preLaboratoryVO.getAdmin();
        String adminNumber = preLaboratoryVO.getAdminNumber();
        String content = preLaboratoryVO.getContent();
        int isFree = preLaboratoryVO.getIsFree();
        if (name == null || name.length() < 1){
            throw new GlobalException(CodeMsg.LABORATORY_NAME_EMPTY);
        }
        if (campus == null || campus.length() < 1) {
            throw new GlobalException(CodeMsg.LABORATORY_CAMPUS_EMPTY);
        }
        Laboratory laboratory = new Laboratory();
        laboratory.setId(id);
        laboratory.setName(name);
        laboratory.setCampus(campus);
        laboratory.setAdmin(admin);
        laboratory.setAdminNumber(adminNumber);
        laboratory.setUpdateDate(new Date());
        laboratory.setContent(content);
        laboratory.setIsFree(isFree);
        laboratoryDao.updateLaboratoryById(laboratory);
    }

    @Transactional
    public Laboratory getLaboratoryById(int id) {
        return laboratoryDao.getLaboratoryById(id);
    }

    @Transactional
    public int deleteLaboratory(int id) {
        return laboratoryDao.deleteLaboratoryById(id);
    }

    @Transactional
    public List<LaboratoryUsageRecordVO> listLaboratoryUsageRecord() {
        List<LaboratoryUsageRecord> lurs = laboratoryUsageRecordDao.listLaboratoryUsageRecord();
        List<LaboratoryUsageRecordVO> lurVOS = new ArrayList<>();
        if (lurs != null) {
            for (int i = 0; i < lurs.size(); i++) {
                LaboratoryUsageRecord lur = lurs.get(i);
                LaboratoryUsageRecordVO lurVO = new LaboratoryUsageRecordVO();
                lurVO.setId(lur.getId());
                lurVO.setLaboratoryName(lur.getLaboratoryName());
                lurVO.setUserName(lur.getUserName());
                lurVO.setLaboratoryStatus(lur.getLaboratoryStatus());
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
}
