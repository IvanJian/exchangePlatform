package cn.edu.jmu.jyf.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * InterestId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class InterestId implements java.io.Serializable {

	// Fields

	private Integer userUserId;
	private Integer tagTagId;

	// Constructors

	/** default constructor */
	public InterestId() {
	}

	/** full constructor */
	public InterestId(Integer userUserId, Integer tagTagId) {
		this.userUserId = userUserId;
		this.tagTagId = tagTagId;
	}

	// Property accessors

	@Column(name = "User_userId", nullable = false)
	public Integer getUserUserId() {
		return this.userUserId;
	}

	public void setUserUserId(Integer userUserId) {
		this.userUserId = userUserId;
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
		if (!(other instanceof InterestId))
			return false;
		InterestId castOther = (InterestId) other;

		return ((this.getUserUserId() == castOther.getUserUserId()) || (this
				.getUserUserId() != null && castOther.getUserUserId() != null && this
				.getUserUserId().equals(castOther.getUserUserId())))
				&& ((this.getTagTagId() == castOther.getTagTagId()) || (this
						.getTagTagId() != null
						&& castOther.getTagTagId() != null && this
						.getTagTagId().equals(castOther.getTagTagId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getUserUserId() == null ? 0 : this.getUserUserId()
						.hashCode());
		result = 37 * result
				+ (getTagTagId() == null ? 0 : this.getTagTagId().hashCode());
		return result;
	}

}