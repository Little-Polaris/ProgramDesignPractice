show databases ;

create database Home;

use Home;

create table RegObject(
    name varchar(30) comment '姓名',
    id char(11) comment '账号' primary key ,
    gender char(1) comment '性别',
    pwd varchar(50) comment '密码',
    tel varchar(20) comment '联系方式',
    email varchar(30) comment '邮箱'
)comment '注册对象';

create table Reged_user(
    name varchar(30) comment '姓名',
    id char(11) comment '账号' primary key ,
    gender char(1) comment '性别',
    pwd varchar(50) comment '密码',
    tel varchar(20) comment '联系方式',
    email varchar(30) comment '邮箱'
)comment '已注册的普通用户';
create table Administrator(
    name varchar(30) comment '姓名',
    id char(11) comment '账号' primary key ,
    gender char(1) comment '性别',
    pwd varchar(50) comment '密码',
    tel varchar(20) comment '联系方式',
    email varchar(30) comment '邮箱'
)comment '管理员';

create table RoomMessage(
    user_name varchar(20) comment '用户名称',
    user_id varchar(11) comment '用户账号',
    telNum varchar(20) comment '用户联系方式',
    email varchar(30) comment '用户邮箱',
    room_num int primary key comment '房间编号',
    using_date date comment '会议日期',
    starting_time time comment '会议开始时间',
    ending_time time comment '会议结束时间',
    purpose varchar(50) comment '使用会议厅目的'
)comment '会议厅信息';

insert into administrator values ('张三',123456,'男','-1ef523c6b645a65441a91fa80df077c2',123456,123456);
insert into reged_user values ('张三',123456,'男','-1ef523c6b645a65441a91fa80df077c2',123456,123456);
