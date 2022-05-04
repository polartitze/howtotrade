package com.skripsi.howtotrade.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skripsi.howtotrade.model.Quiz;
import com.skripsi.howtotrade.service.CourseService;
import com.skripsi.howtotrade.service.QuizService;
import com.skripsi.howtotrade.service.UserService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	UserService userService;
	
	public CourseController() {}

	@ModelAttribute("module")
    public String module() {
        return "course";
    }
	
	@RequestMapping(value = "/all")
	public String getAllCourse(Principal principal, Model model) {
		try {
			int userId = userService.getUserId(principal.getName());
			model.addAttribute("username", principal.getName());
			model.addAttribute("listCourse", courseService.getAllCourse(userId));
			model.addAttribute("listQuiz", quizService.getAllQuiz(userId));
			model.addAttribute("userLogged", "alvin"); //FIXME: when authentication has been set, change into 'userLogged'
			return "course/coursedashboard";
		} catch (Exception e) {
			model.addAttribute("listCourse", courseService.getAllCourse());
			model.addAttribute("listQuiz", quizService.getAllQuiz());
			model.addAttribute("userLogged", "alvin"); //FIXME: when authentication has been set, change into 'userLogged'
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
	
	
	
}
