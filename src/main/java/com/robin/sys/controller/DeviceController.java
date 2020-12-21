package com.robin.sys.controller;

import com.robin.sys.VO.*;
import com.robin.sys.domain.Device;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.DeviceService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DeviceController {
    private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/list/device")
    public String listDevice(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        List<DeviceVO> devices = deviceService.listDevice();
        model.addAttribute("devices",devices);
        return "device_list";
    }

    @RequestMapping("/add/device/add")
    @ResponseBody
    public Result addDevice(PreDeviceVO preDeviceVO, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        deviceService.addDevice(preDeviceVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 添加设备信息："+preDeviceVO);
        return Result.success("添加成功");
    }

    @RequestMapping("/update/device")
    public String deviceUpdate(@Param("id") int id, Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        Device device = deviceService.getDevice(id);
        model.addAttribute("user", user);
        model.addAttribute("device", device);
        return "device_update";
    }

    @RequestMapping("/update/device/update")
    @ResponseBody
    public Result updateDevice(PreDeviceVO preDeviceVO, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        deviceService.updateDevice(preDeviceVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 修改设备信息："+preDeviceVO);
        return Result.success("修改成功");
    }

    @RequestMapping("/list/device/overview")
    public String listDeviceOverview(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        List<DeviceOverviewVO> deviceOverviews = deviceService.listDeviceOverview();
        model.addAttribute("deviceOverviews", deviceOverviews);
        return "device_overview_list";
    }


    @RequestMapping("/add/device")
    public String deviceAdd(Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() !=3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        return "device_add";
    }

    @RequestMapping("/list/device/usage_record")
    public String listDeviceUsageRecode(Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        model.addAttribute("user", user);
        List<DeviceUsageRecordViewVO> durvos = deviceService.listDeviceUsageRecord();
        model.addAttribute("durvos", durvos);
        model.addAttribute("type", 1);
        return "device_usage_record";
    }

    @RequestMapping("/delete/device")
    @ResponseBody
    public Result deleteDevice(@Param("id") int id, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        deviceService.deleteDevice(id);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 删除设备信息："+id);
        return Result.success("删除成功");
    }

    @RequestMapping("/check/device")
    public String deviceCheck(@Param("id") int id, Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        Device device = deviceService.getDevice(id);
        model.addAttribute("user", user);
        model.addAttribute("device", device);
        return "device_check";
    }

    @RequestMapping("/check/device/check")
    @ResponseBody
    public Result checkDevice(PreDeviceVO preDeviceVO, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        deviceService.checkDevice(preDeviceVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 检查设备信息为："+preDeviceVO);
        return Result.success("检查完成");
    }

    @RequestMapping("/borrow/device")
    public String laboratoryBorrow(@Param("id") int id, Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        Device device = deviceService.getDevice(id);
        model.addAttribute("user", user);
        model.addAttribute("device", device);
        return "device_borrow";
    }

    @RequestMapping("/borrow/device/borrow")
    @ResponseBody
    public Result borrowDevice(DeviceUsageRecordVO deviceUsageRecordVO, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        deviceService.borrowDevice(deviceUsageRecordVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 提交了一个设备预约申请："+deviceUsageRecordVO);
        return Result.success("提交预约成功");
    }

    @RequestMapping("/list/device/review")
    public String listDeviceReview(Model model, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        List<DeviceUsageRecordViewVO> durvos = deviceService.listDeviceUsageRecordReviewing(user.getNumber());
        model.addAttribute("user", user);
        model.addAttribute("durvos", durvos);
        model.addAttribute("type", 2);
        return "device_usage_record";
    }

    @RequestMapping("/list/device/finish")
    public String listDeviceFinish(Model model, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        List<DeviceUsageRecordViewVO> durvos = deviceService.listDeviceUsageRecordFinish(user.getNumber());
        model.addAttribute("user", user);
        model.addAttribute("durvos", durvos);
        model.addAttribute("type", 3);
        return "device_usage_record";
    }

    @RequestMapping("/list/device/record")
    public String listDeviceRecord(Model model, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        List<DeviceUsageRecordViewVO> durvos = deviceService.listDeviceUsageRecord(user.getNumber());
        model.addAttribute("user", user);
        model.addAttribute("durvos", durvos);
        model.addAttribute("type", 4);
        return "device_usage_record";
    }
}
