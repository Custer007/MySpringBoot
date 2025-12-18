package com.mr.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 过滤器
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "hello")
public class CountFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("执行过滤器init方法");
        ServletContext context = filterConfig.getServletContext();
        context.setAttribute("count", 0);
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("执行过滤器doFilter方法");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ServletContext context = request.getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        context.setAttribute("count", ++count);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("执行过滤器destroy方法");
        Filter.super.destroy();
    }
}
