package cn.edu.jmu.jyf.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Tag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tag", catalog = "exchangeplatform", uniqueConstraints = @UniqueConstraint(columnNames = "tagName"))
public class Tag implements java.io.Serializable {

	// Fields

	private Integer tagId;
	private String tagName;
	private Boolean isEnabled;
	private String image;
	private Set<Interest> interests = new HashSet<Interest>(0);
	private Set<Keyword> keywords = new HashSet<Keyword>(0);

	// Constructors

	/** default constructor */
	public Tag() {
	}

	/** minimal constructor */
	public Tag(String tagName, Boolean isEnabled) {
		this.tagName = tagName;
		this.isEnabled = isEnabled;
	}

	/** full constructor */
	public Tag(String tagName, Boolean isEnabled, String image,
			Set<Interest> interests, Set<Keyword> keywords) {
		this.tagName = tagName;
		this.isEnabled = isEnabled;
		this.image = image;
		this.interests = interests;
		this.keywords = keywords;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tagId", unique = true, nullable = false)
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "tagName", unique = true, nullable = false, length = 45)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Column(name = "isEnabled", nullable = false)
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(name = "image", length = 45)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<Interest> getInterests() {
		return this.interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tag")
	public Set<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

}