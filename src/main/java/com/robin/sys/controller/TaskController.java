package com.robin.sys.controller;

import com.robin.sys.VO.experiment.ExperimentClazzViewVO;
import com.robin.sys.VO.experiment.ExperimentFinishRecordViewVO;
import com.robin.sys.domain.Clazz;
import com.robin.sys.domain.Experiment;
import com.robin.sys.domain.User;
import com.robin.sys.exception.GlobalException;
import com.robin.sys.result.CodeMsg;
import com.robin.sys.result.Result;
import com.robin.sys.service.*;
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
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private MinioService minioService;
    @Autowired
    private ExperimentService experimentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping("/review/experiment/detail")
    public String detailExperiment(@RequestParam("experimentId") Integer experimentId,@RequestParam("clazzName") String clazzName,@RequestParam("studentId") Integer studentId, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck2(user);
        ExperimentFinishRecordViewVO view = experimentService.getExperimentFinishRecordByExperiment(experimentId, studentId, clazzName);
        model.addAttribute("user", user);
        model.addAttribute("finishRecords", view);
        return "task_review_experiment_detail";
    }

    @RequestMapping("/review/experiment/preview/task/submit")
    @ResponseBody
    public Result reviewExperimentPreviewTask(@RequestParam("studentId") Integer studentId,@RequestParam("experimentId") Integer experimentId,
                                       @RequestParam("clazzName") String clazzName,@RequestParam("previewScore") Integer previewScore,
                                       Model model, User user){
        PowerUtil.PowerCheck2(user);
        model.addAttribute("user", user);
        experimentService.scoringExperimentPrevieTask(studentId,experimentId,clazzName,previewScore);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 给学生："+studentId+"实验："+experimentId+"的预习报告打分为："+previewScore);
        return Result.success("打分成功");
    }

    @RequestMapping("/review/experiment/report/task/submit")
    @ResponseBody
    public Result reviewExperimentReportTask(@RequestParam("studentId") Integer studentId,@RequestParam("experimentId") Integer experimentId,
                                       @RequestParam("clazzName") String clazzName,@RequestParam("previewScore") Integer previewScore,
                                       Model model, User user){
        PowerUtil.PowerCheck2(user);
        model.addAttribute("user", user);
        experimentService.scoringExperimentReportTask(studentId,experimentId,clazzName,previewScore);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 给学生："+studentId+"实验："+experimentId+"的实验报告打分为："+previewScore);
        return Result.success("打分成功");
    }

    @RequestMapping("/review/experiment/preview/task")
    public String experimentPreviewTaskReview(Model model, @RequestParam("studentId") Integer studentId,@RequestParam("experimentId") Integer experimentId, User user) {
        if (user == null) {
            return "login";
        }
        if (studentId <= 0 || experimentId <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        PowerUtil.PowerCheck2(user);
        User student = userService.getUserById(studentId);
        model.addAttribute("user", user);
        model.addAttribute("student", student);
        Experiment experiment = experimentService.getExperimentById(experimentId);
        model.addAttribute("experiment", experiment);
        return "task_review_preview";
    }

    @RequestMapping("/review/experiment/report/task")
    public String experimentReportTaskReview(Model model, @RequestParam("studentId") Integer studentId,@RequestParam("experimentId") Integer experimentId, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck2(user);
        if (studentId <= 0 || experimentId <= 0) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        model.addAttribute("user", user);
        User student = userService.getUserById(studentId);
        model.addAttribute("student", student);
        Experiment experiment = experimentService.getExperimentById(experimentId);
        model.addAttribute("experiment", experiment);
        return "task_review_report";
    }

    @RequestMapping("/list/experiment/task/by_clazz")
    public String listExperimentTaskByClazzName(@RequestParam("experimentId") Integer experimentId,@RequestParam("clazzName") String clazzName, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck2(user);
        model.addAttribute("user", user);
        List<ExperimentFinishRecordViewVO> views = experimentService.listExperimentTaskByClazzName(experimentId, clazzName);
        model.addAttribute("views", views);
        return "task_clazz_experiment_list";
    }

    @RequestMapping("/list/experiment/clazz")
    public String listClazzByExperiment(Integer experimentId, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck2(user);
        List<ExperimentClazzViewVO> views = experimentService.listClazzForExperiment(experimentId);
        model.addAttribute("user", user);
        model.addAttribute("views", views);
        return "task_class_list";
    }

    @RequestMapping("/upload/experiment/group/info")
    public String uploadExperimentGroupInfo(String experimentName, String clazzName, User user, Model model){
        PowerUtil.PowerCheck2(user);
        model.addAttribute("experimentName", experimentName);
        model.addAttribute("clazzName", clazzName);
        model.addAttribute("user", user);
        return "group_file_upload";
    }

    @RequestMapping("/update/group/info")
    @ResponseBody
    public Result updateGroupInfoForClassAndExperiment(@Param("gourpFile") MultipartFile groupFile, String experimentName, String clazzName, User user) {
        PowerUtil.PowerCheck2(user);
        taskService.updateGroupInfoForClassAndExperiment(groupFile, experimentName, clazzName);
        Experiment experiment = experimentService.getExperimentByName(experimentName);
        return Result.success(experiment.getId());
    }

    @RequestMapping("/submit/experiment/preview")
    public String previewSubmit(@Param("experimentId") Integer experimentId, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck1(user);
        model.addAttribute("user", user);
        Experiment experiment = experimentService.getExperimentById(experimentId);
        model.addAttribute("experiment", experiment);
        return "experiment_preview_submit";
    }

    @RequestMapping("/experiment/preview/download")
    public void downloadPreview(String fileName, HttpServletResponse response) {
        if (fileName == null) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        minioService.download(fileName, response);
    }

    @RequestMapping("/experiment/report/download")
    public void downloadReport(String fileName, HttpServletResponse response) {
        if (fileName == null) {
            throw new GlobalException(CodeMsg.CLIENT_ERROR);
        }
        minioService.download(fileName, response);
    }

    @RequestMapping("/submit/experiment/preview/submit")
    @ResponseBody
    public Result submitPreview(@Param("previewFile") MultipartFile previewFile,@Param("experimentId") Integer experimentId, User user) {
        PowerUtil.PowerCheck1(user);
        String name = minioService.upload(user.getNumber(), user.getName(), previewFile);
        experimentService.submitExperimentPreview(name, experimentId, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 提交了实验："+experimentId+"的预习报告："+name);
        return Result.success("提交成功");
    }

    @RequestMapping("/submit/experiment/report")
    public String reportSubmit(@Param("experimentId") Integer experimentId, Model model, User user) {
        if (user == null) {
            return "login";
        }
        PowerUtil.PowerCheck1(user);
        model.addAttribute("user", user);
        Experiment experiment = experimentService.getExperimentById(experimentId);
        model.addAttribute("experiment", experiment);
        return "experiment_report_submit";
    }

    @RequestMapping("/submit/experiment/report/submit")
    @ResponseBody
    public Result submitReport(@Param("reportFile") MultipartFile reportFile,@Param("experimentId") Integer experimentId, User user) {
        PowerUtil.PowerCheck1(user);
        String name = minioService.upload(user.getNumber(), user.getName(), reportFile);
        experimentService.submitExperimentReport(name, experimentId, user);
        logger.info("用户："+user.getName()+"，Number："+user.getNumber()+" 提交了实验："+experimentId+"的报告："+name);
        return Result.success("提交成功");
    }
}
