package com.skripsi.howtotrade.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.skripsi.howtotrade.model.Activity;
import com.skripsi.howtotrade.model.Course;
import com.skripsi.howtotrade.model.Question;
import com.skripsi.howtotrade.model.Quiz;
import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.CourseService;
import com.skripsi.howtotrade.service.QuizService;
import com.skripsi.howtotrade.service.UserService;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pro")
public class ExpertController {
    @Autowired
	private CourseService courseService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private UserService userService;

	@ModelAttribute("module")
	public String module(){
		return "managecourse";
	}
	//TAMBAH KUIS
	@RequestMapping(value = "/add-quiz", method = RequestMethod.GET)
	public String addQuiz(Model model) {
		model.addAttribute("listCourse", courseService.getAllCourseName());
		model.addAttribute("addQuiz", new Quiz());

		return "expert/addquiz";
	}

	@RequestMapping(value = "/add-quiz-save", method = RequestMethod.POST)
	public String addQuizSave(Quiz quiz) {
		try {
			quizService.addQuiz(quiz.getCourseId(), quiz.getQuizName(), quiz.getQuizDesc(), quiz.getImageUrl());
			System.out.println("Insert new quiz success!");
            int latestQuizId =  quizService.getLatestQuizId();
			return "redirect:/pro/add-question/"+String.valueOf(latestQuizId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Insert new quiz failed!");
			return "redirect:/pro/add-quiz?error=true";
		}
	}


	@RequestMapping(value = "/add-question/{quizId}", method = RequestMethod.GET)
	public String addQuestion(Model model, @PathVariable int quizId) {
		model.addAttribute("quizId", quizId);
		model.addAttribute("count", quizService.countListQuestion(quizId));
		model.addAttribute("listQuestion", quizService.getAllQuizQuestion(quizId));
		model.addAttribute("addQuestion", new Question());
		return "expert/addquestion";
	}

	@RequestMapping(value = "/add-question-save", method = RequestMethod.POST)
	public String addQuestionSave(Question question) {
        int latestQuizId =  quizService.getLatestQuizId();
		int latestStepNo = quizService.getLatestStepNo(latestQuizId);
		try {
			quizService.addQuestion(latestQuizId, latestStepNo+1, question.getQuestionDesc(), question.getCorrectAnswer(), question.getChoiceOne(), question.getChoiceTwo(), question.getChoiceThree(), question.getChoiceFour(), question.getImageUrl());
			System.out.println("Insert new question success!");
			return "redirect:/pro/add-question/"+String.valueOf(latestQuizId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Insert new question failed!");
			return "redirect:/pro/add-question/"+String.valueOf(latestQuizId);
		}
	}

    
	@RequestMapping(value = "/delete-question/{questionId}", method = RequestMethod.GET)
	public String deleteQuestion(@PathVariable int questionId) {
        quizService.deleteQuestion(questionId);
        int latestQuizId =  quizService.getLatestQuizId();
        return "redirect:/pro/add-question/"+String.valueOf(latestQuizId);
	}

    @RequestMapping(value = "/save-all-quiz/{quizId}", method = RequestMethod.GET)
    public String saveAllQuiz(@PathVariable int quizId) {
        //LAST SAVE BEFORE
        quizService.saved(quizId);
        return "redirect:/";
    }

	//TAMBAH COURSE
	@RequestMapping(value = "/add-course", method = RequestMethod.GET)
	public String addCourse(Model model) {
		model.addAttribute("addCourse", new Course());
		return "expert/addcourse";
	}

	@RequestMapping(value = "/add-course-save", method = RequestMethod.POST)
	public String addCourseSave(Course course) {
		// @RequestParam(value = "image", required = false) MultipartFile multipartFile
		try {
			courseService.addCourse(course.getCourseName(), course.getCourseDesc(), course.getImageUrl());
			System.out.println("Insert new coourse success!");
			int latestCourseId = courseService.getLatestCourseId();
			return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Insert new course failed!");
			return "redirect:/pro/add-course?error=true";
		}
	}

	@RequestMapping(value = "/add-activity/{courseId}", method = RequestMethod.GET)
	public String addActivity(Model model, @PathVariable int courseId) {
		
		model.addAttribute("count", courseService.countListActivity(courseId));
		model.addAttribute("courseId", courseId);
		model.addAttribute("listActivityType", courseService.getAllActivityType());
		model.addAttribute("listChart", courseService.getChartMaster());
		// model.addAttribute("listActivity", courseService.getAllCourseActivityMap(courseId));
		model.addAttribute("listActivity", courseService.getCourseActivity(courseId));
		model.addAttribute("addActivity", new Activity());

		return "expert/addactivity";
	}
	
	@RequestMapping(value = "/add-activity-save", method = RequestMethod.POST)
	public String addActivitySave(Activity activity, @RequestParam(value = "image", required = false) MultipartFile multipartFile) {
        int latestCourseId =  courseService.getLatestCourseId();
		int latestStepNo = courseService.getLatestStepNo(latestCourseId);

		try {
			int activityId = courseService.addActivity(latestCourseId, latestStepNo+1, activity.getActivityTypeId(), activity.getActivityDesc(), activity.getImageUrl(), activity.getIsQuestion());
			if(activity.getActivityTypeId() == 1) { //chart
				//'05/12/2022 - 05/12/2022'
				String [] parts = activity.getDateRange().split(" - ");
				String startDate = parts[0];
				String endDate = parts[1];
				courseService.saveActivityChart(activityId, activity.getChartMasterId(), startDate, endDate);
			}
			
			if(activity.getIsQuestion()){
				//activity id harus sesuai dgn activity yg terbaru
				courseService.addActivityQuestion(activityId, 0, activity.getQuestion().getQuestionDesc(), activity.getQuestion().getCorrectAnswer(), activity.getQuestion().getChoiceOne(), activity.getQuestion().getChoiceTwo(), activity.getQuestion().getChoiceThree(), activity.getQuestion().getChoiceFour());
			}
			System.out.println("Insert new Activity Question success!");
			System.out.println("Insert new Activity success!");
			return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Insert new Activity failed!");
			return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
		}
	}

	@RequestMapping(value = "/delete-activity/{activityId}", method = RequestMethod.GET)
	public String deleteActivity(@PathVariable int activityId) {
        courseService.deleteActivity(activityId);
        int latestCourseId =  courseService.getLatestCourseId();
        return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
	}

	@RequestMapping(value = "/save-all-course/{courseId}", method = RequestMethod.GET)
    public String saveAllCourse(@PathVariable int courseId) {
        //LAST SAVE BEFORE
		int getLatestCourseOrder = courseService.getLatestCourseOrder(courseId);
        courseService.saved(courseId, getLatestCourseOrder + 1);
        return "redirect:/pro/course/all";
    }
	
	
	
	//MANAGE COURSE
	@RequestMapping(value = "/course/all", method = RequestMethod.GET)
    public String getAllCourse(Model model, Principal principal) {
		try {
			Users user = userService.findUserAccount(principal.getName());
			int userId = userService.getUserId(principal.getName());
			model.addAttribute("username", user.getUserName() );
			model.addAttribute("realname", user.getUserRealName());
			model.addAttribute("listCourse", courseService.getAllCourse(userId));
			model.addAttribute("listQuiz", quizService.getAllQuiz(userId));

			List<Integer> listOrder = new ArrayList();
			List<Course> list = courseService.getAllCourse(userId);
			for (int index = 0; index < list.size(); index++) {
				listOrder.add(index+1);
			}
			model.addAttribute("listOrder", listOrder);
			
			return "expert/managecourse";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("listCourse", courseService.getAllCourse());
			model.addAttribute("listQuiz", quizService.getAllQuiz());
			return "expert/managecourse";
		}
    }

	@RequestMapping(value = "/course/delete/{courseId}", method = RequestMethod.GET)
    public String deleteCourseById(@PathVariable int courseId) {
        courseService.deleteCourseById(courseId);
        return "redirect:/pro/course/all";
    }
	
	@RequestMapping("/course/{courseId}")
	public String getCourseById(Model model, @PathVariable int courseId) {
		model.addAttribute("courseData", courseService.getCourseById(courseId));
		return "expert/viewcourse"; 
	}

	
	@RequestMapping(value = "/quiz/delete/{quizId}", method = RequestMethod.GET)
	public String deleteQuizById(@PathVariable int quizId) {
		quizService.deleteQuizById(quizId);
		System.out.println("test");
		return "redirect:/pro/course/all";
	}
	
	@RequestMapping("/course/quiz/{quizId}")
	public String getQuizById(Model model, @PathVariable int quizId) {
		model.addAttribute("quizData", quizService.getQuizById(quizId));
		return "expert/viewquiz"; 
	}

	@RequestMapping("/course/change-order/{courseId}/{order}")
	public String changeCourseOrder(Model model, @PathVariable int courseId, @PathVariable int order) {
		courseService.changeOrder(order, courseId);		
		return "redirect:/pro/course/all";
	}

}
