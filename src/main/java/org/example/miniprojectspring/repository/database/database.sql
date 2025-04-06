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
    v_frequency VARCHAR(255);
    v_xp_gain INTEGER := 0;
    v_new_xp INTEGER;
    v_current_xp INTEGER;
BEGIN
    -- Find app_user_id and frequency in habits
    SELECT h.app_user_id, h.frequency INTO v_app_user_id, v_frequency
    FROM habits h
    WHERE h.habit_id = NEW.habit_id;

    -- Check if status is COMPLETE with any frequency
    IF NEW.status = 'COMPLETE' THEN
        -- condition on frequency
        IF v_frequency = 'DAILY' THEN
            v_xp_gain := 10;
        ELSIF v_frequency = 'WEEKLY' THEN
            v_xp_gain := 20;
        ELSIF v_frequency = 'MONTHLY' THEN
            v_xp_gain := 30;
        END IF;

        -- Add xp to app_users
        UPDATE app_users
        SET xp = xp + v_xp_gain
        WHERE app_user_id = v_app_user_id
        RETURNING xp INTO v_current_xp;

        -- condition to app level and still keep the current value
        IF v_current_xp >= 100 AND (SELECT level FROM app_users WHERE app_user_id = v_app_user_id) = 0 THEN
            UPDATE app_users
            SET level = 1, xp = 100
            WHERE app_user_id = v_app_user_id;
        END IF;

        -- If XP >= 100, update level and keep XP > 100 for future levels
        IF v_current_xp >= 100 THEN
            WHILE v_current_xp >= 100 LOOP
                    UPDATE app_users
                    SET level = level + 1
                    WHERE app_user_id = v_app_user_id;


                    v_current_xp := v_current_xp - 100;
                    UPDATE app_users
                    SET xp = v_current_xp
                    WHERE app_user_id = v_app_user_id;
                END LOOP;
        END IF;

        -- Insert achievements
        INSERT INTO app_user_achievements (app_user_achievement_id, app_user_id, achievement_id)
        SELECT uuid_generate_v4(), v_app_user_id, a.achievement_id
        FROM achievements a
        WHERE a.xp_required <= v_current_xp
          AND NOT EXISTS (
            SELECT 1
            FROM app_user_achievements aua
            WHERE aua.app_user_id = v_app_user_id
              AND aua.achievement_id = a.achievement_id
        )
        ORDER BY a.xp_required DESC;
    ELSE
        -- If status = MISSED or any other, give 0 XP
        v_xp_gain := 0;
    END IF;

    -- Always update habit_logs with xp_earned
    UPDATE habit_logs
    SET xp_earned = v_xp_gain
    WHERE habit_log_id = NEW.habit_log_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger
CREATE TRIGGER habit_log_insert_trigger
    AFTER INSERT ON habit_logs
    FOR EACH ROW
EXECUTE FUNCTION handle_habit_log_insert();
