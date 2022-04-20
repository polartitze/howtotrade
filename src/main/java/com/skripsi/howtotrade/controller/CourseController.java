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
	@RequestMapping("/all")
	public String getAllCourse(Model model) {
		model.addAttribute("listCourse", courseService.getAllCourse());
		model.addAttribute("listQuiz", quizService.getAllQuiz());
		model.addAttribute("userLogged", "alvin"); //FIXME: when authentication has been set, change into 'userLogged'
		return "course/coursedashboard";
	}
	
	@RequestMapping("/{courseId}")
	public String getCourseById(Model model, @PathVariable int courseId) {
		model.addAttribute("courseData", courseService.getCourseById(courseId));
		
		return "course/course";
	}
	
	@RequestMapping(value = "/saveProgress/{userId}/{courseId}", method = RequestMethod.POST)
	public String saveProgressCourse(@PathVariable int userId, @PathVariable int courseId) {
		courseService.saveProgress(userId, courseId);
		return "redirect:/course/all";			
	}
	
	@RequestMapping("/quiz/{quizId}")
	public String getQuizById(Model model, @PathVariable int quizId) {
		model.addAttribute("quizData", quizService.getQuizById(quizId));
		return "course/quiz";
	}
	
	@RequestMapping(value="/finish", method = RequestMethod.POST)
	public String finishQuiz(@RequestBody Quiz quiz, Principal principal) {
		
		int userId = userService.getUserId(principal.getName());
		quizService.finishQuiz(quiz.getQuizId(), userId, quiz.getFinalScore());
		return "redirect:/course/all";
	}
	
	
	
}
