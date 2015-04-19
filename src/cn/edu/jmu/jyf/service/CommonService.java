package cn.edu.jmu.jyf.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.Tag;
import cn.edu.jmu.jyf.dao.ArticleDAO;
import cn.edu.jmu.jyf.dao.TagDAO;
import cn.edu.jmu.jyf.model.ArticleModel;
import cn.edu.jmu.jyf.model.ArticleSummary;
import cn.edu.jmu.jyf.model.TagModel;
import cn.edu.jmu.jyf.util.SpringContextUtil;

public class CommonService {
	/**
	 * 根据文章ID获取对应文章。
	 * 
	 * @param articleId
	 * @return 返回ArticleModel，不包含敏感字段的Article内容。
	 */
	public static ArticleModel getArticleModel(Integer articleId) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		Article article = articleDAO.findById(articleId);
		if (article == null || article.getIsHidden()) {
			return null;
		}
		Integer n = article.getRead();
		article.setRead(n + 1);
		return new ArticleModel(article);

	}

	/**
	 * 获取最新N篇文章摘要
	 * 
	 * @param amount
	 * @return
	 */
	public static List<ArticleSummary> getNew(Integer amount) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		List<Article> articles;
		try {
			articles = articleDAO.getNew(amount);
		} catch (Exception e) {
			return null;
		}
		List<ArticleSummary> articleSummaries = new ArrayList<ArticleSummary>();
		if (articles.size() == 0) {
			return null;
		}
		for (Iterator iterator = articles.iterator(); iterator.hasNext();) {
			Article article = (Article) iterator.next();
			articleSummaries.add(new ArticleSummary(article));
		}
		return articleSummaries;
	}

	/**
	 * 获取最热门N篇文章摘要
	 * 
	 * @param amount
	 * @return
	 */
	public static List<ArticleSummary> getHot(Integer amount) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		List<Article> articles;
		try {
			articles = articleDAO.getHot(amount);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		List<ArticleSummary> articleSummaries = new ArrayList<ArticleSummary>();
		if (articles.size() == 0) {
			return null;
		}
		for (Iterator iterator = articles.iterator(); iterator.hasNext();) {
			Article article = (Article) iterator.next();
			articleSummaries.add(new ArticleSummary(article));
		}
		return articleSummaries;
	}

	/**
	 * 获取所有可用Tag信息
	 * 
	 * @return
	 */
	public static List<TagModel> getTags() {
		TagDAO tagDAO = SpringContextUtil.getBean("TagDAO");
		List<TagModel> tagList = new ArrayList<TagModel>();
		List<Tag> tags = tagDAO.findByIsEnabled(true);
		for (Iterator iterator = tags.iterator(); iterator.hasNext();) {
			Tag tag = (Tag) iterator.next();
			tagList.add(new TagModel(tag));
		}
		return tagList;
	}
}
