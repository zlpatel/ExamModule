package org.exammodule.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.exception.AllAdditionalQuestionAnsweredException;
import org.exammodule.exception.NoAdditionalQuestionAvailableException;
import org.exammodule.exception.NoAssignedQuestionException;
import org.exammodule.form.QuestionFormBean;
import org.exammodule.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class QuestionController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView initForm(HttpSession session) 
	{
		logger.debug("Received request to show question page");
		
		String userName=(String)session.getAttribute("USERNAME");
		logger.debug(userName+" User logged in");
		QuestionFormBean question=null;
		try {
			question = questionService.getAQuestion(userName);
		} 
		catch(NoAssignedQuestionException e)
		{
			ModelAndView model=new ModelAndView("questionerr");
			model.addObject("message", e.getMessage());
			return model;
		}
		catch(NoAdditionalQuestionAvailableException e)
		{
			ModelAndView model=new ModelAndView("questionerr");
			model.addObject("message", e.getMessage());
			return model;
		}
		catch(AllAdditionalQuestionAnsweredException e)
		{
			ModelAndView model=new ModelAndView("questionerr");
			model.addObject("message", e.getMessage());
			return model;
		}
		catch (Exception e) {
			ModelAndView model=new ModelAndView("questionerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		ModelAndView model=new ModelAndView("questionpage");
		model.addObject("optionList", question.getOptionList());
		model.addObject("wholeQuestion",question.getWholeQuestion());
		model.addObject("command", question);
		return model;
	}
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public ModelAndView  questionFormSubmit(@ModelAttribute("command")QuestionFormBean question,HttpSession session)
	{
		logger.debug("Question page subimtted");
		ModelAndView mav=new ModelAndView();
		String userName=(String)session.getAttribute("USERNAME");
		String videoLink=null;
		try {
			videoLink = questionService.getVideoLink(question);
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("questionerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		question.setVideoLink(videoLink);
		boolean result=false;
		try {
			result = questionService.checkAnswer(question,userName);
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("questionerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		question.setCorrect(result);
		if(result)
			question.setMessage("Your answer is correct :D !!");
		else
			question.setMessage("Your answer is incorrect :( !!");
		mav.setViewName("choicepage");
		mav.addObject("question",question);
		return mav;
	}
	@RequestMapping(value = "/choice/{choice}", method=RequestMethod.GET)
	public String moreQuestionChoice(@PathVariable boolean choice) 
	{
		logger.debug("Trying again or opting for more questions!");
		if(choice)
			return "redirect:../question";
		else
			return "userpage";
	}
}
