package com.robin.sys.controller;

import com.robin.sys.VO.DeviceUsageRecordViewVO;
import com.robin.sys.VO.LaboratoryUsageRecordVO;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.ReviewService;
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
public class ReviewController {
    private static Logger logger = LoggerFactory.getLogger(ReviewController.class);
    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/list/device/wait/review")
    public String listDeviceReview(Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        List<DeviceUsageRecordViewVO> durvos = reviewService.listDeviceWaitReview();
        model.addAttribute("user", user);
        model.addAttribute("durvos", durvos);
        return "review_device_list";
    }

    @RequestMapping("/list/laboratory/wait/review")
    public String listLaboratoryReview(Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        List<LaboratoryUsageRecordVO> lurvos = reviewService.listLaboratoryWaitReview();
        model.addAttribute("user", user);
        model.addAttribute("lurvos", lurvos);
        return "review_laboratory_list";
    }

    @RequestMapping("/list/device/already/handle")
    public String listDeviceHandle(Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        List<DeviceUsageRecordViewVO> durvos = reviewService.listDeviceAlreadyHandle();
        model.addAttribute("durvos",durvos);
        model.addAttribute("user", user);
        model.addAttribute("type", 5);
        return "device_usage_record";
    }

    @RequestMapping("/list/laboratory/already/handle")
    public String listLaboratoryHandle(Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        List<LaboratoryUsageRecordVO> lurvos = reviewService.listLaboratoryAlreadyHandle();
        logger.info(lurvos.toString());
        model.addAttribute("lurvos",lurvos);
        model.addAttribute("user", user);
        model.addAttribute("type", 4);
        return "laboratory_usage_record";
    }

    @RequestMapping("/list/device/wait/return")
    public String listDeviceWaitReturn(Model model, User user){
        powerCheack(user);
        List<DeviceUsageRecordViewVO> durvos = reviewService.listDeviceWaitReturn();
        model.addAttribute("user", user);
        model.addAttribute("durvos",durvos);
        return "device_wait_return";
    }

    @RequestMapping("borrow/device/return/finish")
    @ResponseBody
    public Result returnDeviceFinish(@Param("id") int id, User user) {
        powerCheack(user);
        reviewService.deviceReturnFinish(id);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 结束（完成）了预约流程："+id);
        return Result.success("流程已完成");
    }

    @RequestMapping("/review/device/pass")
    @ResponseBody
    public Result reviewDevicePass(@Param("id") int id, User user) {
        powerCheack(user);
        reviewService.reviewDevicePass(id, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 通过了预约申请："+id);
        return Result.success("流程已完成");
    }

    @RequestMapping("/review/laboratory/pass")
    @ResponseBody
    public Result reviewLaboratoryPass(@Param("id") int id, User user) {
        powerCheack(user);
        reviewService.reviewLaboratoryPass(id, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 通过了预约申请："+id);
        return Result.success("流程已完成");
    }

    @RequestMapping("/review/device/no_pass")
    @ResponseBody
    public Result reviewDeviceNoPass(@Param("id") int id, User user) {
        powerCheack(user);
        reviewService.reviewDeviceNoPass(id, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 拒绝了预约申请："+id);
        return Result.success("流程已完成");
    }

    @RequestMapping("/review/laboratory/no_pass")
    @ResponseBody
    public Result reviewLaboratoryNoPass(@Param("id") int id, User user) {
        powerCheack(user);
        reviewService.reviewLaboratoryNoPass(id, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 拒绝了预约申请："+id);
        return Result.success("流程已完成");
    }

    private void powerCheack(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
    }
}
