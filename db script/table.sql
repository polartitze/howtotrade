drop table if exists course;
create table course(
    courseid serial primary key,
    coursename character varying(50),
    coursedesc character varying(500),
    courseduration time without time zone,
	imageUrl character varying(500)
);

drop table if exists course_enroll;
create table course_enroll(
    userid integer NOT NULL,
    courseid integer NOT NULL,
    enrolldate timestamp without time zone,
    CONSTRAINT courseenroll_pkey PRIMARY KEY (userid, courseid)
);

drop table if exists quiz;
create table quiz(
	quizid serial primary key,
	courseId int,
	quizname character varying(50),
	quizdesc character varying(500),
	imageUrl character varying(500)
);

drop table if exists quiz_enroll;
create table quiz_enroll(
    userid integer NOT NULL,
    quizid integer NOT NULL,
	score integer NOT NULL,
    enrolldate timestamp without time zone,
    CONSTRAINT quizenroll_pkey PRIMARY KEY (userid, quizid)
);

drop table if exists question;
create table question(
	questionid serial primary key,
	quizid int,
	-- activityid int,
	stepno int not null,
	questiondesc character varying(500),
	correctanswer int not null,
	useranswer int,
	choiceone character varying(500) not null,
	choicetwo character varying(500) not null,
	choicethree character varying(500),
	choicefour character varying(500),
	imageUrl character varying(500)
);

drop table if exists activity;
create table activity(
	activityid serial primary key,
	courseid int,
	stepno int,
	activitytypeid int,
	activitydesc character varying(100),
	imageUrl character varying(500),
	isquestion boolean
);

drop table if exists activity_type;
create table activity_type(
	id serial primary key,
	name character varying(100)
);

drop table if exists chart;
CREATE TABLE IF NOT EXISTS chart
(
    chartid serial primary key,
    activityid integer,
    chartname character varying(100)
)

drop table if exists candle;
CREATE TABLE IF NOT EXISTS candle
(
	candleid SERIAL PRIMARY KEY, 
	chartid integer,
	candletime character varying(20),
	openprice integer,
	highprice integer,
	lowprice integer,
	closeprice integer,
	volumebuy float,
	volumesell float,
	tradecount integer
);

drop table if exists coin;
CREATE TABLE coin
(
    coinid serial NOT NULL PRIMARY KEY,
    coincode VARCHAR(10),
    coinname VARCHAR(50),
    coinreturn integer,
    active VARCHAR(1)
);

drop table if exists comment;
CREATE TABLE comment
(
    commentid SERIAL NOT NULL PRIMARY KEY,
    description VARCHAR(500),
    createddate date,
    userid integer,
    topicid integer
);

drop table if exists investmentplanning;
CREATE TABLE investmentplanning
(
    planningid SERIAL NOT NULL PRIMARY KEY,
    userid integer,
    target VARCHAR(50),
    duration integer,
    coinid integer,
    currentbalance integer
);

drop table if exists roles;
CREATE TABLE roles
(
    roleid integer NOT NULL PRIMARY KEY,
    rolename VARCHAR(10)
);

drop table if exists topic;
CREATE TABLE topic
(
    topicid SERIAL NOT NULL PRIMARY KEY,
    topictitle VARCHAR(255),
    description VARCHAR(3000),
    createddate date,
    authorid integer NOT NULL
);

drop table if exists users;
CREATE TABLE public.users
(
    userid SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    useremail VARCHAR(50),
    userpassword VARCHAR(65),
    userrole integer,
    userstatus VARCHAR(1)
);

alter table topic alter column createddate type TIMESTAMP;
alter table comment alter column createddate type TIMESTAMP;

alter table topic add column imagepath varchar(65) default '-';

alter table users add column imagepath varchar(65) default '-';

alter table investmentplanning add column calcType varchar(20);
alter table investmentplanning add column result varchar(255);
alter table investmentplanning add column createddate timestamp;
alter table investmentplanning add column coincode varchar(20) default '-';
alter table investmentplanning alter column duration type varchar(20);
alter table investmentplanning alter column currentbalance type varchar(20);

alter table users add column isverified varchar(1) default '0';

alter table quiz add column isSaved varchar(1) default '0';
alter table course add column isSaved varchar(1) default '0';
alter table course drop column courseduration;

ALTER TABLE INVESTMENTPLANNING RENAME COLUMN CURRENTBALANCE TO INVESTASIAWAL
ALTER TABLE INVESTMENTPLANNING ADD COLUMN INVESTASIPERBULAN VARCHAR(20) default '-'
ALTER TABLE INVESTMENTPLANNING DROP COLUMN TARGET

ALTER TABLE TOPIC ALTER COLUMN DESCRIPTION TYPE VARCHAR(10000)

alter table users add column realname varchar(200) default '-'