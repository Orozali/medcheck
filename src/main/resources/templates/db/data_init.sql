insert into service(id, name)
values (1, 'Аллергология'),
       (2, 'Вакцинация'),
       (3, 'Гинекология'),
       (4, 'Кардиология'),
       (5, 'Психтерапия'),
       (6, 'Ревмотология'),
       (7, 'Онкология'),
       (8, 'Эндокринология'),
       (9, 'Урология');

insert into user_info(id, email, password, reset_password_token, roles)
VALUES (1,'ilyazovorozali08@gmail.com', '$2a$12$5OCLf1sLMM/GBmbo10JhOuXuofj/6gTxHjVSgqzxzaTox1hMlVCXu',null,'PATIENT'),
       (2,'laborant@gmail.com', '$2a$12$iMk3UcI9VoECATqSIZhvq.ICHltA255I8ee9UuFJoe.2yNShDjaX.',null,'LABORANT'),
       (3,'admin@gmail.com', '$2a$12$dlNOZ4QUKxi5f/YBySadxO9A/i6iSIj.ilCxVg7fq/UR53iyouQ7O',null,'ADMIN');




