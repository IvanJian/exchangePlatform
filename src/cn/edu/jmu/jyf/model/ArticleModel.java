package cn.edu.jmu.jyf.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.Keyword;

public class ArticleModel {
	private Integer articleId;
	private String authorName;
	private String image;
	private String title;
	private String content;
	private Timestamp uploadDateTime;
	private Integer likeAmount;
	private Integer readNumber;
	private Set<String> tags = new HashSet<String>();

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(Timestamp uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Integer getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(Integer likeAmount) {
		this.likeAmount = likeAmount;
	}

	public ArticleModel() {
		super();
	}

	public Integer getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(Integer readNumber) {
		this.readNumber = readNumber;
	}

	public ArticleModel(Integer articleId, String authorName, String image,
			String title, String content, Timestamp uploadDateTime,
			Integer likeAmount, Integer read, Set<String> tags) {
		super();
		this.articleId = articleId;
		this.authorName = authorName;
		this.image = image;
		this.title = title;
		this.content = content;
		this.uploadDateTime = uploadDateTime;
		this.likeAmount = likeAmount;
		this.readNumber = readNumber;
		this.tags = tags;
	}

	public ArticleModel(Article article) {
		this.articleId = article.getArticleId();
		this.title = article.getTitle();
		this.content = article.getContent();
		this.uploadDateTime = article.getUploadDateTime();
		this.image = article.getImage();
		this.authorName = article.getUser().getName();
		this.likeAmount = article.getLikes().size();
		this.readNumber = article.getReadNumber();
		for (Iterator iterator = article.getKeywords().iterator(); iterator
				.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			this.tags.add(keyword.getTag().getTagName());
		}
	}

}
