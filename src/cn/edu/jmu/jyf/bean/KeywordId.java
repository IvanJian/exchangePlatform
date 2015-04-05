package cn.edu.jmu.jyf.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KeywordId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class KeywordId implements java.io.Serializable {

	// Fields

	private Integer articleArticleId;
	private Integer tagTagId;

	// Constructors

	/** default constructor */
	public KeywordId() {
	}

	/** full constructor */
	public KeywordId(Integer articleArticleId, Integer tagTagId) {
		this.articleArticleId = articleArticleId;
		this.tagTagId = tagTagId;
	}

	// Property accessors

	@Column(name = "Article_articleId", nullable = false)
	public Integer getArticleArticleId() {
		return this.articleArticleId;
	}

	public void setArticleArticleId(Integer articleArticleId) {
		this.articleArticleId = articleArticleId;
	}

	@Column(name = "Tag_tagId", nullable = false)
	public Integer getTagTagId() {
		return this.tagTagId;
	}

	public void setTagTagId(Integer tagTagId) {
		this.tagTagId = tagTagId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KeywordId))
			return false;
		KeywordId castOther = (KeywordId) other;

		return ((this.getArticleArticleId() == castOther.getArticleArticleId()) || (this
				.getArticleArticleId() != null
				&& castOther.getArticleArticleId() != null && this
				.getArticleArticleId().equals(castOther.getArticleArticleId())))
				&& ((this.getTagTagId() == castOther.getTagTagId()) || (this
						.getTagTagId() != null
						&& castOther.getTagTagId() != null && this
						.getTagTagId().equals(castOther.getTagTagId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getArticleArticleId() == null ? 0 : this
						.getArticleArticleId().hashCode());
		result = 37 * result
				+ (getTagTagId() == null ? 0 : this.getTagTagId().hashCode());
		return result;
	}

}