package com.robin.sys.controller;

import com.robin.sys.VO.clazz.ClazzVO;
import com.robin.sys.VO.experiment.ExperimentFinishRecordViewVO;
import com.robin.sys.VO.experiment.ExperimentVO;
import com.robin.sys.VO.experiment.PreExperimentVO;
import com.robin.sys.domain.Clazz;
import com.robin.sys.domain.Experiment;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.ClazzService;
import com.robin.sys.service.ExperimentService;
import com.robin.sys.service.MinioService;
import com.robin.sys.util.PowerUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ExperimentController {
    private static final Logger logger = LoggerFactory.getLogger(ExperimentController.class);
    @Autowired
    private ExperimentService experimentService;
    @Autowired
    private MinioService minioService;
    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/list/experiment")
    public String listTest(Model model, User user){
        if (user == null) {
            return "login";
        }
        List<ExperimentVO> experiments = experimentService.listExperiment();
        model.addAttribute("user", user);
        model.addAttribute("experiments", experiments);
        return "experiment_list";
    }

    @RequestMapping("/list/experiment_record")
    public String listExperimentForStudent(Model model, User user) {
        if (user == null) {
            return "login";
        }
        List<ExperimentFinishRecordViewVO> experiments = experimentService.listExperimentForStudent(user);
        model.addAttribute("user", user);
        model.addAttribute("experiments", experiments);
        return "experiment_record_list";
    }

    @RequestMapping("/experiment/task/download")
    public void download(String number, String name, String fileName, HttpServletResponse response) {
        if (fileName == null) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        minioService.download(number, name, fileName, response);
    }

    @RequestMapping("/add/experiment")
    public String testAdd(Model model, User user){
        if (user == null) {
            return "login";
        }
        powerCheck(user);
        model.addAttribute("user", user);
        return "experiment_add";
    }

    @RequestMapping("/add/experiment/add")
    @ResponseBody
    public Result addExperiment(@Param("file") MultipartFile file, PreExperimentVO preExperimentVO, User user) {
        logger.info(preExperimentVO.toString());
        powerCheck(user);
        String task = minioService.upload(file);
        experimentService.addExperiment(task, preExperimentVO);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 添加实验信息："+preExperimentVO);
        return Result.success("添加成功");
    }

    @RequestMapping("/delete/experiment")
    @ResponseBody
    public Result deleteExperiment(int id, User user) {
        powerCheck(user);
        if (id <= 1) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        experimentService.deleteExperiment(id);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 删除了实验信息："+id);
        return Result.success("删除成功");
    }

    @RequestMapping("/update/experiment")
    public String experimentUpdate(@Param("id") int id, User user,Model model) {
        if (user == null) {
            return "login";
        }
        powerCheck(user);
        Experiment experiment = experimentService.getExperimentById(id);
        model.addAttribute("user",user);
        model.addAttribute("experiment", experiment);
        return "experiment_update";
    }

    @RequestMapping("/update/experiment/update")
    @ResponseBody
    public Result updateExperiment(@Param("file") MultipartFile file, PreExperimentVO preExperimentVO, User user) {
        logger.info(preExperimentVO.toString());
        powerCheck(user);
        if (file == null) {
            logger.info("修改实验信息，但不修改试验任务书");
            experimentService.updateExperimentWithoutFile(preExperimentVO);
        } else {
            logger.info("修改实验信息，并且修改试验任务书");
            experimentService.updateExperiment(file, preExperimentVO);
        }
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 修改了实验信息："+preExperimentVO);
        return Result.success("修改成功");
    }

    @RequestMapping("/detail/experiment")
    @ResponseBody
    public Result detailExperiment(@RequestParam("experimentId") int experimentId, User user) {
        PowerUtil.PowerCheck1(user);
        if (experimentService.finishRecordIsExist(experimentId, user.getId())) {
            return Result.success("信息已找到");
        }
        return Result.error(CodeMsg.PLEASE_SUBMIT_TASK_FIRST);
    }

    @RequestMapping("/experiment/detail")
    public String detailExperimentInfo(@RequestParam("experimentId") int experimentId, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck1(user);
        model.addAttribute("user", user);
        ExperimentFinishRecordViewVO recordViewVO = experimentService.getExperimentFinishRecordByExperiment(experimentId, user);
        model.addAttribute("finishRecords", recordViewVO);
        return "experiment_detail";
    }

    @RequestMapping("/publish/experiment")
    public String experimentPublish(@RequestParam("experimentId") int experimentId, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck2(user);
        model.addAttribute(user);
        List<ClazzVO> clazzes = clazzService.listClazz();
        model.addAttribute("clazzes", clazzes);
        Experiment experiment = experimentService.getExperimentById(experimentId);
        model.addAttribute("experiment", experiment);
        return "experiment_publish";
    }

    @RequestMapping("/publish/experiment/publish")
    @ResponseBody
    public Result publishExperiment(@RequestParam("experimentId") int experimentId,@RequestParam("clazzName") String clazzName, User user) {
        PowerUtil.PowerCheck2(user);
        experimentService.publishExperimentForClazz(experimentId, clazzName, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 给班级："+ clazzName + "发布实验信息："+ experimentId);
        return Result.success("发布成功");
    }

    private void powerCheck(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        if (user.getPower() != 2 && user.getPower() != 3) {
            throw new GlobalException(CodeMsg.POWER_ERROR);
        }
    }
}
