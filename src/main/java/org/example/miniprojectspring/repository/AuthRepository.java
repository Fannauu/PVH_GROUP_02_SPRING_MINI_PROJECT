package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.exception.UUIDTypeHandler.UUIDTypeHandler;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
public interface AuthRepository {

    @Select("""
    INSERT INTO app_users (username, email, password, profile_image) VALUES (#{request.username}, #{request.email}, #{request.password}, #{request.profileImageUrl})
    RETURNING *
""")

    @Results(id = "appUserMapper", value = {
            @Result(property = "profileImageUrl", column = "profile_image"),
            @Result(property = "appUserId", column = "app_user_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "isVerified", column = "is_verified")
    }
    )
    AppUser register(@Param("request") @RequestBody AppUserRequest appUserRequest);



    @Select("""
    SELECT * FROM app_users
    WHERE email = #{email}
""")
    @ResultMap("appUserMapper")
    AppUser getUserByEmail(String email);

//    @Update("""
//    UPDATE app_users SET is_verified = #{request.isVerified}
//    WHERE email = #{email}
//""")
//    void save(@Param(("request")) AppUser appUser, String email);

    @Update("""
    UPDATE app_users
    SET is_verified = #{request.isVerified}
    WHERE email = #{request.email}
""")
    void save(@Param("request") AppUser appUser);

}

