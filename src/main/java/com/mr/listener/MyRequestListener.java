package com.mr.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 监听器
 */
@Component
@Slf4j
public class MyRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("执行监听器销毁方法");
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        String sessionId = servletRequest.getSession().toString();
        log.info("sessionId:" + sessionId + "已销毁");
    }

    public void requestInitialized(ServletRequestEvent sre) {
        log.info("执行监听器初始化方法");
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        String id = servletRequest.getRemoteAddr();
        String url = servletRequest.getRequestURL().toString();
        String sessionId = servletRequest.getSession().getId();

        log.info("监听器获取前端请求地址：" + id);
        log.info("监听器获取前端请求URL：" + url);
        log.info("监听器获取前端请求sessionId：" + sessionId);
    }
}
