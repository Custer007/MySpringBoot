package com.mr.service.impl;

import com.mr.mapper.UrUserMapper;
import com.mr.model.UrUser;
import com.mr.service.UrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrUserServiceImpl implements UrUserService {

    @Autowired
    UrUserMapper urUserMapper;

    @Override
    public List<UrUser> findAllUrUsers() {
         List<UrUser> userList = urUserMapper.selectAllUsers();
        return userList;
    }
}
