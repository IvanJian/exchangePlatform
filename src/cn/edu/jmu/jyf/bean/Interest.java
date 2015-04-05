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
 * Interest entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "interest", catalog = "exchangeplatform")
public class Interest implements java.io.Serializable {

	// Fields

	private InterestId id;
	private User user;
	private Tag tag;
	private Integer weight;

	// Constructors

	/** default constructor */
	public Interest() {
	}

	/** full constructor */
	public Interest(InterestId id, User user, Tag tag, Integer weight) {
		this.id = id;
		this.user = user;
		this.tag = tag;
		this.weight = weight;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userUserId", column = @Column(name = "User_userId", nullable = false)),
			@AttributeOverride(name = "tagTagId", column = @Column(name = "Tag_tagId", nullable = false)) })
	public InterestId getId() {
		return this.id;
	}

	public void setId(InterestId id) {
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
	@JoinColumn(name = "Tag_tagId", nullable = false, insertable = false, updatable = false)
	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Column(name = "weight", nullable = false)
	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}