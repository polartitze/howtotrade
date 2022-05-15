package com.skripsi.howtotrade.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skripsi.howtotrade.model.Course;
import com.skripsi.howtotrade.model.Users;
import com.skripsi.howtotrade.service.CourseService;
import com.skripsi.howtotrade.service.QuizService;
import com.skripsi.howtotrade.service.UserService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private UserService userService;
	
	public CourseController() {}

	@ModelAttribute("module")
    private String module() {
        return "course";
    }
	
	@RequestMapping(value = "/all")
	public String getAllCourse(Principal principal, Model model) {
		try {
			Users user = userService.findUserAccount(principal.getName());
			// int userId = userService.getUserId(principal.getName());
			int userId = user.getUserId();
			model.addAttribute("username", user.getUserName() );
			model.addAttribute("realname", user.getUserRealName());
			model.addAttribute("listCourse", courseService.getAllCourse(userId));
			model.addAttribute("listQuiz", quizService.getAllQuiz(userId));
			return "course/coursedashboard";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("listCourse", courseService.getAllCourse());
			model.addAttribute("listQuiz", quizService.getAllQuiz());
			return "course/coursedashboard";
		}
	}
	
	@RequestMapping("/{courseId}")
	public String getCourseById(Model model, @PathVariable int courseId) {
		model.addAttribute("courseData", courseService.getCourseById(courseId));
		
		return "course/course";
	}
	
	@RequestMapping(value = "/finish/{courseId}", method = RequestMethod.GET)
	public String saveProgressCourse(Principal principal, @PathVariable int courseId) {
		int userId = userService.getUserId(principal.getName());
		courseService.saveProgress(userId, courseId);
		return "redirect:/course/all";			
	}
	
	@RequestMapping("/quiz/{quizId}")
	public String getQuizById(Model model, @PathVariable int quizId) {
		model.addAttribute("quizData", quizService.getQuizById(quizId));
		return "course/quiz";
	}
	
	@RequestMapping(value="/quiz/finish/{quizId}/{score}", method = RequestMethod.GET)
	public String saveProgressQuiz(Principal principal, @PathVariable int quizId, @PathVariable int score) {
		int userId = userService.getUserId(principal.getName());
		quizService.saveProgress(userId, quizId, score);
		
		return "redirect:/course/all";
	}
	
	// @RequestMapping(value="/certificate", method = RequestMethod.GET)
	// public String certificatePage(Model model,
	// 							@RequestParam ("uName") String userName,
	// 							@RequestParam ("cid") String cid,
	// 							@RequestParam ("qid") String qid) {
	@RequestMapping(value="/certificate/{userName}/{cid}/{qid}", method = RequestMethod.GET)
	public String certificatePage(Model model,
								@PathVariable  String userName,
								@PathVariable  String cid,
								@PathVariable String qid) {
		int courseId = Integer.parseInt(cid);
		int quizId = Integer.parseInt(qid);
		int userId = userService.getUserId(userName);
		boolean isPassCourseAndQuiz = quizService.isPassCourseAndQuiz(userName, courseId, quizId);
		System.out.println("isPassCourseAndQuiz: "+isPassCourseAndQuiz);
		if(isPassCourseAndQuiz){
			model.addAttribute("user", userService.findUserAccount(userName));
			model.addAttribute("course", courseService.getCourseById(courseId));
			model.addAttribute("quiz", quizService.getQuizHighestScore(userId, quizId));
			return "course/certificate"; //go to certificate page
		}
		return "redirect:/user/add";		//return to register ?
	}

	@ResponseBody
	@RequestMapping(value="/get-coursename", method = RequestMethod.GET)
	public String getCourseName(@RequestParam("id") String id) {
		int courseId = Integer.parseInt(id);
		Course course = courseService.getCourseById(courseId);

		return course.getCourseName();
	}
}
