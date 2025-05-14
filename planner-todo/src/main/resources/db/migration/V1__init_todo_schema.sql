CREATE TABLE priority (
                          id bigint NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          title TEXT,
                          color TEXT,
                          user_id BIGINT
);

CREATE TABLE categories (
                            id bigint NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            title TEXT,
                            completed_count BIGINT,
                            uncompleted_count BIGINT,
                            user_id BIGINT
);

CREATE TABLE task (
                      id BIGSERIAL PRIMARY KEY,
                      title TEXT NOT NULL,
                      completed BIGINT NOT NULL,
                      task_date TIMESTAMP without time zone,
                      priority_id BIGINT REFERENCES priority(id),
                      category_id BIGINT REFERENCES categories(id),
                      user_id BIGINT
);

INSERT INTO priority (title, color, user_id) VALUES
                                                 ('Высокий', 'красный', 1),
                                                 ('Средний', 'желтый', 1),
                                                 ('Низкий', 'зеленый', 1),
                                                 ('Высокий', 'красный', 2),
                                                 ('Низкий', 'зеленый', 2);

INSERT INTO categories (title, completed_count, uncompleted_count, user_id) VALUES
                                                                                ('Работа', 5, 3, 1),
                                                                                ('Дом', 2, 7, 1),
                                                                                ('Учеба', 0, 10, 1),
                                                                                ('Проекты', 8, 2, 2),
                                                                                ('Личное', 3, 4, 2);

-- Вставка задач
INSERT INTO task (title, completed, task_date, priority_id, category_id, user_id) VALUES
                                                                                      ('Завершить отчет', 1, '2023-12-15 18:00:00', 1, 1, 1),
                                                                                      ('Купить продукты', 1, '2023-12-10 12:00:00', 2, 2, 1),
                                                                                      ('Сделать домашнее задание', 0, '2023-12-05 15:30:00', 3, 3, 1),
                                                                                      ('Подготовить презентацию', 0, '2023-12-20 14:00:00', 1, 4, 2),
                                                                                      ('Позвонить родителям', 1, '2023-12-08 10:00:00', 3, 5, 2),
                                                                                      ('Отправить письмо клиенту', 0, '2023-12-12 09:00:00', 1, 1, 1),
                                                                                      ('Починить кран', 1, '2023-12-01 16:00:00', 2, 2, 1),
                                                                                      ('Прочитать книгу', 0, '2023-12-25 20:00:00', 3, 3, 1);
