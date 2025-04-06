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
    SELECT h.*, u.*
    FROM habits h
    JOIN app_users u ON h.app_user_id = u.app_user_id
    WHERE u.email = #{email}
""")
    @Results(id = "habitMapper", value = {
            @Result(property = "id", column = "habit_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "name", column = "username"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "xpLevel", column = "xp"),
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createAt", column = "created_at"),
            @Result(property = "appUser", column = "email",
                    one = @One(select = "org.example.miniprojectspring.repository.AppUserRepository.getUserBYEmail")
            )
    })
    List<Habit> getAllHabits(@Param("email") String email);


//    fixed this

    @Select("""
                    SELECT * FROM app_users where app_user_id = #{id}
            """)
//    @Result(property = "id",column = "app_user_id",typeHandler = UUIDTypeHandler.class)
    AppUser getUserByUserId(UUID id);

// end fixed


    @Select("""
                INSERT INTO habits (title,description,frequency)
                VALUES (#{request.title},#{request.description},#{request.frequency})
                RETURNING *
            """)
    @ResultMap("habitMapper")
    Habit postHabits(@Param("request") HabitRequest habitRequest);


    @Select("""
                SELECT * FROM habits where habit_id = #{id}
            """)
    @ResultMap("habitMapper")
    Habit getHabitById(@Param("id") UUID id);


    @Select("""
                UPDATE SET title = #{request.title}, description = #{request.description}, frequency = #{request.frequency}
                WHERE habit_id = #{id}
                RETURNING *
            """)
    @ResultMap("habitMapper")
    Habit updateHabitById(@Param("id") UUID id, @Param("request") HabitRequest habitRequest);


    @Select("""
                DELETE FROM habits where habit_id = #{id}
                RETURNING *
            """)
    @ResultMap("habitMapper")
    Habit deleteHabitById(@Param("id") UUID id);
}
