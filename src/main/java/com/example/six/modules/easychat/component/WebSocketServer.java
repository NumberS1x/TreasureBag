package com.example.six.modules.easychat.component;



import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author geeksix
 * @create 2023/9/4 17:58
 */
@ServerEndpoint("/easychat/{username}")
@Component
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 记录当前在线连接数
     */
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();



    /**
     * 在建立连接的时候调用
     */
    @OnOpen
    public void OnOpen(Session session, @PathParam("username") String username){
//        将连接加入容器中
        sessionMap.put(username,session);
        log.info("有新用户加入，username={},当前在线人数为{}",username,sessionMap.size());
//        存放所有用户名称
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        result.set("users",array);
        for(String name : sessionMap.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.set("username",name);
            array.set(jsonObject);
        }
        sendAllMessage(JSONUtil.toJsonStr(result));
    }

    @OnMessage
    public void OnMessage(String message,Session session,@PathParam("username") String username){
        log.info("接收到客户端username={}发送过来的消息{}",username,message);
        JSONObject jsonObject = JSONUtil.parseObj(message);
        String toUserName = jsonObject.getStr("to");
        String text = jsonObject.getStr("text");
        Session toSession = sessionMap.get(toUserName);
        if (toSession != null){
            JSONObject obj = new JSONObject();
            obj.set("from",username);
            obj.set("text",text);
            this.sendMessage(obj.toString(),toSession);
            log.info("发送给用户username={}，消息：{}", toUserName, jsonObject.toString());
        }else {
            log.info("发送失败，未找到用户username={}的session", toUserName);
        }
    }


    /**
     * 链接关闭时调用的方法
     */
    @OnClose
    public void onClose(Session session,@PathParam("username") String username){
        sessionMap.remove(username);  //移除掉当前断开的用户
        log.info("有一链接已经关闭，移除username={}的用户session,当前的在线人数为{}",username,sessionMap.size());
    }


    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    /**
     *服务端发给对应消息的客户端
     */
    public void sendMessage(String message,Session session){
        try{
            log.info("服务端给客户端[{}]发送消息{}",session.getId(),message);
            session.getBasicRemote().sendText(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * 服务端发送信息给所有的客户端
     */
    public void sendAllMessage(String message){
        try {
            for(Session session : sessionMap.values()){
                log.info("服务端给客户端[{}]发送消息{}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
