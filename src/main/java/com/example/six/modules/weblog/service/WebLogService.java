package com.example.six.modules.weblog.service;

import com.example.six.modules.weblog.entity.WebLogEntity;

import java.util.List;

public interface WebLogService {
    void insertWebLog(WebLogEntity webLogEntity);
    List<WebLogEntity> getAllLog();
}
