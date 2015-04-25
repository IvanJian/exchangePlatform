package cn.edu.jmu.jyf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.jmu.jyf.bean.Tag;
import cn.edu.jmu.jyf.service.CommonService;

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

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write() {
		return new ModelAndView("write");
	}

	@RequestMapping(value = "/my/bookmarks", method = RequestMethod.GET)
	public ModelAndView getBookmarks() {
		return new ModelAndView("bookmarks");
	}

	@RequestMapping(value = "/my/profile", method = RequestMethod.GET)
	public ModelAndView getProfile() {
		return new ModelAndView("profile");
	}

	@RequestMapping(value = "/keyword/{tagId}", method = RequestMethod.GET)
	public ModelAndView getByTag(@PathVariable("tagId") Integer tagId) {
		Map<String, String> map = new HashMap<String, String>();
		Tag tag = CommonService.getTagById(tagId);
		if (tag == null) {
			return new ModelAndView("404");
		}
		map.put("tagId", tagId.toString());
		map.put("tagName", tag.getTagName());
		return new ModelAndView("tag", map);
	}
}
