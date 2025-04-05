package org.example.miniprojectspring.repository;


import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.UUIDHandler.UUIDTypeHandler;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.AppUser;
import org.example.miniprojectspring.model.entity.Habit;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Select("""
        SELECT * FROM habits
    """)
    @Results(id="habitMapper", value = {
            @Result(property = "name", column = "username"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "xpLevel", column = "xp"),
            @Result(property = "id",column = "habit_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "createAt",column = "created_at"),
            @Result(property = "appUser",column = "email",
                    one = @One(select = "org.example.miniprojectspring.repository.AppUserRepository.getUserBYEmail")
            )
    })
    List<Habit> getAllHabits(String email);



//    @Select("""
//            SELECT * FROM app_users where email = #{email}
//            """)
////    @Result(property = "",column = "app_user_id",typeHandler = UUIDTypeHandler.class)
//    AppUser getUserByUserId(String email);

    Habit postHabits(HabitRequest habitRequest);
}
