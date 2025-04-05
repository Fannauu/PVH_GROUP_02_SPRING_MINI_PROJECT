package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.dto.request.HabitRequest;
import org.example.miniprojectspring.model.entity.Habit;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitRepositoty {
    @Select("""
    SELECT * from habits;
    """)
    @Results(id="HabitMap", value = {
            @Result(property = "habit_id",column = "habit_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "frequency", column = "frequency"),
            @Result(property = "isActive",column = "is_active"),
            @Result(property = "createAt",column = "created_at"),
            @Result(property = "appuser",column = "app_user_id",
                    one=@One(select = "org.example.miniprojectspring.repository.AppUerRepository.getUserById"))
    })
    List<Habit>  getAllHabits();

    @Select("""
        insert into habits(title,description,frequency)
        values (#{request.title},#{request.description},#{request.frequency})
        returning *
    """)
    @ResultMap("HabitMap")
    Habit postHabits(@Param("request") HabitRequest habitRequest);

    @Select("""
        delete from habits where habit_id = #{id}
    """)
    @ResultMap("HabitMap")
    Habit deleteHabitById(UUID id);

    @Select("""
        select * from habits where habit_id = #{habit_id}
    """)
    @ResultMap({"HabitMap"})
    Habit getHabitById(UUID habit_id);

    @Select("""
    UPDATE habits set title= #{request.title}, description= #{request.description}, frequency= #{request.frequency} where habit_id= #{id}
    returning *
    """)
    @ResultMap({"HabitMap"})
    Habit updateHabit(UUID id, @Param("request") HabitRequest habitRequest);
}
