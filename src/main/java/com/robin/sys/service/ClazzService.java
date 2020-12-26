package com.robin.sys.service;

import com.robin.sys.VO.clazz.ClazzVO;
import com.robin.sys.VO.clazz.PreClazzVO;
import com.robin.sys.dao.ClazzDao;
import com.robin.sys.domain.Clazz;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClazzService {
    @Autowired
    private ClazzDao clazzDao;

    @Transactional
    public List<ClazzVO> listClazz(){
        List<Clazz> list = clazzDao.listClazz();
        List<ClazzVO> clazzVoList = new ArrayList<>();
        if (list == null) {
            return clazzVoList;
        }
        for (int i = 0; i < list.size(); i++) {
            Clazz clazz = list.get(i);
            ClazzVO clazzVO = new ClazzVO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(clazz.getCreateDate());
            if (clazz.getUpdateDate() != null){
                String updateDate = sdf.format(clazz.getUpdateDate());
                clazzVO.setUpdateDate(updateDate);
            }
            clazzVO.setId(clazz.getId());
            clazzVO.setClazz(clazz.getClazz());
            clazzVO.setGrade(clazz.getGrade());
            clazzVO.setCount(clazz.getCount());
            clazzVO.setCreateDate(createDate);
            clazzVoList.add(clazzVO);
        }
        return clazzVoList;
    }

    public Clazz getClazzById(int id) {
        return clazzDao.getClassById(id);
    }

    @Transactional
    public void addClazz(PreClazzVO preClazzVO) {
        if (preClazzVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String grade = preClazzVO.getGrade();
        String preClazz = preClazzVO.getClazz();
        if (StringUtils.isEmpty(grade)) {
            throw new GlobalException(CodeMsg.GRADE_EMPTY);
        }
        if (StringUtils.isEmpty(preClazz)) {
            throw new GlobalException(CodeMsg.CLAZZ_CLAZZ_EMPTY);
        }
        Clazz clazz = clazzDao.getClassByClazz(preClazz);
        if (clazz != null) {
            throw new GlobalException(CodeMsg.REPEAT_ADD);
        }
        clazz = new Clazz();
        clazz.setGrade(grade);
        clazz.setClazz(preClazz);
        clazz.setCreateDate(new Date());
        clazzDao.insertClass(clazz);
    }

    @Transactional
    public void updateClazz(PreClazzVO preClazzVO) {
        if (preClazzVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        int id = preClazzVO.getId();
        String grade = preClazzVO.getGrade();
        String preClazz = preClazzVO.getClazz();
        if (id <= 0) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        if (StringUtils.isEmpty(preClazz)) {
            throw new GlobalException(CodeMsg.CLAZZ_CLAZZ_EMPTY);
        }
        if (StringUtils.isEmpty(grade)) {
            throw new GlobalException(CodeMsg.GRADE_EMPTY);
        }
        Clazz clazz = new Clazz();
        clazz.setClazz(preClazz);
        clazz.setGrade(grade);
        clazz.setUpdateDate(new Date());
        clazz.setId(id);
        clazzDao.updateClassById(clazz);
    }

    @Transactional
    public void deleteClazzById(int id) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        clazzDao.deleteClass(id);
    }
}
