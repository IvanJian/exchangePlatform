package cn.edu.jmu.jyf.bean;

import java.sql.Timestamp;
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
 * Bookmark entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bookmark", catalog = "exchangeplatform")
public class Bookmark implements java.io.Serializable {

	// Fields

	private BookmarkId id;
	private User user;
	private Article article;
	private Timestamp bookmarkDateTime;

	// Constructors

	/** default constructor */
	public Bookmark() {
	}

	/** full constructor */
	public Bookmark(BookmarkId id, User user, Article article,
			Timestamp bookmarkDateTime) {
		this.id = id;
		this.user = user;
		this.article = article;
		this.bookmarkDateTime = bookmarkDateTime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userUserId", column = @Column(name = "User_userId", nullable = false)),
			@AttributeOverride(name = "articleArticleId", column = @Column(name = "Article_articleId", nullable = false)) })
	public BookmarkId getId() {
		return this.id;
	}

	public void setId(BookmarkId id) {
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

	@Column(name = "bookmarkDateTime", nullable = false, length = 19)
	public Timestamp getBookmarkDateTime() {
		return this.bookmarkDateTime;
	}

	public void setBookmarkDateTime(Timestamp bookmarkDateTime) {
		this.bookmarkDateTime = bookmarkDateTime;
	}

}