package com.mr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.interfaces.TrackPoint;
import com.mr.model.People;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 *
 */
@Slf4j
@RestController
public class HelloController {

    @Autowired
    private People peo;
    @Autowired
    ObjectMapper objectMapper;
    /*@Autowired
    StringRedisTemplate template;// Redis驱动*/

    @RequestMapping("hello")
    @TrackPoint(value = "sayHello", desc = "听我说谢谢你因为有你", printParam = false)
    public String sayHello(HttpServletRequest req) throws Exception {

        peo.setAge(12);
        peo.setName("莱布尼茨");

        String json = objectMapper.writeValueAsString(peo);
        log.info("解析json格式值=：" + json);


        /*Set<String> keys = template.keys("*");  //keys("*") 表示获取redis所有的键
        for (String key : keys) {
            String value = template.opsForValue().get(key);
            log.info("redis缓存key=" + key + ";value=" + value);
        }*/

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        ServletContext context = req.getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        return "欢迎" + peo.getName() + "来到第" + count + "次访问SpringBoot项目";
    }
}
