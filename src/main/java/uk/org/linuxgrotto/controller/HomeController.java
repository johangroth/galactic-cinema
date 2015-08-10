package uk.org.linuxgrotto.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.org.linuxgrotto.form.MessageForm;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	private static List<MessageForm> messageFormRepository = new ArrayList<>();

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeForm(Model model) {
		log.info("Welcome home! from Primefaces");

		model.addAttribute("messageInfo", new MessageForm());

		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("messageInfo") MessageForm messageForm,
			BindingResult result) {

		if (result.hasErrors()) {
			return "index";
		}

		addNewMessage(messageForm);
		return "redirect:/list";

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView showResults() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("messages", messageFormRepository);

		return modelAndView;

	}

	private void addNewMessage(MessageForm messageForm) {
		messageFormRepository.add(messageForm);
	}

}
