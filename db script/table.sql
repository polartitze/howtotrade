create table course(
    courseid serial primary key,
    coursename character varying(50),
    coursedesc character varying(500),
    courseduration time without time zone,
);

create table course_enroll(
    userid integer NOT NULL,
    courseid integer NOT NULL,
    enrolldate timestamp without time zone,
    CONSTRAINT courseenroll_pkey PRIMARY KEY (userid, courseid)
);

create table question(
	questionid serial primary key,
	questiondesc character varying(500),
	quizid int,
	actionid int,
	correctanswer int not null,
	choiceone character varying(500) not null,
	choicetwo character varying(500) not null,
	choicethree character varying(500),
	choicefour character varying(500),
	imageUrl character varying(500)
);

create table activity(
	activityid serial primary key,
	courseid int,
	stepno int,
	activitytypeid int,
	name character varying(100),
	imageUrl character varying(500)
);

create table activity_type(
	id serial primary key,
	name character varying(100)
);