package cn.edu.jmu.jyf.bean;

import java.sql.Timestamp;
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

import org.json.JSONObject;

import cn.edu.jmu.jyf.model.Token;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "exchangeplatform", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String passwordHash;
	private String name;
	private String email;
	private Timestamp registrationDateTime;
	private Boolean isBanned;
	private String token;
	private Timestamp tokenDeadline;
	private String icon;
	private String introduction;
	private Set<Article> articles = new HashSet<Article>(0);
	private Set<Interest> interests = new HashSet<Interest>(0);
	private Set<Bookmark> bookmarks = new HashSet<Bookmark>(0);
	private Set<Like> likes = new HashSet<Like>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String passwordHash, String name,
			String email, Timestamp registrationDateTime, Boolean isBanned) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.name = name;
		this.email = email;
		this.registrationDateTime = registrationDateTime;
		this.isBanned = isBanned;
	}

	/** full constructor */
	public User(String username, String passwordHash, String name,
			String email, Timestamp registrationDateTime, Boolean isBanned,
			String token, Timestamp tokenDeadline, String icon,
			String introduction, Set<Article> articles,
			Set<Interest> interests, Set<Bookmark> bookmarks, Set<Like> likes) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.name = name;
		this.email = email;
		this.registrationDateTime = registrationDateTime;
		this.isBanned = isBanned;
		this.token = token;
		this.tokenDeadline = tokenDeadline;
		this.icon = icon;
		this.introduction = introduction;
		this.articles = articles;
		this.interests = interests;
		this.bookmarks = bookmarks;
		this.likes = likes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "passwordHash", nullable = false, length = 32)
	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "registrationDateTime", nullable = false, length = 19)
	public Timestamp getRegistrationDateTime() {
		return this.registrationDateTime;
	}

	public void setRegistrationDateTime(Timestamp registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}

	@Column(name = "isBanned", nullable = false)
	public Boolean getIsBanned() {
		return this.isBanned;
	}

	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}

	@Column(name = "token", length = 45)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "tokenDeadline", length = 19)
	public Timestamp getTokenDeadline() {
		return this.tokenDeadline;
	}

	public void setTokenDeadline(Timestamp tokenDeadline) {
		this.tokenDeadline = tokenDeadline;
	}

	@Column(name = "icon", length = 45)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "introduction", length = 300)
	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Interest> getInterests() {
		return this.interests;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Bookmark> getBookmarks() {
		return this.bookmarks;
	}

	public void setBookmarks(Set<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Like> getLikes() {
		return this.likes;
	}

	public void setLikes(Set<Like> likes) {
		this.likes = likes;
	}
	

}