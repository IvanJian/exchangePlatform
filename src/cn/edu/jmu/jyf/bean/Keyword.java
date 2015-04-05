package cn.edu.jmu.jyf.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Keyword entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "keyword", catalog = "exchangeplatform")
public class Keyword implements java.io.Serializable {

	// Fields

	private KeywordId id;
	private Article article;
	private Tag tag;

	// Constructors

	/** default constructor */
	public Keyword() {
	}

	/** full constructor */
	public Keyword(KeywordId id, Article article, Tag tag) {
		this.id = id;
		this.article = article;
		this.tag = tag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "articleArticleId", column = @Column(name = "Article_articleId", nullable = false)),
			@AttributeOverride(name = "tagTagId", column = @Column(name = "Tag_tagId", nullable = false)) })
	public KeywordId getId() {
		return this.id;
	}

	public void setId(KeywordId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Article_articleId", nullable = false, insertable = false, updatable = false)
	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Tag_tagId", nullable = false, insertable = false, updatable = false)
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}