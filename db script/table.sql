drop table if exists course;
create table course(
    courseid serial primary key,
    coursename character varying(100),
    coursedesc character varying(1000),
	imageUrl character varying(500),
	issaved character varying(1) DEFAULT '0',
	courseOrder integer
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
	quizname character varying(100),
	quizdesc character varying(1000),
	imageUrl character varying(500),
	issaved varchar(1) default '0'
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
	activityid int,
	stepno int not null,
	questiondesc character varying(1000),
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
	activitydesc character varying(1000),
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
);

DROP TABLE IF EXISTS public.candle;
CREATE TABLE IF NOT EXISTS public.candle
(
    candleid serial primary key,
    chartid integer,
    candletime character varying(20),
    openprice decimal,
    highprice decimal,
    lowprice decimal,
    closeprice decimal,
    volume decimal,
    tradecount integer
);

drop table if exists coin;
CREATE TABLE coin
(
    coincode VARCHAR(10) PRIMARY KEY,
    coinname VARCHAR(50),
    coinreturn integer,
    isActive VARCHAR(1)
);

drop table if exists comment;
CREATE TABLE comment
(
    commentid SERIAL NOT NULL PRIMARY KEY,
    description VARCHAR(1000),
    createddate timestamp,
    userid integer,
    topicid integer
);

drop table if exists investmentplanning;
CREATE TABLE IF NOT EXISTS public.investmentplanning
(
	planningid serial primary key,
	userid integer,
	duration character varying(20),
	coinid integer,
	investasiawal character varying(20),
	calctype character varying(20),
	result character varying(255),
	createddate timestamp without time zone,
	coincode character varying(20) DEFAULT '-'::character varying,
	investasiperbulan character varying(20) DEFAULT '-'::character varying
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
    description VARCHAR(10000),
    createddate timestamp,
    authorid integer NOT NULL,
    topicImage varchar(1000) default ''
);

drop table if exists users;
CREATE TABLE public.users
(
    userid SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    userrealname varchar(200) default '-',
    useremail VARCHAR(50),
    userpassword VARCHAR(65),
    userrole integer,
    userstatus VARCHAR(1),
    userImage varchar(1000) default '',
    isverified varchar(1) default '0'
);

drop table if exists calculator;
CREATE TABLE IF NOT EXISTS public.calculator
(
    calculatorid serial primary key,
    userid integer,
    coincode character varying(20) DEFAULT '-',
    calculatortypeid integer,
    duration character varying(20),
    investasiawal character varying(20),
    investasiperbulan character varying(20) DEFAULT '-',
    result character varying(255),
    createddate timestamp without time zone
);

drop table if exists calculatortype;
create table calculatortype(
	calculatortypeid integer primary key,
	calculatortypename character varying(100)
);

drop table if exists helper_course;
CREATE TABLE IF NOT EXISTS helper_course
(
    courseorder integer,
    courseid integer
)