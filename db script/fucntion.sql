CREATE OR REPLACE FUNCTION fn_save_course_enroll(
	i_userid integer,
	i_courseid integer)
    RETURNS character varying (50)
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
begin
	insert into course_enroll(userid, courseid, enrolldate)
	values(I_USERID, I_COURSEID, CURRENT_TIMESTAMP)
	on conflict on constraint courseenroll_pkey
	do
		update set enrolldate = CURRENT_TIMESTAMP;
		
	return current_timestamp;
end;
$BODY$;

CREATE OR REPLACE FUNCTION public.fn_save_quiz_enroll(
	i_userid integer,
	i_quizid integer,
	i_score integer)
    RETURNS character varying
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
	v_maxattempt int;
	v_response character varying (20);
BEGIN

	--check sudah berapa kali coba
	select max(attemptno) into v_maxattempt from quiz_enroll 
	where userid = i_userid and quizid = i_quizid;
	
	--insert percobaan selanjutnya
	insert into quiz_enroll
	values (i_userid, i_quizid, v_maxattempt + 1, i_score, current_timestamp);
	
	return current_timestamp;
END;
$BODY$;

CREATE OR REPLACE FUNCTION public.fn_islocked_quiz(
	i_userid integer,
	i_quizid integer)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
BEGIN
	if exists (select * from course_enroll where userid = i_userid and courseid in (select courseid from quiz where quizid = i_quizid)) then 
		return false;
	else
		return true;
	end if;
END;
$BODY$;

CREATE OR REPLACE FUNCTION fn_islocked_course
(
	i_userid integer,
	i_courseid integer)
    RETURNS boolean
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
	v_countquestion integer;
	v_quizscore float;
BEGIN
	if(i_courseid = 1) then
		return false;
	end if;
	
	select count(*) into v_countquestion from question where quizid = 1;
	
	select score::float / v_countquestion into v_quizscore from quiz_enroll where quizid = 1;
	
	if (v_quizscore >= 0.75) then 
		return false;
	else
		return true;
	end if;
END;
$BODY$;