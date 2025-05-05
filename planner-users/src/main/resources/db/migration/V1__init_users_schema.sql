CREATE TABLE user_data (
                           id bigint NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                               email TEXT,
                                               username TEXT,
                                               userpassword TEXT
);
INSERT INTO user_data (email, username, userpassword) VALUES
                                                                ('test1@example.com', 'testuser1', 'password1'),
                                                                ('test2@example.com', 'testuser2', 'password2');
