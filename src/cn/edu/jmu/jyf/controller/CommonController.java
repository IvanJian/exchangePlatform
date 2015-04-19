package cn.edu.jmu.jyf.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.jmu.jyf.model.ArticleModel;
import cn.edu.jmu.jyf.model.ArticleSummary;
import cn.edu.jmu.jyf.model.TagModel;
import cn.edu.jmu.jyf.service.CommonService;

@Controller
public class CommonController {

	/**
	 * 依据ID获取文章接口，若无该文章或该文章被封禁则返回空。此接口为公开接口，无需授权信息。
	 * 
	 * @param articleId
	 * @return 返回该文章信息的JSON,文章上传时间需要前端处理，从Long值转换为对应时间
	 */
	@RequestMapping(value = "/api/article/{articleId}", method = RequestMethod.GET)
	@ResponseBody
	public ArticleModel getArticleById(
			@PathVariable("articleId") Integer articleId) {
		ArticleModel articleModel = CommonService.getArticleModel(articleId);
		return articleModel;
	}

	/**
	 * 获取最新的N篇文章的摘要。此接口为公开接口，无需授权
	 * 
	 * @param amount
	 * @return 最新N篇文章摘要的JSON数组
	 */
	@RequestMapping(value = "/api/article/list/new/{amount}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getNewArticleList(
			@PathVariable("amount") Integer amount) {
		if (amount <= 0) {
			return null;
		}
		return CommonService.getNew(amount);
	}

	/**
	 * 获取热门的N篇文章的摘要。此接口为公开接口，无需授权。
	 * 
	 * @param amount
	 * @return最热门N篇文章摘要的JSON数组
	 */
	@RequestMapping(value = "/api/article/list/hot/{amount}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getHotArticleList(
			@PathVariable("amount") Integer amount) {
		if (amount <= 0) {
			return null;
		}
		return CommonService.getHot(amount);
	}

	/**
	 * 获取Tag接口
	 * 
	 * @return 包含Tag信息的JSON数组
	 */
	@RequestMapping(value = "/api/tags", method = RequestMethod.GET)
	@ResponseBody
	public List<TagModel> getTags() {
		return CommonService.getTags();
	}

	/**
	 * 获取过往文章列表
	 * 
	 * @param time
	 *            Long型的Timestamp数值,获取这个时间点以前的文章
	 * @param amount
	 *            获取时间点在time之前的文章数量
	 * @return 文章列表
	 */
	@RequestMapping(value = "/api/article/list/before/{time}/{amount}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getHistoryArticleList(
			@PathVariable("time") Timestamp time,
			@PathVariable("amount") Integer amount) {
		return null;

	}
}
