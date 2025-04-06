CREATE  DATABASE SpringMiniProject;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE app_users (
                           app_user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           username VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           level INTEGER DEFAULT 0,
                           xp INTEGER DEFAULT 0,
                           profile_image VARCHAR(255),
                           is_verified BOOLEAN DEFAULT false,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE achievements (
                              achievement_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              title VARCHAR(255) NOT NULL,
                              description TEXT,
                              badge VARCHAR(255),
                              xp_required INTEGER
);

CREATE TABLE app_user_achievements (
                                       app_user_achievement_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                       app_user_id UUID REFERENCES app_users(app_user_id) ON UPDATE  CASCADE ON DELETE CASCADE ,
                                       achievement_id UUID REFERENCES achievements(achievement_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE habits (
                        habit_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        title VARCHAR(255) NOT NULL,
                        description TEXT,
                        frequency VARCHAR(255),
                        is_active BOOLEAN DEFAULT true,
                        app_user_id UUID REFERENCES app_users(app_user_id) ON DELETE CASCADE ON UPDATE CASCADE ,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE habit_logs
    ALTER COLUMN log_date SET DEFAULT CURRENT_TIMESTAMP;
CREATE TABLE habit_logs (
                            habit_log_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            habit_id UUID REFERENCES habits(habit_id) ON UPDATE CASCADE ON DELETE CASCADE,
                            log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            status VARCHAR(255),
                            xp_earned INTEGER DEFAULT 0
);


-- Function to handle when habit log
CREATE OR REPLACE FUNCTION handle_habit_log_insert()
    RETURNS TRIGGER AS $$
DECLARE
    v_app_user_id UUID;
    v_new_xp INTEGER;
BEGIN
    -- 1. Find user ID from habit
    SELECT h.app_user_id INTO v_app_user_id
    FROM habits h
    WHERE h.habit_id = NEW.habit_id;

    -- 2. Update user's XP (+10)
    UPDATE app_users
    SET xp = xp + 10
    WHERE app_user_id = v_app_user_id
    RETURNING xp INTO v_new_xp;

    -- 3. Increase level if XP reaches 100 or more
    IF v_new_xp >= 100 THEN
        UPDATE app_users
        SET level = level + 1, xp = 0  -- Reset XP to 0 after leveling up
        WHERE app_user_id = v_app_user_id;
    END IF;

    -- 4. Update the habit_log xp_earned
    UPDATE habit_logs
    SET xp_earned = 10
    WHERE habit_log_id = NEW.habit_log_id;

    -- 5. Insert achievements if unlocked
    INSERT INTO app_user_achievements (app_user_achievement_id, app_user_id, achievement_id)
    SELECT uuid_generate_v4(), v_app_user_id, a.achievement_id
    FROM achievements a
    WHERE v_new_xp >= a.xp_required
      AND NOT EXISTS (
        SELECT 1
        FROM app_user_achievements aua
        WHERE aua.app_user_id = v_app_user_id
          AND aua.achievement_id = a.achievement_id
    )
    ORDER BY a.xp_required DESC;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Function to call FUNCTION handle_habit_log_insert
CREATE TRIGGER habit_log_insert_trigger
    AFTER INSERT ON habit_logs
    FOR EACH ROW
EXECUTE FUNCTION handle_habit_log_insert();
