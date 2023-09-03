package com.example.six.modules.weblog.controller;

import cn.hutool.core.util.StrUtil;
import com.example.six.core.api.controller.BaseController;
import com.example.six.core.utils.ApiRest;
import com.example.six.modules.weblog.entity.WebLogEntity;
import com.example.six.modules.weblog.service.WebLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author geeksix
 * @create 2023/8/29 10:35
 */
@RestController
@RequestMapping("/api/weblogs")
public class WebLogController extends BaseController {

    @Autowired
    private WebLogService webLogService;

    @GetMapping
    public ApiRest webLogPage(@RequestParam int pageNum,
                              @RequestParam int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<WebLogEntity> webLogs;
        webLogs = webLogService.getAllLog();
        PageInfo<WebLogEntity> pageInfo = new PageInfo<>(webLogs);
        return super.success(pageInfo);
    }
}
