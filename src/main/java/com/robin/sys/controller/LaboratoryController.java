package com.robin.sys.controller;

import com.robin.sys.VO.laboratory.LaboratoryUsageRecordVO;
import com.robin.sys.VO.laboratory.LaboratoryVO;
import com.robin.sys.VO.laboratory.PreLaboratoryRecordVO;
import com.robin.sys.VO.laboratory.PreLaboratoryVO;
import com.robin.sys.domain.Laboratory;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.LaboratoryService;
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
public class LaboratoryController {
    private static Logger logger = LoggerFactory.getLogger(LaboratoryController.class);
    @Autowired
    private LaboratoryService laboratoryService;

    @RequestMapping("/list/laboratory")
    public String listLaboratory(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        List<LaboratoryVO> laboratories = laboratoryService.listLaboratory();
        model.addAttribute("laboratories", laboratories);
        return "laboratory_list";
    }

    @RequestMapping("/add/laboratory")
    public String laboratoryAdd(Model model, User user) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        return "laboratory_add";
    }

    @RequestMapping("/add/laboratory/add")
    @ResponseBody
    public Result addLaboratory(PreLaboratoryVO preLaboratoryVO, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        laboratoryService.addLaboratory(preLaboratoryVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 添加实验室信息："+preLaboratoryVO);
        return Result.success("添加成功");
    }

    @RequestMapping("/update/laboratory")
    public String updateLaboratory(@Param("id") Integer id, Model model, User user){
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        Laboratory laboratory = laboratoryService.getLaboratoryById(id);
        model.addAttribute("laboratory", laboratory);
        return "laboratory_update";
    }

    @RequestMapping("/update/laboratory/update")
    @ResponseBody
    public Result updateLaboratoryUpdate(PreLaboratoryVO preLaboratoryVO, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        laboratoryService.updateLaboratory(preLaboratoryVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 修改实验室信息"+preLaboratoryVO.getId()+"为："+preLaboratoryVO);
        return Result.success("修改成功");
    }

    @RequestMapping("/delete/laboratory")
    @ResponseBody
    public Result deleteLaboratory(@Param("id") Integer id, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        laboratoryService.deleteLaboratory(id);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 成功删除实验室信息"+id);
        return Result.success("删除成功");
    }

    @RequestMapping("/borrow/laboratory")
    public String borrowLaboratory(@Param("id") Integer id, Model model, User user){
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        Laboratory laboratory = laboratoryService.getLaboratoryById(id);
        model.addAttribute("user", user);
        model.addAttribute("laboratory", laboratory);
        return "laboratory_borrow";
    }

    @RequestMapping("/borrow/laboratory/borrow")
    @ResponseBody
    public Result laboratoryBorrow(PreLaboratoryRecordVO laboratoryRecord, User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        laboratoryService.borrowLaboratory(laboratoryRecord);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 提交了一个实验室预约申请："+laboratoryRecord);
        return Result.success("预约已提交");
    }

    @RequestMapping("/list/laboratory/usage_record")
    public String listLaboratoryUsageRecord(Model model, User user) {
        if (user == null) {
            return "login";
        }
        List<LaboratoryUsageRecordVO> lurVOS = laboratoryService.listLaboratoryUsageRecord();
        model.addAttribute("user", user);
        model.addAttribute("lurvos", lurVOS);
        model.addAttribute("type",1);
        return "laboratory_usage_record";
    }

    @RequestMapping("/list/laboratory/record")
    public String listLaboratoryRecord(Model model, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        List<LaboratoryUsageRecordVO> lurVOS = laboratoryService.listLaboratoryUsageRecord(user.getNumber());
        model.addAttribute("user", user);
        model.addAttribute("lurvos", lurVOS);
        model.addAttribute("type", 2);
        return "laboratory_usage_record";
    }

    @RequestMapping("/list/laboratory/review")
    public String listLaboratoryUsageRecordReview(Model model, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 1 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
        List<LaboratoryUsageRecordVO> lurVOS = laboratoryService.listLaboratoryUsageRecordReviewing(user.getNumber());
        model.addAttribute("user", user);
        model.addAttribute("lurvos", lurVOS);
        model.addAttribute("type", 3);
        return "laboratory_usage_record";
    }
}
