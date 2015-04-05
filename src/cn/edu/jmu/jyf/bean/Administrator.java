package cn.edu.jmu.jyf.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "administrator", catalog = "exchangeplatform", uniqueConstraints = @UniqueConstraint(columnNames = "loginName"))
public class Administrator implements java.io.Serializable {

	// Fields

	private Integer administratorId;
	private String loginName;
	private String passwordHash;
	private String name;
	private String token;
	private Timestamp tokenDeadline;

	// Constructors

	/** default constructor */
	public Administrator() {
	}

	/** minimal constructor */
	public Administrator(String loginName, String passwordHash, String name) {
		this.loginName = loginName;
		this.passwordHash = passwordHash;
		this.name = name;
	}

	/** full constructor */
	public Administrator(String loginName, String passwordHash, String name,
			String token, Timestamp tokenDeadline) {
		this.loginName = loginName;
		this.passwordHash = passwordHash;
		this.name = name;
		this.token = token;
		this.tokenDeadline = tokenDeadline;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "administratorId", unique = true, nullable = false)
	public Integer getAdministratorId() {
		return this.administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	@Column(name = "loginName", unique = true, nullable = false, length = 45)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "passwordHash", nullable = false, length = 45)
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

}