package com.robin.sys.controller;

import com.robin.sys.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MinioController {
    @Autowired
    private MinioService minioService;
}
