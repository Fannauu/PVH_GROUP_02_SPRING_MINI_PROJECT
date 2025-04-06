package org.example.miniprojectspring.repository;


import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.UUIDHandler.UUIDTypeHandler;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepository {
    @Select("""
                SELECT h.habit_id, h.title, h.description, h.frequency, h.is_active, h.app_user_id
                FROM habits h
                offset #{size} * (#{page} -1)
                limit #{size}
            
            """)
    @Results(id = "habitMapper", value = {
            @Result(property = "id", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "appUser", column = "app_user_id",
                    one = @One(select = "org.example.miniprojectspring.repository.AppUserRepository.getCurrentUserById"))
    })
    List<Habit> getAllHabits(Integer size, Integer page);

    @Select("""
                SELECT h.habit_id, h.title, h.description, h.frequency, h.is_active, h.app_user_id
                FROM habits h WHERE h.habit_id = #{id}
            """)
    @ResultMap("habitMapper")
    Habit getHabitById(UUID id);

    @Select("""
                INSERT INTO habits(title, description, frequency, app_user_id) VALUES (#{request.title}, #{request.description}, #{request.frequency}, 
                                                                                       (SELECT app_user_id FROM app_users WHERE email = #{email}))
                RETURNING *
            """)
    @ResultMap("habitMapper")
    @Result(property = "email", column = "email")
    Habit postHabits(@Param("request") HabitRequest habitRequest, String email);


    @Select("""
                UPDATE habits SET title = #{request.title}, description = #{request.description} , frequency = #{request.frequency} WHERE habit_id = #{id} RETURNING *
            """)
    @ResultMap("habitMapper")
    Habit updateHabitById(UUID id, @Param("request") HabitRequest habitRequest);

    @Select("""
            DELETE FROM habits WHERE habit_id = #{id} RETURNING *
            """)
    @ResultMap("habitMapper")
    Habit deleteHabitById(UUID id);
}
