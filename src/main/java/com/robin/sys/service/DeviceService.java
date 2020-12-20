package com.robin.sys.service;

import com.robin.sys.VO.*;
import com.robin.sys.dao.DeviceDao;
import com.robin.sys.dao.DeviceUsageRecordDao;
import com.robin.sys.domain.Device;
import com.robin.sys.domain.DeviceOverview;
import com.robin.sys.domain.DeviceUsageRecord;
import com.robin.sys.domain.DeviceUsageRecordView;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private DeviceUsageRecordDao deviceUsageRecordDao;

    public Device getDevice(int id) {
        return deviceDao.getDeviceById(id);
    }

    @Transactional
    public List<DeviceVO> listDevice(){
        List<Device> devices = deviceDao.listDevice();
        List<DeviceVO> deviceVOS = new ArrayList<>();
        if (devices == null) {
            return deviceVOS;
        }
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            DeviceVO deviceVO = new DeviceVO();
            deviceVO.setId(device.getId());
            deviceVO.setName(device.getName());
            deviceVO.setNumber(device.getNumber());
            deviceVO.setAdmin(device.getAdmin());
            deviceVO.setModel(device.getModel());
            deviceVO.setDeviceStatus(device.getDeviceStatus());
            deviceVO.setUsageStatus(device.getUsageStatus());
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String createDate = sdf1.format(device.getCreateDate());
            deviceVO.setCreateDate(createDate);
            String buyDate = sdf2.format(device.getBuyDate());
            deviceVO.setBuyDate(buyDate);
            if (device.getUpdateDate() != null) {
                String updateDate = sdf1.format(device.getUpdateDate());
                deviceVO.setUpdateDate(updateDate);
            }
            if (device.getCheckDate() != null) {
                String checkDate = sdf2.format(device.getCheckDate());
                deviceVO.setCheckDate(checkDate);
            }
            deviceVOS.add(deviceVO);
        }
        return deviceVOS;
    }

    @Transactional
    public List<DeviceOverviewVO> listDeviceOverview(){
        List<DeviceOverview> dos = deviceDao.listDeviceOverview();
        List<DeviceOverviewVO> dovs = new ArrayList<>();
        if (dos == null) {
            return dovs;
        }
        for (int i = 0; i < dos.size(); i++) {
            DeviceOverview dow = dos.get(i);
            DeviceOverviewVO dov = new DeviceOverviewVO();
            dov.setName(dow.getName());
            dov.setId(dow.getId());
            dov.setModel(dow.getModel());
            dov.setAdmin(dow.getAdmin());
            dov.setCount(dow.getCount());
            dov.setAlreadyRepairCount(dow.getAlreadyRepairCount());
            dov.setFreeCount(dow.getFreeCount());
            dov.setNormalCount(dow.getNormalCount());
            dov.setScrapCount(dow.getScrapCount());
            dov.setUsageCount(dow.getUsageCount());
            dov.setWaitRepairCount(dow.getWaitRepairCount());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (dow.getCheckDate() != null){
                String checkDate = sdf.format(dow.getCheckDate());
                dov.setCheckDate(checkDate);
            }
            dovs.add(dov);
        }
        return dovs;
    }

    @Transactional
    public void addDevice(PreDeviceVO preDeviceVO) {
        if (preDeviceVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String name = preDeviceVO.getName();
        String model = preDeviceVO.getModel();
        String number = preDeviceVO.getNumber();
        String buy_date = preDeviceVO.getBuyDate();
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date buyDate = null;
        try {
            buyDate = formatter.parse(buy_date);
        } catch (ParseException e) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String admin = preDeviceVO.getAdmin();
        int deviceStatus = preDeviceVO.getDeviceStatus();
        int usageStatus = preDeviceVO.getUsageStatus();
        if (name == null || name.length() < 1) {
            throw new GlobalException(CodeMsg.DEVICE_NAME_EMPTY);
        }
        if (model == null || model.length() < 1) {
            throw new GlobalException(CodeMsg.DEVICE_MODEL_EMPTY);
        }
        if (number == null || number.length() < 1) {
            throw new GlobalException(CodeMsg.DEVICE_NUMBER_EMPTY);
        }
        if (buyDate == null) {
            throw new GlobalException(CodeMsg.DEVICE_BUY_DATE_EMPTY);
        }
        Device device = new Device();
        device.setName(name);
        device.setAdmin(admin);
        device.setNumber(number);
        device.setModel(model);
        device.setBuyDate(buyDate);
        device.setDeviceStatus(deviceStatus);
        device.setUsageStatus(usageStatus);
        device.setCreateDate(new Date());
        deviceDao.insertDevice(device);
    }

    @Transactional
    public void updateDevice(PreDeviceVO preDeviceVO) {
        if (preDeviceVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        int id = preDeviceVO.getId();
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        String name = preDeviceVO.getName();
        String model = preDeviceVO.getModel();
        String number = preDeviceVO.getNumber();
        String admin = preDeviceVO.getAdmin();
        int deviceStatus = preDeviceVO.getDeviceStatus();
        int usageStatus = preDeviceVO.getUsageStatus();
        if (name == null || name.length() < 1) {
            throw new GlobalException(CodeMsg.DEVICE_NAME_EMPTY);
        }
        if (model == null || model.length() < 1) {
            throw new GlobalException(CodeMsg.DEVICE_MODEL_EMPTY);
        }
        if (number == null || number.length() < 1) {
            throw new GlobalException(CodeMsg.DEVICE_NUMBER_EMPTY);
        }
        Device device = new Device();
        device.setId(id);
        device.setName(name);
        device.setAdmin(admin);
        device.setNumber(number);
        device.setModel(model);
        device.setDeviceStatus(deviceStatus);
        device.setUsageStatus(usageStatus);
        device.setUpdateDate(new Date());
        deviceDao.updateDeviceById(device);
    }

    @Transactional
    public int deleteDevice(int id) {
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        return deviceDao.deleteDeviceById(id);
    }

    @Transactional
    public int checkDevice(PreDeviceVO preDeviceVO) {
        if (preDeviceVO == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        int id = preDeviceVO.getId();
        if (id <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        int deviceStatus = preDeviceVO.getDeviceStatus();
        Device device = new Device();
        device.setId(id);
        device.setDeviceStatus(deviceStatus);
        device.setCheckDate(new Date());
        return deviceDao.updateDeviceCheckInfoById(device);
    }

    @Transactional
    public List<DeviceUsageRecordViewVO> listDeviceUsageRecord() {
        List<DeviceUsageRecordView> durvs = deviceUsageRecordDao.listDeviceUsageRecord();
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
            durvo.setUsageStatus(dur.getUsageStatus());
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
