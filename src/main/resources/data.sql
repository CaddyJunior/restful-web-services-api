insert into user_details(id,birth_date,name)
values(10001, current_date(), 'KB');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'TP');

insert into user_details(id,birth_date,name)
values(10003, current_date(), 'NAL');

insert into post(id,description,user_id)
values(20001,'I want to learn AWS', 10001);

insert into post(id,description,user_id)
values(20002,'I want to learn DevOps', 10001);

insert into post(id,description,user_id)
values(20003,'I want to Get AWS Certified', 10002);

insert into post(id,description,user_id)
values(20004,'I want to learn Multi Cloud', 10002);

insert into post(id,description,user_id)
values(20005,'I want to Get Honoured for AWS', 10003);

insert into post(id,description,user_id)
values(20006,'I want to Get Honoured for  Multi Cloud', 10003);