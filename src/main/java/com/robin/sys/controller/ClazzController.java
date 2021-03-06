package com.robin.sys.controller;

import com.robin.sys.VO.clazz.ClazzVO;
import com.robin.sys.VO.clazz.PreClazzVO;
import com.robin.sys.domain.Clazz;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.ClazzService;
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
public class ClazzController {
    private static Logger logger = LoggerFactory.getLogger(ClazzController.class);
    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/list/clazz")
    public String clazzList(Model model, User user){
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        List<ClazzVO> clazzes = clazzService.listClazz();
        model.addAttribute("clazzes", clazzes);
        return "class_list";
    }

    @RequestMapping("/add/clazz")
    public String clazzAdd(Model model, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 3 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        return "class_add";
    }

    @RequestMapping("/update/clazz")
    public String clazzUpdate(Model model, @Param("id") Integer id, User user) {
        if (user == null) {
            return "login";
        }
        if (user.getPower() != 3 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        model.addAttribute("user", user);
        if (id <= 0) {
            return "class_add";
        }
        Clazz clazz = clazzService.getClazzById(id);
        model.addAttribute("clazz", clazz);
        model.addAttribute("id", id);
        return "class_update";
    }

    @RequestMapping("/update/clazz/update")
    @ResponseBody
    public Result updateClazz(PreClazzVO preClazzVO, User user) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 修改班级ID为"+preClazzVO.getId()+"的班级信息为"+preClazzVO);
        clazzService.updateClazz(preClazzVO);
        return Result.success("修改成功！");
    }

    @RequestMapping("/add/clazz/add")
    @ResponseBody
    public Result addClazz(PreClazzVO preClazzVO, User user){
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        clazzService.addClazz(preClazzVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 添加班级信息："+preClazzVO);
        return Result.success("添加成功！");
    }

    @RequestMapping("/delete/clazz")
    @ResponseBody
    public Result deleteClazz(@Param("id") Integer id, User user) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 3 && user.getPower() != 2) {
            throw new GlobalException(CodeMsg.POWER_LOW);
        }
        clazzService.deleteClazzById(id);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 成功删除班级信息"+id);
        return Result.success("删除成功！");
    }
}
