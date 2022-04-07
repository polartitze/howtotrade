drop table course;
create table course(
    courseid serial primary key,
    coursename character varying(50),
    coursedesc character varying(500),
    courseduration time without time zone,
	imageUrl character varying(500)
);

drop table course_enroll;
create table course_enroll(
    userid integer NOT NULL,
    courseid integer NOT NULL,
    enrolldate timestamp without time zone,
    CONSTRAINT courseenroll_pkey PRIMARY KEY (userid, courseid)
);

drop table quiz;
create table quiz(
	quizid serial primary key,
	courseId int,
	quizname character varying(50),
	quizdesc character varying(500)
);

drop table quiz_enroll;
create table quiz_enroll(
    userid integer NOT NULL,
    quizid integer NOT NULL,
	attemptno integer NOT NULL,
	score integer NOT NULL,
    enrolldate timestamp without time zone,
    CONSTRAINT quizenroll_pkey PRIMARY KEY (userid, quizid, attemptno)
);

drop table question;
create table question(
	questionid serial primary key,
	stepno int not null,
	questiondesc character varying(500),
	quizid int,
	actionid int,
	correctanswer int not null,
	useranswer int not null,
	choiceone character varying(500) not null,
	choicetwo character varying(500) not null,
	choicethree character varying(500),
	choicefour character varying(500),
	imageUrl character varying(500)
);

drop table activity;
create table activity(
	activityid serial primary key,
	courseid int,
	stepno int,
	activitytypeid int,
	name character varying(100),
	imageUrl character varying(500)
);

drop table activity_type;
create table activity_type(
	id serial primary key,
	name character varying(100)
);

drop table coin;
CREATE TABLE coin
(
    coinid serial NOT NULL PRIMARY KEY,
    coincode VARCHAR(10),
    coinname VARCHAR(50),
    coinreturn integer,
    active VARCHAR(1)
);

drop table comment;
CREATE TABLE comment
(
    commentid SERIAL NOT NULL PRIMARY KEY,
    description VARCHAR(500),
    createddate date,
    userid integer,
    topicid integer
);

drop table investmentplanning;
CREATE TABLE investmentplanning
(
    planningid SERIAL NOT NULL PRIMARY KEY,
    userid integer,
    target VARCHAR(50),
    duration integer,
    coinid integer,
    currentbalance integer
);

drop table roles;
CREATE TABLE roles
(
    roleid integer NOT NULL PRIMARY KEY,
    rolename VARCHAR(10)
);

drop table topic;
CREATE TABLE topic
(
    topicid SERIAL NOT NULL PRIMARY KEY,
    topictitle VARCHAR(255),
    description VARCHAR(3000),
    createddate date,
    authorid integer NOT NULL
);

drop table users;
CREATE TABLE public.users
(
    userid SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    useremail VARCHAR(50),
    userpassword VARCHAR(50),
    userrole integer,
    userstatus VARCHAR(1)
);