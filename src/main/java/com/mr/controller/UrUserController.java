package com.mr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.interfaces.TrackPoint;
import com.mr.model.UrUser;
import com.mr.service.UrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Executors;

@RestController
public class UrUserController {

    @Autowired
    UrUserService urUserService;
    @Autowired
    ObjectMapper objectMapper;

    @TrackPoint
    @RequestMapping("urUsers")
    public String getAllUrUsers() throws JsonProcessingException {
        List<UrUser> users = urUserService.findAllUrUsers();
        String jsonRet = objectMapper.writeValueAsString(users);
        return jsonRet;
    }
}
