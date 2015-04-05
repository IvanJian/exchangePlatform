package cn.edu.jmu.jyf.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * LikeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class LikeId implements java.io.Serializable {

	// Fields

	private Integer userUserId;
	private Integer articleArticleId;

	// Constructors

	/** default constructor */
	public LikeId() {
	}

	/** full constructor */
	public LikeId(Integer userUserId, Integer articleArticleId) {
		this.userUserId = userUserId;
		this.articleArticleId = articleArticleId;
	}

	// Property accessors

	@Column(name = "User_userId", nullable = false)
	public Integer getUserUserId() {
		return this.userUserId;
	}

	public void setUserUserId(Integer userUserId) {
		this.userUserId = userUserId;
	}

	@Column(name = "Article_articleId", nullable = false)
	public Integer getArticleArticleId() {
		return this.articleArticleId;
	}

	public void setArticleArticleId(Integer articleArticleId) {
		this.articleArticleId = articleArticleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LikeId))
			return false;
		LikeId castOther = (LikeId) other;

		return ((this.getUserUserId() == castOther.getUserUserId()) || (this
				.getUserUserId() != null && castOther.getUserUserId() != null && this
				.getUserUserId().equals(castOther.getUserUserId())))
				&& ((this.getArticleArticleId() == castOther
						.getArticleArticleId()) || (this.getArticleArticleId() != null
						&& castOther.getArticleArticleId() != null && this
						.getArticleArticleId().equals(
								castOther.getArticleArticleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getUserUserId() == null ? 0 : this.getUserUserId()
						.hashCode());
		result = 37
				* result
				+ (getArticleArticleId() == null ? 0 : this
						.getArticleArticleId().hashCode());
		return result;
	}

}