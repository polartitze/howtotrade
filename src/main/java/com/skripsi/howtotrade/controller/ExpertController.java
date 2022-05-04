package com.skripsi.howtotrade.controller;


import java.io.IOException;

import com.skripsi.howtotrade.model.Activity;
import com.skripsi.howtotrade.model.Course;
import com.skripsi.howtotrade.model.Question;
import com.skripsi.howtotrade.model.Quiz;
import com.skripsi.howtotrade.service.CourseService;
import com.skripsi.howtotrade.service.QuizService;
import com.skripsi.howtotrade.utility.Constant;
import com.skripsi.howtotrade.utility.FileUploadUtil;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pro")
public class ExpertController {
    @Autowired
	private CourseService courseService;
	
	@Autowired
	private QuizService quizService;

	@RequestMapping(value = "/add-quiz", method = RequestMethod.GET)
	public String addQuiz(Model model) {
		model.addAttribute("listCourse", courseService.getAllCourseName());
		model.addAttribute("addQuiz", new Quiz());

		return "expert/addquiz";
	}

	@RequestMapping(value = "/add-quiz-save", method = RequestMethod.POST)
	public String addQuizSave(Quiz quiz, @RequestParam("image") MultipartFile multipartFile) {
        String filename = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = Constant.QUIZ_IMAGE_PATH + quiz.getCourseId();   //karna quiz belum terbentuk idnya
        
        if(filename == null || "".equals(filename)){
            quiz.setImageUrl("-");
        } 
        else{
            quiz.setImageUrl(filename);
            try {
                FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
                
            } catch (IOException exception) {
                System.out.println("File uploaded with error");
                exception.printStackTrace();
            }
        }

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
	public String addQuestionSave(Question question, @RequestParam("image") MultipartFile multipartFile) {
        String filename = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = Constant.QUESTION_IMAGE_PATH +question.getQuizId();
        
        if(filename == null || "".equals(filename)){
            question.setImageUrl("-");
        } 
        else{
            question.setImageUrl(filename);
            try {
                FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
                
            } catch (IOException exception) {
                System.out.println("File uploaded with error");
                exception.printStackTrace();
            }
        }

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


	@RequestMapping(value = "/add-course", method = RequestMethod.GET)
	public String addCourse(Model model) {
		model.addAttribute("addCourse", new Course());
		return "expert/addcourse";
	}

	@RequestMapping(value = "/add-course-save", method = RequestMethod.POST)
	public String addCourseSave(Course course, @RequestParam("image") MultipartFile multipartFile) {
		String filename = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = Constant.COURSE_IMAGE_PATH + course.getCourseName();
        
        if(filename == null || "".equals(filename)){
            course.setImageUrl("-");
        } 
        else{
            course.setImageUrl(filename);
            try {
                FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
                
            } catch (IOException exception) {
                System.out.println("File uploaded with error");
                exception.printStackTrace();
            }
        }

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
		System.out.println(courseService.getAllCourseActivityMap(courseId));
		model.addAttribute("listActivity", courseService.getAllCourseActivityMap(courseId)); 
		model.addAttribute("addActivity", new Activity());
		// model.addAttribute("addQuestion", new Question());

		return "expert/addactivity";
	}

	@RequestMapping(value = "/add-activity-save", method = RequestMethod.POST)
	public String addActivitySave(Activity activity, @RequestParam("image") MultipartFile multipartFile) {
        String filename = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = Constant.ACTIVITY_IMAGE_PATH +activity.getCourseId();
        
        if(filename == null || "".equals(filename)){
            activity.setImageUrl("-");
        } 
        else{
            activity.setImageUrl(filename);
            try {
                FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
                
            } catch (IOException exception) {
                System.out.println("File uploaded with error");
                exception.printStackTrace();
            }
        }

        int latestCourseId =  courseService.getLatestCourseId();
		int latestStepNo = courseService.getLatestStepNo(latestCourseId);
        int latestActivityId =  courseService.getLatestActivityId();

		try {
			if(activity.isIsquestion()){
				courseService.addActivityQuestion(latestActivityId, 0, activity.getQuestion().getQuestionDesc(), activity.getQuestion().getCorrectAnswer(), activity.getQuestion().getChoiceOne(), activity.getQuestion().getChoiceTwo(), activity.getQuestion().getChoiceThree(), activity.getQuestion().getChoiceFour());
			}
			System.out.println("Insert new ActivityQuestion success!");
			courseService.addActivity(latestCourseId, latestStepNo+1, activity.getActivityTypeId(), activity.getActivityDesc(), activity.getImageUrl(), activity.isIsquestion());
			System.out.println("Insert new Activity success!");
			return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			System.out.println("Insert new Activity failed!");
			return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
		}
	}

	//khusus insert dari activity
	// @ResponseBody
	// @RequestMapping(value = "/activity/add-question-save", method = RequestMethod.POST)
	// public void addActivityQuestionSave(Question question) {
    //     int latestActivityId =  courseService.getLatestActivityId();
	// 	// int latestStepNo = courseService.getLatestStepNo(latestQuizId);
	// 	//GAK BUTUH STEP NO, KARNA ACTIVITY CUMAN PUNYA 1 QUESTION
	// 	try {
	// 		courseService.addActivityQuestion(latestActivityId, 0, question.getQuestionDesc(), question.getCorrectAnswer(), question.getChoiceOne(), question.getChoiceTwo(), question.getChoiceThree(), question.getChoiceFour());
	// 		// System.out.println("Insert new question success!");
	// 		// return "redirect:/pro/add-question/"+String.valueOf(latestQuizId);
	// 	} catch (PersistenceException e) {
	// 		e.printStackTrace();
	// 		// System.out.println("Insert new question failed!");
	// 		// return "redirect:/pro/add-question/"+String.valueOf(latestQuizId);
	// 	}
	// }

	@RequestMapping(value = "/delete-activity/{activityId}", method = RequestMethod.GET)
	public String deleteActivity(@PathVariable int activityId) {
        courseService.deleteActivity(activityId);
        int latestCourseId =  courseService.getLatestCourseId();
        return "redirect:/pro/add-activity/"+String.valueOf(latestCourseId);
	}

	@RequestMapping(value = "/save-all-course/{courseId}", method = RequestMethod.GET)
    public String saveAllCourse(@PathVariable int courseId) {
        //LAST SAVE BEFORE
        courseService.saved(courseId);
        return "redirect:/";
    }
}
