package com.example.six.modules.weblog.service.impl;

import com.example.six.modules.weblog.entity.WebLogEntity;
import com.example.six.modules.weblog.mapper.WebLogMapper;
import com.example.six.modules.weblog.service.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("webLogService")
public class WebLogServiceImpl implements WebLogService {

    @Autowired
    private WebLogMapper webLogMapper;


    @Override
    public void insertWebLog(WebLogEntity webLogEntity){
        webLogMapper.insert(webLogEntity);
    }

    @Override
    public List<WebLogEntity> getAllLog(){
        return webLogMapper.getLogList();
    }


}
