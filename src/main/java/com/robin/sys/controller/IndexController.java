package com.robin.sys.controller;

import com.robin.sys.domain.view.ExperimentRecordView;
import com.robin.sys.domain.User;
import com.robin.sys.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ExperimentService experimentService;

    @RequestMapping("/index")
    public String index(Model model, User user){
        if (user == null) {
            return "login";
        }
        List<ExperimentRecordView> views = experimentService.listExperimentRecordView();
        model.addAttribute("views", views);
        model.addAttribute(user);
        return "index";
    }
}
