package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.uuidHandler.UUIDTypeHandler;

import java.util.UUID;

@Mapper
public interface HabitRepository {
    // Query Post
    @Select("""
    INSERT INTO habits(title, description, frequency)
    VALUES (#{request.title}, #{request.description}, #{request.frequency})
    RETURNING *
""")
    @Results(id = "habitMap", value = {
            @Result(property = "habitId", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "isActive", column = "is_active"),
//            @Result(property = "appUserId", column = "app_user_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "createdAt", column = "created_at")
    })
    Habit createHabit(@Param("request") HabitRequest habitRequest);

    //Query Get By ID
    @Select("""
        SELECT * FROM habits WHERE habit_id = #{habitId}
    """)
    @ResultMap("habitMap")
    Habit getHabitById(UUID habitId);
}
