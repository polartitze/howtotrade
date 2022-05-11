delete from course;
insert into course
values 
(1, 'Technical Analysis', 'Mempelajari cara membaca teknikal grafik dan pola-pola berulang yang seringkali muncul pada saat membaca grafik candlestick',
	'https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80', '1'),
(2, 'Fundamental Analysis', 'Mempelajari cara membaca laporan keuangan dan menilai kesehatan suatu perusahaan',
	'https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80', '1');

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
(3, 1, 3, 1, 'Belajar membaca grafik BTC/USDT', 'https://wallpaperaccess.com/full/4040865.jpg', 'n'),
(4, 1, 4, 1, 'Belajar pola bullish hammer',	NULL, 'n');

delete from chart;
insert into chart
values
	(1, 3, 'BTC/USDT'),
	(2, 4, 'BNB/USDT');

delete from candle;
insert into candle
values 
(1, 6, '3/25/2022', 43991, 43997, 43854, 43897, 21733),
(2, 6, '3/24/2022', 42882, 44220, 42560, 43991, 1202541),
(3, 6, '3/23/2022', 42364, 43025, 41751, 42882, 916671),
(4, 6, '3/22/2022', 41002, 43361, 40875, 42364, 1367343),
(5, 6, '3/21/2022', 41262, 41544, 40467, 41002, 947206),
(6, 6, '3/20/2022', 42201, 42296, 40911, 41262, 799695),
(7, 6, '3/19/2022', 41757, 42400, 41499, 42201, 801197),
(8, 6, '3/18/2022', 40917, 42325, 40135, 41757, 1065054),
(9, 6, '3/17/2022', 41114, 41478, 40500, 40917, 935784),
(10, 6, '3/16/2022', 39280, 41718, 38828, 41114, 2051837),
(11, 6, '3/15/2022', 39671, 39887, 38098, 39280, 1060517),
(12, 6, '3/14/2022', 37777, 39947, 37555, 39671, 1114899),
(13, 6, '3/13/2022', 38807, 39310, 37578, 37777, 841844),
(14, 6, '3/12/2022', 38729, 39486, 38660, 38807, 671733),
(15, 6, '3/11/2022', 39422, 40236, 38223, 38729, 1252571),
(16, 6, '3/10/2022', 41941, 42039, 38539, 39422, 1512384),
(17, 7, '5/31/2021', 326, 356, 307, 353, 5617362.0, 2430665),
(18, 7, '5/30/2021', 304, 338, 291, 326, 5630652.0, 2535067),
(19, 7, '5/29/2021', 329, 348, 296, 304, 6285914.0, 2658465),
(20, 7, '5/28/2021', 370, 374, 312, 329, 7554255.0, 3260665),
(21, 7, '5/27/2021', 379, 388, 340, 370, 5718913.0, 2625852),
(22, 7, '5/26/2021', 343, 389, 336, 379, 6414380.0, 2902919),
(23, 7, '5/25/2021', 345, 376, 307, 343, 8856765.0, 3525048),
(24, 7, '5/24/2021', 261, 350, 257, 345, 9630203.0, 3386476),
(25, 7, '5/23/2021', 300, 314, 211, 261, 13121033.0, 4212459),
(26, 7, '5/22/2021', 326, 336, 285, 300, 6478250.0, 2840985),
(27, 7, '5/21/2021', 391, 416, 274, 326, 8510825.0, 3670938),
(28, 7, '5/20/2021', 334, 423, 284, 391, 9016040.0, 3815160),
(29, 7, '5/19/2021', 508, 517, 250, 334, 12232300.0, 5287533),
(30, 7, '5/18/2021', 512, 535, 496, 508, 3063473.0, 1867718),
(31, 7, '5/17/2021', 564, 564, 484, 512, 4903574.0, 2558582),
(32, 7, '5/16/2021', 564, 607, 535, 564, 2956628.0, 1823175),
(33, 7, '5/15/2021', 604, 612, 560, 564, 2361015.0, 1573706),
(34, 7, '5/14/2021', 571, 616, 566, 603, 2583123.0, 1607597),
(35, 7, '5/13/2021', 594, 633, 527, 571, 6731897.0, 3468565),
(36, 7, '5/12/2021', 671, 685, 580, 594, 2988759.0, 1752479),
(37, 7, '5/11/2021', 632, 673, 622, 671, 2466914.0, 1682451),
(38, 7, '5/10/2021', 662, 693, 585, 632, 4579766.0, 2335910),
(39, 7, '5/9/2021', 646, 680, 630, 662, 3424034.0, 1863735),
(40, 7, '5/8/2021', 625, 659, 619, 646, 2655923.0, 1611121),
(41, 7, '5/7/2021', 634, 645, 612, 625, 2400711.0, 1547943),
(42, 7, '5/6/2021', 652, 655, 621, 634, 2558175.0, 1526688),
(43, 7, '5/5/2021', 610, 658, 605, 652, 3253407.0, 1714883),
(44, 7, '5/4/2021', 677, 680, 605, 610, 6042418.0, 2790458),
(45, 7, '5/3/2021', 622, 682, 622, 677, 4826440.0, 2285486),
(46, 7, '5/2/2021', 621, 630, 596, 622, 3226655.0, 1655023),
(47, 7, '5/1/2021', 624, 646, 609, 621, 3612503.0, 1771933);

DELETE FROM ROLES;
INSERT INTO roles(roleid, rolename) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_PRO');

DELETE FROM COIN;
INSERT INTO COIN VALUES ('BTC','BITCOIN',20,'1'),
('ETH','ETHEREUM',10,'1'),
('TKO','TOKOCRYTO',5,'1'),
('DOGE','DOGECOIN',7,'1');

insert into calculatortype
values 
	(1, 'Total Investasi (Tabung bulanan)'),
	(2, 'Total Investasi (Tabung sekali di awal)');