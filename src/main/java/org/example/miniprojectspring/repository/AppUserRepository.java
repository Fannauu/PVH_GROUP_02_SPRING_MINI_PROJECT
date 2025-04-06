package org.example.miniprojectspring.repository;


import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.exception.UUIDTypeHandler;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.entity.AppUser;


@Mapper
public interface AppUserRepository {
    @Select("""
         SELECT * FROM app_users
         WHERE email= #{email}
         """)
    @Results(id = "appUSer" , value = {
            @Result(property = "uerId", column = "user_id"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "roles", column = "user_id",
                    many =@Many(select = "getAllRowByUserId"))
    })
    AppUser getUserBYEmail(String email) ;

    @Select("""
     INSERT INTO app_users (username, email, password, profile_image)
     VALUES (#{request.username}, #{request.email}, #{request.password}, #{request.profileImage})
     RETURNING *
 """)
    @Results(id = "appUserMapper", value = {
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "id", column = "app_user_id", typeHandler = UUIDTypeHandler.class)
    }
    )
    AppUser register(@Param("request") AppUserRequest appUserRequest);
}
