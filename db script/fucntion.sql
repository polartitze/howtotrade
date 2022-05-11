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
	v_highestscore int;
	v_response character varying (20);
BEGIN

	insert into quiz_enroll(userid, quizid, score, enrolldate)
	values(I_USERID, I_QUIZID, I_SCORE, CURRENT_TIMESTAMP)
	on conflict on constraint quizenroll_pkey
	do
		update set enrolldate = CURRENT_TIMESTAMP;
		
	select score 
	into v_highestscore
	from quiz_enroll where userid = I_USERID and quizid = I_QUIZID;
		
	if (i_score > v_highestscore) then
		update quiz_enroll 
		set score = I_SCORE
		where userid = I_USERID and quizid = I_QUIZID;
	end if;
	
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

CREATE OR REPLACE FUNCTION public.fn_islocked_course(
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
	if exists (select * from quiz_enroll where userid = i_userid and quizid in (select quizid from quiz where courseid = i_courseid-1)) then 
		select count(*) into v_countquestion from question where quizid = 1;
		select score::float / v_countquestion into v_quizscore from quiz_enroll where quizid = 1;

		if (v_quizscore >= 0.75) then 
			return false;
		else
			return true;
		end if;
	else 
		return true;
	end if;
END;
$BODY$;

drop function fn_insert_chart_activity;
CREATE OR REPLACE FUNCTION public.fn_insert_chart_activity(
	i_activityid integer,
	i_chartmasterid integer,
	i_startdate character varying (20),
	i_enddate character varying (20))
    RETURNS integer
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE v_chartid integer;
BEGIN
	insert into chart(activityid, chartname)
		select i_activityid, chartname
		from chart where chartid = i_chartmasterid
	returning chartid into v_chartid;
	
	insert into candle (chartid, candletime, openprice, highprice, lowprice, closeprice, volume, tradecount)
	select v_chartid, candletime, openprice, highprice, lowprice, closeprice, volume, tradecount 
	from candle 
	where chartid = i_chartmasterid and  
		candletime::date >= i_startdate::date and candletime::date <= i_enddate::date;
		
	return v_chartid;
END;
$BODY$;
