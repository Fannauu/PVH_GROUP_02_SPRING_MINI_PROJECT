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
    """)
    @Results(id = "achievementMapper" , value = {
            @Result(property = "id",column = "achievement_id",typeHandler = UUIDTypeHandler.class),
            @Result(property = "xpRequired",column = "xp_required")
    })
    List<Achievement> getAchievements();


    @Select("""
                select * from achievements
                offset #{size} * (#{page} -1)
                limit #{size}
            """)
    @ResultMap("achievementMapper")
    Achievement getByUserId(UUID userId, int size, int page);
}
