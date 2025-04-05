package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.dto.request.RegisterRequest;
import org.example.miniprojectspring.model.entity.Profile;
import org.example.miniprojectspring.uuidhandler.UUIDHandler;

import java.util.List;

@Mapper
public interface UserRepository {

    @Select("""
        INSERT INTO app_users(username, email, password, profile_image)
        VALUES (#{request.name}, #{request.email}, #{request.password}, #{request.profileImage})
        RETURNING *
    """)
    @Results(id = "userMapper", value = {
            @Result(property = "id", column = "app_user_id", typeHandler = UUIDHandler.class),
            @Result(property = "name", column = "username"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "level", column = "level"),
            @Result(property = "xpLevel", column = "xp"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    Profile register(@Param("request") RegisterRequest registerRequest);


    @Select("""
        SELECT * FROM app_users
    """)
    @ResultMap("userMapper")
    List<Profile> getAllUser();
}
