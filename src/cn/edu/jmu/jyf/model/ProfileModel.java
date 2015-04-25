package cn.edu.jmu.jyf.model;

import java.util.Iterator;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.User;
import cn.edu.jmu.jyf.dao.BookmarkDAO;
import cn.edu.jmu.jyf.dao.LikeDAO;
import cn.edu.jmu.jyf.dao.UserDAO;
import cn.edu.jmu.jyf.util.SpringContextUtil;

public class ProfileModel {
	private String username;
	private String name;
	private String email;
	private String icon;
	private Integer likeAmount;
	private Integer bookmarkAmount;
	private Integer articleAmount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(Integer likeAmount) {
		this.likeAmount = likeAmount;
	}

	public Integer getBookmarkAmount() {
		return bookmarkAmount;
	}

	public void setBookmarkAmount(Integer bookmarkAmount) {
		this.bookmarkAmount = bookmarkAmount;
	}

	public Integer getArticleAmount() {
		return articleAmount;
	}

	public void setArticleAmount(Integer articleAmount) {
		this.articleAmount = articleAmount;
	}

	public ProfileModel(String username, String name, String email,
			String icon, Integer likeAmount, Integer bookmarkAmount,
			Integer articleAmount) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.icon = icon;
		this.likeAmount = likeAmount;
		this.bookmarkAmount = bookmarkAmount;
		this.articleAmount = articleAmount;
	}

	public ProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfileModel(Integer userId) {
		UserDAO userDAO = SpringContextUtil.getBean("UserDAO");
		User user = userDAO.findById(userId);
		this.username = user.getUsername();
		this.name = user.getName();
		this.icon = user.getIcon();
		this.email = user.getEmail();

		BookmarkDAO bookmarkDAO = SpringContextUtil.getBean("BookmarkDAO");
		LikeDAO likeDAO = SpringContextUtil.getBean("LikeDAO");
		this.articleAmount = user.getArticles().size();
		this.bookmarkAmount = 0;
		this.likeAmount = 0;
		for (Iterator iterator = user.getArticles().iterator(); iterator
				.hasNext();) {
			Article article = (Article) iterator.next();
			this.likeAmount += likeDAO.findByProperty("article", article)
					.size();
			this.bookmarkAmount += bookmarkDAO.findByProperty("article",
					article).size();
		}
	}

}
