package org.example.miniprojectspring.repository;


import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.UUIDHandler.UUIDTypeHandler;
import org.example.miniprojectspring.model.entity.Achievement;

import java.util.List;
import java.util.UUID;

@Mapper
public interface AchievementRepository {
    @Select("""
        select * from achievements 
        OFFSET #{page} * (#{size} - 1)
        LIMIT #{page}
    """)
    @Results(id = "achievementMapper", value = {
            @Result(property = "id", column = "achievement_id"),
            @Result(property = "xpRequired", column = "xp_required"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "badge", column = "badge")
    })
    List<Achievement> getAchievements(@Param("size") Integer size,@Param("page") Integer page);


    @Select("""
    SELECT a.* 
    FROM app_user_achievements aua
    JOIN achievements a ON a.achievement_id = aua.achievement_id
    WHERE aua.app_user_id = #{userId}
    
""")
    @ResultMap("achievementMapper")
    List<Achievement> getAchievementByAppUserId(
            @Param("userId") UUID userId,
            @Param("size") Integer size,
            @Param("page") Integer page
    );



}
