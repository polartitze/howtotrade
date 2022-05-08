delete from course;
insert into course
values 
(1, 'Technical Analysis', 'Mempelajari cara membaca teknikal grafik dan pola-pola berulang yang seringkali muncul pada saat membaca grafik candlestick', '00:30:00', 
	'https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80'),
(2, 'Fundamental Analysis', 'Mempelajari cara membaca laporan keuangan dan menilai kesehatan suatu perusahaan', '00:30:00', 
	'https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80');

delete from quiz;
insert into quiz
values 
(1, 1, 'Technical Analysis', 'Mempelajari cara membaca teknikal grafik dan pola-pola berulang yang seringkali muncul pada saat membaca grafik candlestick', 
	'https://images.unsplash.com/photo-1531913223931-b0d3198229ee?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1378&q=80','1'),
(2, 2, 'Fundamental Analysis', 'Mengetes kemampuan pengetahuan mengenai cara membaca laporan keuangan dan kesehatan suatu perusahaan', 
	'https://images.unsplash.com/photo-1531913223931-b0d3198229ee?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1378&q=80','1');
	
delete from question;
insert into question
values
(1, 1, 1, 1, 'Quiz 1 Question 1', 1, null, 'Choice 1', 'Choice 2', 'Choice 3', 'Choice 4', 
	'https://canopylab.com/wp-content/uploads/2020/05/Working-with-adaptive-quizzes-A-beginners-guide.jpg'),
(2, 1, 2, 2, 'Quiz 1 Question 2', 1, null, 'Choice 1', 'Choice 2', 'Choice 3', 'Choice 4', 
	'https://canopylab.com/wp-content/uploads/2020/05/Working-with-adaptive-quizzes-A-beginners-guide.jpg'),
(3, 1, 3, 3, 'Saham apakah ini?', 2, null, 'BSML', 'DEWA', 'TAMA', 'GOTO',  
	'https://facsekuritas.co.id/storage/media/original/dewa.jpg');

delete from activity_type;
insert into activity_type 
values (1, 'chart'), (2, 'picture'), (3, 'text');

delete from activity;
INSERT INTO public.activity
VALUES 
(1, 1, 1, 2, 'Halo semua, selamat datang di howtotrade :D', 'https://images.unsplash.com/photo-1600577916048-804c9191e36c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1032&q=80', 'y'),
(2, 1, 2, 2, 'Pada course kali ini kita akan mempelajari mengenai analisa teknikal dan cara membaca chart', 'https://wallpaperaccess.com/full/4040865.jpg', 'n'),
(3, 1, 3, 1, 'Belajar membaca grafik BTC/USDT', 'https://wallpaperaccess.com/full/4040865.jpg', 'n');

delete from chart;
insert into chart
values(1, 3, 'BTC/USDT');

delete from candle;
insert into candle
values 
(1, 1, '3/25/2022', 43991, 43997, 43854, 43897, 822, 36123620, 21733),
(2, 1, '3/24/2022', 42882, 44220, 42560, 43991, 56195, 2436258224, 1202541),
(3, 1, '3/23/2022', 42364, 43025, 41751, 42882, 40828, 1725787171, 916671),
(4, 1, '3/22/2022', 41002, 43361, 40875, 42364, 59454, 2525975508, 1367343),
(5, 1, '3/21/2022', 41262, 41544, 40467, 41002, 39426, 1618789548, 947206),
(6, 1, '3/20/2022', 42201, 42296, 40911, 41262, 30653, 1273880705, 799695),
(7, 1, '3/19/2022', 41757, 42400, 41499, 42201, 29067, 1216709156, 801197),
(8, 1, '3/18/2022', 40917, 42325, 40135, 41757, 45408, 1862337033, 1065054),
(9, 1, '3/17/2022', 41114, 41478, 40500, 40917, 37189, 1521083190, 935784),
(10, 1, '3/16/2022', 39280, 41718, 38828, 41114, 88120, 3552563766, 2051837),
(11, 1, '3/15/2022', 39671, 39887, 38098, 39280, 46015, 1796571984, 1060517),
(12, 1, '3/14/2022', 37777, 39947, 37555, 39671, 46945, 1821922588, 1114899),
(13, 1, '3/13/2022', 38807, 39310, 37578, 37777, 32791, 1268441605, 841844),
(14, 1, '3/12/2022', 38729, 39486, 38660, 38807, 24034, 939663387, 671733),
(15, 1, '3/11/2022', 39422, 40236, 38223, 38729, 59018, 2305825016, 1252571),
(16, 1, '3/10/2022', 41941, 42039, 38539, 39422, 71950, 2853792081, 1512384);

DELETE FROM ROLES;
INSERT INTO roles(roleid, rolename) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_PRO');

DELETE FROM COIN;
INSERT INTO COIN VALUES ('BTC','BITCOIN',20,'1'),
('ETH','ETHEREUM',10,'1'),
('TKO','TOKOCRYTO',5,'1'),
('DOGE','DOGECOIN',7,'1')

insert into calculatortype 
values 
	(1, 'Total Investasi (Tabung bulanan)'),
	(2, 'Total Investasi (Tabung sekali di awal)')