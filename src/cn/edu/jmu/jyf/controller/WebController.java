package cn.edu.jmu.jyf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	@RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
	public ModelAndView getArticle(@PathVariable("articleId") Integer articleId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", articleId);
		return new ModelAndView("article", map);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getArticle() {
		return new ModelAndView("signup");
	}
}
