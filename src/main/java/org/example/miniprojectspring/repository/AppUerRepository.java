package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.entity.AppUser;

import java.util.UUID;

@Mapper
public interface AppUerRepository {
    @Select("""
    select * from app_users where app_user_id=#{id};
    """)
    @Results(id="AppUserMap",value = {
            @Result(property = "id", column = "app_user_id"),
            @Result(property = "name", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "level", column = "level"),
            @Result(property = "xp", column = "xp"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createAt", column = "created_at"),
    })
    AppUser getUserById(UUID id);
}
