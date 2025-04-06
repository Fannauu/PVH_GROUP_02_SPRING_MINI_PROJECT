package org.example.miniprojectspring.repository;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.*;
import org.example.miniprojectspring.UUIDHandler.UUIDTypeHandler;
import org.example.miniprojectspring.model.entity.Achievement;

@Mapper
public interface AchievementRepo {
    @Results(id = "AchievementMapper", value = {
            @Result(property = "id",column = "achievement_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "title",column = "title"),
            @Result(property = "description",column = "description"),
            @Result(property = "badge",column = "badge"),
            @Result(property = "xpRequired",column = "xp_required")
    })
    @Select("""
        SELECT a.*
            FROM achievements a
            JOIN app_user_achievements aua ON aua.achievement_id = a.achievement_id
            WHERE aua.app_user_id = #{appUserId}
            LIMIT #{size} OFFSET #{page};
    """)
    Achievement getAchievementByAppUserId(UUID achievementId,Integer page,Integer size);



    @Select("""
        SELECT * FROM achievements
            ORDER BY achievement_id ASC
            LIMIT #{size} OFFSET #{page}
    """)
    @ResultMap("AchievementMapper")
    List<Achievement> getAchievements(Integer page,Integer size);

}
