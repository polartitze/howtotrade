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
