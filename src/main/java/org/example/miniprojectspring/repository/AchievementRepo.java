package org.example.miniprojectspring.repository;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.example.miniprojectspring.model.entity.Achievement;
import org.example.miniprojectspring.uuidhandler.UUIDTypeHandler;

@Mapper
public interface AchievementRepo {
    @Results(id = "AchievementMapper", value = {
            @Result(property = "id",column = "achievement_id", typeHandler = UUIDTypeHandler.class),
            @Result(property = "xpRequired",column = "xp_required")

    })
    @Select("""
        SELECT * FROM achievements WHERE achievement_id = #{achievement_id};
    """)
    Achievement getAchievementByAppUserId(UUID achievementId);



    @Select("""
        SELECT * FROM  achievements;
    """)
    @ResultMap("AchievementMapper")
    List<Achievement> getAchievements(Integer page,Integer size);
}
