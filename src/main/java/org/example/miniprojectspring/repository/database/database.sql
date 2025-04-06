CREATE  DATABASE SpringMiniProject;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


create table if not exists public.app_users
(
    app_user_id   uuid      default uuid_generate_v4() not null
        primary key,
    username      varchar(255)                         not null,
    email         varchar(255)                         not null,
    password      varchar(255)                         not null,
    level         integer   default 1,
    xp            integer   default 0,
    profile_image varchar(255),
    is_verified   boolean   default false,
    created_at    timestamp default now()
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
                        is_active BOOLEAN,
                        app_user_id UUID REFERENCES app_users(app_user_id) ON DELETE CASCADE ON UPDATE CASCADE ,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE habit_logs (
                            habit_log_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            habit_id UUID REFERENCES habits(habit_id) ON UPDATE CASCADE ON DELETE CASCADE,
                            log_date TIMESTAMP NOT NULL,
                            status VARCHAR(255),
                            xp_earned INTEGER
);





INSERT INTO app_users (username, email, password, level, xp, profile_image, is_verified)
VALUES
    ('alice', 'alice@example.com', 'password123', 2, 150, 'alice.png', true),
    ('bob', 'bob@example.com', 'securepass', 3, 400, 'bob.jpg', true),
    ('carla', 'carla@example.com', 'pass456', 1, 50, NULL, false),
    ('david', 'david@example.com', 'david123', 4, 800, 'david.png', true),
    ('ella', 'ella@example.com', 'ella2024', 1, 20, NULL, false),
    ('frank', 'frank@example.com', 'frankly', 2, 250, 'frank.jpg', true),
    ('grace', 'grace@example.com', 'grace!pass', 5, 1000, 'grace.jpg', true),
    ('harry', 'harry@example.com', 'harry456', 1, 70, NULL, false),
    ('isla', 'isla@example.com', 'islapass', 3, 350, 'isla.png', true),
    ('jack', 'jack@example.com', 'jack1234', 2, 200, NULL, false);

-- Insert Data into achievements
INSERT INTO achievements (title, description, badge, xp_required)
VALUES
    ('First Login', 'Logged in for the first time', 'badge_login.png', 0),
    ('Beginner Level', 'Reached level 1', 'badge_level1.png', 100),
    ('Habit Streak', 'Completed a habit 7 days in a row', 'badge_streak.png', 500),
    ('Consistency King', 'Logged habits for 30 days', 'badge_consistency.png', 1000),
    ('Early Riser', 'Logged habit before 6AM', 'badge_early.png', 200),
    ('Night Owl', 'Logged habit after 10PM', 'badge_nightowl.png', 200),
    ('Socializer', 'Connected with 5 friends', 'badge_social.png', 150),
    ('Profile Complete', 'Completed profile info', 'badge_profile.png', 50),
    ('Task Master', 'Completed 50 habit logs', 'badge_taskmaster.png', 800),
    ('Pro Planner', 'Created 10 habits', 'badge_planner.png', 300);


SELECT a.*
FROM app_user_achievements aua
         JOIN achievements a ON a.achievement_id = aua.achievement_id
WHERE aua.app_user_id = '69031cab-2263-4a96-9c10-6184b2dba7cf'