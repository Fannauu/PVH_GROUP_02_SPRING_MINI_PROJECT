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
            @Result(property = "id",column = "habit_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "createAt",column = "created_at"),
            @Result(property = "appUser",column = "app_user_id",
                    one = @One(select = "getUserByUserId")
            )
    })
    List<Habit> getAllHabits();



    @Select("""
            SELECT * FROM app_users where app_user_id = '8b52b756-eeaf-4a50-9e82-a22f7c47454a'
            """)
    AppUser getUserByUserId(UUID habitId);

    Habit postHabits(HabitRequest habitRequest);
}
