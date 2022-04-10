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
	'https://images.unsplash.com/photo-1531913223931-b0d3198229ee?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1378&q=80'),
(2, 2, 'Fundamental Analysis', 'Mengetes kemampuan pengetahuan mengenai cara membaca laporan keuangan dan kesehatan suatu perusahaan', 
	'https://images.unsplash.com/photo-1531913223931-b0d3198229ee?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1378&q=80')

delete from question;

insert into question
values
(1, 1, 1, 1, 'Quiz 1 Question 1', 1, null, 'Choice 1', 'Choice 2', 'Choice 3', 'Choice 4', 
	'https://canopylab.com/wp-content/uploads/2020/05/Working-with-adaptive-quizzes-A-beginners-guide.jpg'),
(2, 1, 2, 2, 'Quiz 1 Question 2', 1, null, 'Choice 1', 'Choice 2', 'Choice 3', 'Choice 4', 
	'https://canopylab.com/wp-content/uploads/2020/05/Working-with-adaptive-quizzes-A-beginners-guide.jpg');