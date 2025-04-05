package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;
import org.example.miniprojectspring.uuihander.UUIDTypeHandler;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface HabitReposity {



    @Select("""
        SELECT * FROM habits;
    """)
    @Results(id="HabitMapper",value = {
            @Result(property = "id", column = "habit_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "createAt",column = "created_at"),
            @Result(property = "profile", column = "app_user_id",
                    one = @One(select = "")),
    })
    List<Habit> getAllHabits();


    @Select("""
        INSERT INTO habits(title,description,frequency) 
        VALUES (#{request.title},#{request.description},#{request.frequency})
        RETURNING*;
    """)
    @ResultMap("HabitMapper")
    Habit postHabits(@Param("request") HabitRequest habitRequest);

}
