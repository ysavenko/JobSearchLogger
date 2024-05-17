insert into users(username, password, enabled) values('Ievgen', '123', 'Y');
insert into users(username, password, enabled) values('John',   '123', 'Y');
insert into users(username, password, enabled) values('Rick',   '123', 'Y');
set @candidate1 = select username from users where username='Ievgen';
set @candidate2 = select username from users where username='John';
set @candidate3 = select username from users where username='Rick';

insert into job_contact(type, description, candidate, position_id, date) values ('Apply',  'email:hr@nova.com, resume added',   @candidate2, 1, '2024-03-27');
insert into job_contact(type, description, candidate, position_id, date) values ('Reject', 'email:hr@nova.com, resume added',   @candidate2, 1, '2024-03-29');

insert into job_contact(type, description, candidate, position_id, date) values ('Apply',    'email:hr@nova.com, resume added', @candidate2, 2, '2024-04-20');
insert into job_contact(type, description, candidate, position_id, date) values ('Interiew', 'Round 1, phone',                  @candidate2, 2, '2024-04-22');
insert into job_contact(type, description, candidate, position_id, date) values ('Interiew', 'Round 2, onsite',                 @candidate2, 2, '2024-04-26');
insert into job_contact(type, description, candidate, position_id, date) values ('Notice',   'On hold',                         @candidate2, 2, '2024-04-20');