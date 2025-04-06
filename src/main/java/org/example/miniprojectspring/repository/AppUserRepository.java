package org.example.miniprojectspring.repository;


import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.UUIDHandler.UUIDTypeHandler;
import org.example.miniprojectspring.model.dto.request.AppUserRequest;
import org.example.miniprojectspring.model.dto.request.ProfileRequest;
import org.example.miniprojectspring.model.dto.response.UserDTO;
import org.example.miniprojectspring.model.entity.AppUser;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AppUserRepository {



    @Select("""
     INSERT INTO app_users (username, email, password, profile_image)
     VALUES (#{request.username}, #{request.email}, #{request.password}, #{request.profileImage})
     RETURNING *
 """)
    @Results(id = "appUserMapper", value = {
            @Result(property = "name", column = "username"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "id", column = "app_user_id"),
            @Result(property = "xpLevel", column = "xp"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    }
    )
    AppUser register(@Param("request") AppUserRequest appUserRequest);


    @Select("""
         SELECT * FROM app_users
         WHERE email= #{email}
         """)
    @ResultMap("appUserMapper")
    AppUser getUserBYEmail(String email);


    @Select("""
            UPDATE app_users
            SET username = #{request.name}, profile_image = #{request.profileImage}
            WHERE email= #{email}
            RETURNING *
            """)
    @ResultMap("appUserMapper")
    AppUser updateNameAndImgOfUser(String email, @Param("request") ProfileRequest profileRequest);

    @Select("""
            DELETE FROM app_users WHERE email = #{email}
            RETURNING *
            """)
    @ResultMap("appUserMapper")
    AppUser deleteCurrentUser(String email);


    @Select("""
        SELECT * FROM app_users WHERE app_user_id = #{id}
    """)
    @Results(id = "userDTOMapper", value = {
            @Result(property = "id", column = "app_user_id"),
            @Result(property = "name", column = "username"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "xpLevel", column = "xp"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at"),
    })
    UserDTO getCurrentUserById(UUID id);

//private UUID id;
//    private String name;
//    private String email;
//    private Integer level;
//    private Integer xpLevel;
//    private String profileImage;
//    private Boolean isVerified;
//    private LocalDateTime createdAt;
}