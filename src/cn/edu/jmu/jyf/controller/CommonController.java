package cn.edu.jmu.jyf.controller;

import java.util.Date;
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
		ArticleModel articleModel = CommonService.getArticle(articleId);
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
	public List<ArticleSummary> getNew(@PathVariable("amount") Integer amount) {
		if (amount <= 0) {
			return null;
		}
		return CommonService.getNew(amount);
	}

	/**
	 * 获取热门文章列表
	 * 
	 * @param amount获取总数
	 * @param begin从第N条记录开始
	 * @return 包含文章列表的JSON数组
	 */
	@RequestMapping(value = "/api/article/list/hot/{amount}/{begin}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getHot(@PathVariable("amount") Integer amount,
			@PathVariable("begin") Integer begin) {
		if (amount <= 0 || begin <= 0) {
			return null;
		}
		return CommonService.getHot(amount, begin);
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
	public List<ArticleSummary> getPast(@PathVariable("time") Long time,
			@PathVariable("amount") Integer amount) {
		if (time <= 0 || amount <= 0) {
			return null;
		}
		return CommonService.getPast(time, amount);
	}

	/**
	 * 按文章权重获取文章列表
	 * 
	 * @param amount
	 *            获取记录数
	 * @param begin
	 *            从第N条记录开始获取
	 * @return 按照文章权重排序的文章列表
	 */
	@RequestMapping(value = "/api/article/list/quality/{amount}/{begin}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getByQuality(
			@PathVariable("amount") Integer amount,
			@PathVariable("begin") Integer begin) {
		if (amount <= 0 || begin <= 0) {
			return null;
		}
		return CommonService.getByQuality(amount, begin);
	}

	/**
	 * 按照关键字获取某时间点以前的N篇文章列表
	 * 
	 * @param tagId标签编号
	 * @param amount总数
	 * @param time时间点
	 * @return文章列表
	 */
	@RequestMapping(value = "/api/article/list/keyword/{tagId}/before/{time}/{amount}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getByKeyword(
			@PathVariable("tagId") Integer tagId,
			@PathVariable("amount") Integer amount,
			@PathVariable("time") Long time) {
		if (amount <= 0 || time <= 0) {
			return null;
		}
		return CommonService.getByKeyword(tagId, amount, time);
	}

	/**
	 * 按照关键字获取最新N篇文章
	 * 
	 * @param tagId标签编号
	 * @param amount总数
	 * @return文章列表
	 */
	@RequestMapping(value = "/api/article/list/keyword/{tagId}/{amount}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getNewByKeyword(
			@PathVariable("tagId") Integer tagId,
			@PathVariable("amount") Integer amount) {
		if (amount <= 0) {
			return null;
		}
		Long time = new Date().getTime();
		return CommonService.getByKeyword(tagId, amount, time);
	}

	/**
	 * 按照关键字获取热门文章
	 * 
	 * @param tagId标签编号
	 * @param amount获取数量
	 * @param begin从第几条记录开始获取
	 * @return文章列表
	 */
	@RequestMapping(value = "/api/article/list/keyword/{tagId}/hot/{amount}/{begin}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getHotByKeword(
			@PathVariable("tagId") Integer tagId,
			@PathVariable("amount") Integer amount,
			@PathVariable("begin") Integer begin) {
		if (amount <= 0 || begin <= 0) {
			return null;
		}
		return CommonService.getHotByKeyword(tagId, amount, begin);
	}

	/**
	 * 按照关键字和文章权重排序获取文章
	 * 
	 * @param tagId标签编号
	 * @param amount获取总数
	 * @param begin从第几条数据开始获取
	 * @return文章列表
	 */
	@RequestMapping(value = "/api/article/list/keyword/{tagId}/quality/{amount}/{begin}", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleSummary> getByKewordAndQuality(
			@PathVariable("tagId") Integer tagId,
			@PathVariable("amount") Integer amount,
			@PathVariable("begin") Integer begin) {
		if (amount <= 0 || begin <= 0) {
			return null;
		}
		return CommonService.getByKeywordAndQuality(tagId, amount, begin);
	}
}
