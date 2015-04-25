package cn.edu.jmu.jyf.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.Keyword;
import cn.edu.jmu.jyf.bean.Tag;
import cn.edu.jmu.jyf.dao.ArticleDAO;
import cn.edu.jmu.jyf.dao.KeywordDAO;
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
	public static ArticleModel getArticle(Integer articleId) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		Article article = articleDAO.findById(articleId);
		if (article == null || article.getIsHidden()) {
			return null;
		}
		Integer n = article.getReadNumber();
		article.setReadNumber(n + 1);
		articleDAO.merge(article);
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
	 * 获取热门文章。文章的热门程度与文章权重成正比，并且受到发布时间的影响，请在Config文件中设置详细参数。
	 * 
	 * @param amount
	 * @param begin
	 * @return
	 */
	public static List<ArticleSummary> getHot(Integer amount, Integer begin) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		List<Article> articles;
		try {
			articles = articleDAO.getHot(amount, begin);
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
	 * 按照文章权重排序，阅读量、收藏数、点赞数会影响文章权重。 请在Config文件中设置详细参数。
	 * 
	 * @param amount
	 * @param begin
	 * @return
	 */
	public static List<ArticleSummary> getByQuality(Integer amount,
			Integer begin) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		List<Article> articles;
		try {
			articles = articleDAO.getByQuality(amount, begin);
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

	public static Tag getTagById(Integer tagId) {
		TagDAO tagDAO = SpringContextUtil.getBean("TagDAO");
		Tag tag = tagDAO.findById(tagId);
		if (tag == null) {
			return null;
		}
		return tag;
	}

	/**
	 * 获取过往文章列表。
	 * 
	 * @param time
	 *            在该时间点之前
	 * @param amount
	 *            获取文章数量
	 * @return
	 */
	public static List<ArticleSummary> getPast(Long time, Integer amount) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		List<Article> articles;
		try {
			articles = articleDAO.getPastArticleList(time, amount);
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

	public static List<ArticleSummary> getByKeyword(Integer tagId,
			Integer amount, Long time) {
		KeywordDAO keywordDAO = SpringContextUtil.getBean("KeywordDAO");
		List<Keyword> keywords;
		try {
			keywords = keywordDAO.getByKeyword(tagId, time, amount);
		} catch (Exception e) {
			return null;
		}
		if (keywords.size() == 0) {
			return null;
		}
		List<Article> articles = new ArrayList<Article>();
		List<ArticleSummary> articleSummaries = new ArrayList<ArticleSummary>();
		for (Iterator iterator = keywords.iterator(); iterator.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			articles.add(keyword.getArticle());
		}
		for (Iterator iterator = articles.iterator(); iterator.hasNext();) {
			Article article = (Article) iterator.next();
			articleSummaries.add(new ArticleSummary(article));
		}
		return articleSummaries;
	}

	public static List<ArticleSummary> getHotByKeyword(Integer tagId,
			Integer amount, Integer begin) {
		KeywordDAO keywordDAO = SpringContextUtil.getBean("KeywordDAO");
		List<Keyword> keywords;
		try {
			keywords = keywordDAO.getHotByKeyword(tagId, amount, begin);
		} catch (Exception e) {
			return null;
		}
		if (keywords.size() == 0) {
			return null;
		}
		List<Article> articles = new ArrayList<Article>();
		List<ArticleSummary> articleSummaries = new ArrayList<ArticleSummary>();
		for (Iterator iterator = keywords.iterator(); iterator.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			articles.add(keyword.getArticle());
		}
		for (Iterator iterator = articles.iterator(); iterator.hasNext();) {
			Article article = (Article) iterator.next();
			articleSummaries.add(new ArticleSummary(article));
		}
		return articleSummaries;
	}

	public static List<ArticleSummary> getByKeywordAndQuality(Integer tagId,
			Integer amount, Integer begin) {
		KeywordDAO keywordDAO = SpringContextUtil.getBean("KeywordDAO");
		List<Keyword> keywords;
		try {
			keywords = keywordDAO.getByKeywordAndQuality(tagId, amount, begin);
		} catch (Exception e) {
			return null;
		}
		if (keywords.size() == 0) {
			return null;
		}
		List<Article> articles = new ArrayList<Article>();
		List<ArticleSummary> articleSummaries = new ArrayList<ArticleSummary>();
		for (Iterator iterator = keywords.iterator(); iterator.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			articles.add(keyword.getArticle());
		}
		for (Iterator iterator = articles.iterator(); iterator.hasNext();) {
			Article article = (Article) iterator.next();
			articleSummaries.add(new ArticleSummary(article));
		}
		return articleSummaries;
	}
}
