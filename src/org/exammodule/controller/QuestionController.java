package org.exammodule.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.exception.AllQuestionsAnsweredException;
import org.exammodule.form.QuestionFormBean;
import org.exammodule.handler.Constants;
import org.exammodule.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class QuestionController {
	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView initForm(HttpSession session) {
		logger.debug("Received request to show question page");
		
		String userName = (String) session.getAttribute("USERNAME");
		Integer questionOrder = (Integer) session
				.getAttribute("QUESTION_ORDER");
		logger.debug(userName + " User logged in");
		QuestionFormBean question = null;
		ModelAndView mav = new ModelAndView();
		try {
			question = questionService.getAQuestion(questionOrder);
		} catch (AllQuestionsAnsweredException e) {
			mav.setViewName("questionerr");
			mav.addObject("message",
					e.getMessage());
			e.printStackTrace();
			return mav;
		}catch (Exception e) {
			mav.setViewName("questionerr");
			mav.addObject("message",
					"Something went wrong, please try again later!");
			return mav;
		}
		ModelAndView model = new ModelAndView("questionpage");
		model.addObject("optionList", question.getOptionList());
		model.addObject("wholeQuestion", question.getWholeQuestion());
		model.addObject("command", question);
		return model;
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public ModelAndView questionFormSubmit(
			@ModelAttribute("command") QuestionFormBean question,
			HttpSession session) {
		logger.debug("Question page subimtted");
		ModelAndView mav = new ModelAndView();
		String userName = (String) session.getAttribute("USERNAME");
		String access = (String) session.getAttribute("ACCESS_LEVEL");
		Integer questionOrder = (Integer) session
				.getAttribute("QUESTION_ORDER");
		
		long testCurrentTime=new Date().getTime()/1000;
		
		long differenceTime=testCurrentTime-(long)session.getAttribute("TEST_START_TIME");
		long remainingTime=Constants.TEST_TIME-differenceTime;
		if (session != null) {
            session.setAttribute("TIME", remainingTime);
        }

		if (access == "ROLE_USER_VIDEO") {
			try {
				mav.addObject("feedbackVideoLink",questionService.getFeedbackVideoLink(question));
				mav.setViewName("videofeedbackpage");
			}catch (Exception e) {
				mav.setViewName("questionerr");
				mav.addObject("message",
						"Something went wrong, please try again later!");
				e.printStackTrace();
			}

		} else if (access == "ROLE_USER_IMAGE") {
			try {
				mav.addObject("feedbackImageName",questionService.getFeedbackImage((question)));
				mav.setViewName("imagefeedbackpage");
			} catch (Exception e) {
				mav.setViewName("questionerr");
				mav.addObject("message",
						"Something went wrong, please try again later!");
				e.printStackTrace();
			}

		} else if (access == "ROLE_USER_NOTHING") {
			mav.setViewName("nofeedbackpage");
		}
		try {
			questionService.checkAnswer(question, userName);
			if (session != null) {
				session.setAttribute("QUESTION_ORDER", questionOrder + 1);
			}
		} catch (Exception e) {
			ModelAndView model = new ModelAndView("questionerr");
			model.addObject("message",
					"Something went wrong, please try again later!");
			return model;
		}
		return mav;

	}

	@RequestMapping(value = "/nextQuestion", method = RequestMethod.GET)
	public String nextQuestion(HttpSession session) {
		logger.debug("Request for next question");
		long testCurrentTime=new Date().getTime()/1000;
		
		long differenceTime=testCurrentTime-(long)session.getAttribute("TEST_START_TIME");
		long remainingTime=Constants.TEST_TIME-differenceTime;
		if (session != null) {
            session.setAttribute("TIME", remainingTime);
        }
		return "redirect:../user/question";
	}
}
