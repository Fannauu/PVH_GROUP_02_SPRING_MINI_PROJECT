-- Insert data into app_users table
INSERT INTO app_users (username, email, password, level, xp, profile_image, is_verified) VALUES
     ('user1', 'user1@example.com', 'password123', 1, 0, 'profile1.jpg', true),
     ('user2', 'user2@example.com', 'secure456', 2, 100, 'profile2.png', false),
     ('user3', 'user3@example.com', 'pass789', 3, 250, 'profile3.jpeg', true),
     ('user4', 'user4@example.com', 'mysecret', 1, 50, 'profile4.gif', false),
     ('user5', 'user5@example.com', 'strongpass', 4, 500, 'profile5.svg', true),
     ('user6', 'user6@example.com', 'testpass', 2, 150, 'profile6.webp', false),
     ('user7', 'user7@example.com', 'anotherpass', 5, 750, 'profile7.jpg', true),
     ('user8', 'user8@example.com', 'finalpass', 3, 300, 'profile8.png', false),
     ('user9', 'user9@example.com', 'securepass', 1, 25, 'profile9.jpeg', true),
     ('user10', 'user10@example.com', 'simplepass', 6, 1000, 'profile10.gif', false);

SELECT * FROM achievements;
-- Insert data into achievements table
INSERT INTO achievements (title, description, badge, xp_required) VALUES
      ('First Step', 'Created your first habit.', 'badge1.png', 10),
      ('Weekly Streak', 'Completed a habit for 7 consecutive days.', 'badge2.png', 50),
      ('Monthly Milestone', 'Completed a habit for 30 days.', 'badge3.png', 200),
      ('Level Up', 'Reached level 2.', 'badge4.png', 100),
      ('Halfway Hero', 'Reached 500 XP.', 'badge5.png', 500),
      ('Early Bird', 'Completed a habit before 8 AM.', 'badge6.png', 25),
      ('Night Owl', 'Completed a habit after 10 PM.', 'badge7.png', 30),
      ('Consistent User', 'Logged in 7 days in a row.', 'badge8.png', 75),
      ('Active Achiever', 'Completed 3 achievements.', 'badge9.png', 150),
      ('Ultimate Achiever', 'Completed 10 achievements', 'badge10.png', 1000);

-- Insert data into app_user_achievements table
INSERT INTO app_user_achievements (app_user_id, achievement_id) VALUES
    ((SELECT app_user_id FROM app_users WHERE username = 'user1'), (SELECT achievement_id FROM achievements WHERE title = 'First Step')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user2'), (SELECT achievement_id FROM achievements WHERE title = 'Weekly Streak')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user3'), (SELECT achievement_id FROM achievements WHERE title = 'Monthly Milestone')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user4'), (SELECT achievement_id FROM achievements WHERE title = 'Level Up')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user5'), (SELECT achievement_id FROM achievements WHERE title = 'Halfway Hero')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user6'), (SELECT achievement_id FROM achievements WHERE title = 'Early Bird')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user7'), (SELECT achievement_id FROM achievements WHERE title = 'Night Owl')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user8'), (SELECT achievement_id FROM achievements WHERE title = 'Consistent User')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user9'), (SELECT achievement_id FROM achievements WHERE title = 'Active Achiever')),
    ((SELECT app_user_id FROM app_users WHERE username = 'user10'), (SELECT achievement_id FROM achievements WHERE title = 'Ultimate Achiever'));

-- Insert data into habits table
INSERT INTO habits (title, description, frequency, is_active, app_user_id) VALUES
   ('Read a book', 'Read for 30 minutes.', 'Daily', true, (SELECT app_user_id FROM app_users WHERE username = 'user1')),
   ('Exercise', 'Go for a 1-hour run.', 'Weekly', true, (SELECT app_user_id FROM app_users WHERE username = 'user2')),
   ('Learn a language', 'Practice for 15 minutes.', 'Daily', true, (SELECT app_user_id FROM app_users WHERE username = 'user3')),
   ('Meditate', 'Meditate for 10 minutes.', 'Daily', true, (SELECT app_user_id FROM app_users WHERE username = 'user4')),
   ('Write in journal', 'Write 3 pages.', 'Weekly', true, (SELECT app_user_id FROM app_users WHERE username = 'user5')),
   ('Drink water', 'Drink 8 glasses of water.', 'Daily', true, (SELECT app_user_id FROM app_users WHERE username = 'user6')),
   ('Cook healthy meal', 'Cook a balanced meal.', 'Weekly', true, (SELECT app_user_id FROM app_users WHERE username = 'user7')),
   ('Study', 'Study for 2 hours.', 'Daily', true, (SELECT app_user_id FROM app_users WHERE username = 'user8')),
   ('Clean room', 'Clean for 30 minutes', 'Weekly', true, (SELECT app_user_id FROM app_users WHERE username = 'user9')),
   ('Learn a skill', 'Practice daily', 'Daily', true, (SELECT app_user_id FROM app_users WHERE username = 'user10'));

-- Insert data into habit_logs table
INSERT INTO habit_logs (habit_id, log_date, status, xp_earned) VALUES
   ((SELECT habit_id FROM habits WHERE title = 'Read a book'), '2023-10-26 10:00:00', 'Completed', 10),
   ((SELECT habit_id FROM habits WHERE title = 'Exercise'), '2023-10-26 11:00:00', 'Completed', 20),
   ((SELECT habit_id FROM habits WHERE title = 'Learn a language'), '2023-10-26 12:00:00', 'Completed', 15),
   ((SELECT habit_id FROM habits WHERE title = 'Meditate'), '2023-10-26 13:00:00', 'Completed', 10),
   ((SELECT habit_id FROM habits WHERE title = 'Write in journal'), '2023-10-26 14:00:00', 'Completed', 25),
   ((SELECT habit_id FROM habits WHERE title = 'Drink water'), '2023-10-26 15:00:00', 'Completed', 5),
   ((SELECT habit_id FROM habits WHERE title = 'Cook healthy meal'), '2023-10-26 16:00:00', 'Completed', 30),
   ((SELECT habit_id FROM habits WHERE title = 'Study'), '2023-10-26 17:00:00', 'Completed', 20),
   ((SELECT habit_id FROM habits WHERE title = 'Clean room'), '2023-10-26 18:00:00', 'Completed', 15),
   ((SELECT habit_id FROM habits WHERE title = 'Learn a skill'), '2023-10-26 19:00:00', 'Completed', 20);