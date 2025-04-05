package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.miniprojectspring.model.entity.AppUser;

@Mapper
public interface AppUserRepository {

    @Select("""
        SELECT * from app_users
        WHERE username = #{username}
    """)
    @Results(id = "userMapper",value = {
            @Result(property = "id",column = "app_user_id"),
            @Result(property = "name",column = "username")
    })
    public AppUser findByUsername(String username);
}
