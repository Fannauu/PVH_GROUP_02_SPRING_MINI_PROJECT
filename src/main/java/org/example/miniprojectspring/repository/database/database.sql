CREATE  DATABASE SpringMiniProject;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE app_users (
                           app_user_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                           username VARCHAR(255) NOT NULL,
                           email VARCHAR(255) UNIQUE NOT NULL,
                           password VARCHAR(255) NOT NULL,
                           level INTEGER,
                           xp INTEGER,
                           profile_image VARCHAR(255),
                           is_verified BOOLEAN,
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


INSERT INTO habits(title,description,frequency)
VALUES ('dedsds','hjhkkh','iuhjj')
RETURNING*;


    SELECT a.attendee_id, a.attendee_name, a.email
    FROM attendee a
    INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
    WHERE ea.event_id = #{eventId}


