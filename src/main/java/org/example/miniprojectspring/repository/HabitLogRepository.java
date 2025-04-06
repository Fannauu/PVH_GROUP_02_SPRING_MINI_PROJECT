package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.entity.HabitLog;


import java.util.List;
import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    //Query Get Method
    @Select("""
    SELECT * FROM habit_logs
    WHERE habit_id = #{habitId}
""")
    @Results(id = "habitLogMapper", value = {
            @Result(property = "id", column = "habit_log_id"),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habitId", column = "habit_id",
                    one = @One(select = "org.example.miniprojectspring.repository.HabitRepository.getHabitById"))
    })
    List<HabitLog> getHabitLogByHabitId(UUID habitId);

    //Query Post Method
    @Select("""
    INSERT INTO habit_logs(status, habit_id)
    VALUES ( #{request.status}, #{request.habitId})
    RETURNING *
""")
    @ResultMap("habitLogMapper")
    HabitLog createHabitLog(@Param("request") HabitLogRequest habitLogRequest);

}
