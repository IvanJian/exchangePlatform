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
 * Like entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "like", catalog = "exchangeplatform")
public class Like implements java.io.Serializable {

	// Fields

	private LikeId id;
	private User user;
	private Article article;

	// Constructors

	/** default constructor */
	public Like() {
	}

	/** full constructor */
	public Like(LikeId id, User user, Article article) {
		this.id = id;
		this.user = user;
		this.article = article;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userUserId", column = @Column(name = "User_userId", nullable = false)),
			@AttributeOverride(name = "articleArticleId", column = @Column(name = "Article_articleId", nullable = false)) })
	public LikeId getId() {
		return this.id;
	}

	public void setId(LikeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "User_userId", nullable = false, insertable = false, updatable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Article_articleId", nullable = false, insertable = false, updatable = false)
	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}