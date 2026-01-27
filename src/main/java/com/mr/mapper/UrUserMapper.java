package com.mr.mapper;

import com.mr.model.UrUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UrUserMapper {

    UrUser selectUserById(Integer id);

    //@Select({"select * from ur_user"})
    List<UrUser> selectAllUsers();

    void insertUser(UrUser user);

    void updateUser(UrUser user);

    void deleteUser(Integer id);
}
