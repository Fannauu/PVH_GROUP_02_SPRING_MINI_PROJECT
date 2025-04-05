package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.model.dto.request.HabitLogRequest;
import org.example.miniprojectspring.model.entity.HabitLog;
import org.example.miniprojectspring.uuidHandler.UUIDTypeHandler;

import java.util.UUID;

@Mapper
public interface HabitLogRepository {

    //Query Get Method
    @Select("""
    SELECT * FROM habit_logs
    WHERE habit_id = #{habitId}
""")
    @Results(id = "habitLogMapper", value = {
            @Result(property = "habitLogId", column = "habit_log_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "logDate", column = "log_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "xpEarned", column = "xp_earned"),
            @Result(property = "habitId", column = "habit_id")
    })
    HabitLog getHabitLogByHabitId(Integer page, Integer size, UUID habitId);

    //Query Post Method
    @Select("""
    INSERT INTO habit_logs (status, habit_id)
    VALUES ( #{status}, #{habitId} )
    RETURNING *
""")
    @ResultMap("habitLogMapper")
    HabitLog createHabitLog(HabitLogRequest habitLogRequest);

}
