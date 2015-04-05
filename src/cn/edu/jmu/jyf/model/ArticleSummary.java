package cn.edu.jmu.jyf.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.Keyword;

public class ArticleSummary {
	private Integer articleId;
	private String title;
	private String authorName;
	private Timestamp uploadDateTime;
	private String image;
	private Set<String> tags = new HashSet<String>();

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Timestamp getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(Timestamp uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public ArticleSummary(Integer articleId, String title, String authorName,
			Timestamp uploadDateTime, String image, Set<String> tags) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.authorName = authorName;
		this.uploadDateTime = uploadDateTime;
		this.image = image;
		this.tags = tags;
	}

	public ArticleSummary() {
		super();
	}

	public ArticleSummary(Article article) {
		this.articleId = article.getArticleId();
		this.title = article.getTitle();
		this.uploadDateTime = article.getUploadDateTime();
		this.image = article.getImage();
		this.authorName = article.getUser().getName();
		for (Iterator iterator = article.getKeywords().iterator(); iterator
				.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			this.tags.add(keyword.getTag().getTagName());
		}
	}
}
