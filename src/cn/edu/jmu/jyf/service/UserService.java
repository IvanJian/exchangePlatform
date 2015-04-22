package cn.edu.jmu.jyf.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.Bookmark;
import cn.edu.jmu.jyf.bean.BookmarkId;
import cn.edu.jmu.jyf.bean.Interest;
import cn.edu.jmu.jyf.bean.InterestId;
import cn.edu.jmu.jyf.bean.Keyword;
import cn.edu.jmu.jyf.bean.KeywordId;
import cn.edu.jmu.jyf.bean.Like;
import cn.edu.jmu.jyf.bean.LikeId;
import cn.edu.jmu.jyf.bean.User;
import cn.edu.jmu.jyf.config.Config;
import cn.edu.jmu.jyf.dao.ArticleDAO;
import cn.edu.jmu.jyf.dao.BookmarkDAO;
import cn.edu.jmu.jyf.dao.InterestDAO;
import cn.edu.jmu.jyf.dao.LikeDAO;
import cn.edu.jmu.jyf.dao.UserDAO;
import cn.edu.jmu.jyf.model.ArticleSummary;
import cn.edu.jmu.jyf.model.Token;
import cn.edu.jmu.jyf.util.Md5Util;
import cn.edu.jmu.jyf.util.SpringContextUtil;

public class UserService {

	// 注册检查
	public static String registerCheck(User user) {
		UserDAO userDAO = UserDAO.getFromApplicationContext(SpringContextUtil
				.getApplicationContext());

		// 用户名为空
		if (user.getUsername() == null) {
			return "0104";
		}

		// 用户名已存在
		String username = user.getUsername();
		if (!userDAO.findByUsername(username).isEmpty()) {
			return "0102";
		}

		// 用户名长度不对
		if (user.getUsername().length() < Config.USERNAME_LENGTH_MIN
				|| user.getUsername().length() > Config.USERNAME_LENGTH_MAX) {
			return "0103";
		}

		// 密码为空
		if (user.getPasswordHash() == null) {
			return "0105";
		}

		// 密码长度不对
		if (user.getPasswordHash().length() < Config.PASSWORD_LENGTH_MIN
				|| user.getPasswordHash().length() > Config.PASSWORD_LENGTH_MAX) {
			return "0106";
		}

		// 邮箱为空
		if (user.getEmail() == null) {
			return "0107";
		}

		// 邮箱格式不对
		boolean flag = false;
		String email = user.getEmail();
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@"
					+ "([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		if (!flag) {
			return "0108";
		}

		// 邮箱已存在
		if (!userDAO.findByEmail(email).isEmpty()) {
			return "0109";
		}

		// 名字为空
		if (user.getName() == null) {
			return "0110";
		}

		// 昵称已被注册
		if (!userDAO.findByName(user.getName()).isEmpty()) {
			return "0113";
		}
		// 通过检查
		return "0101";
	}

	// 用户注册并生成Token存入数据库
	public static User register(User user) {
		UserDAO userDAO = UserDAO.getFromApplicationContext(SpringContextUtil
				.getApplicationContext());
		Timestamp registrationDateTime = new Timestamp(new Date().getTime());
		Token token = generateToken();
		user.setToken(token.getToken());
		user.setRegistrationDateTime(registrationDateTime);
		user.setTokenDeadline(token.getTokenDeadline());
		user.setPasswordHash(Md5Util.getMD5(user.getPasswordHash()));
		user.setIsBanned(false);

		try {
			userDAO.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	// 从User实例中获取Token对象
	public static Token getTokenObject(User user) {
		Token token = new Token(user.getUserId(), user.getToken(),
				user.getTokenDeadline());
		return token;
	}

	// 从JSON字符串中读取User对象
	public static User fromJSON(String json) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
		} catch (Exception e) {
			return null;
		}
		User user = new User();
		try {
			user.setUsername(jsonObject.getString("username"));
		} catch (Exception e) {
		}
		try {
			user.setPasswordHash(jsonObject.getString("password"));
		} catch (JSONException e) {
		}
		try {
			user.setEmail(jsonObject.getString("email"));
		} catch (Exception e) {
		}
		try {
			user.setName(jsonObject.getString("name"));
		} catch (Exception e) {
		}
		return user;
	}

	public static Token authenticate(String username, String passwordHash) {
		UserDAO userDAO = UserDAO.getFromApplicationContext(SpringContextUtil
				.getApplicationContext());
		User user = new User();
		user.setUsername(username);
		user.setPasswordHash(passwordHash);
		List<User> users = userDAO.findByExample(user);
		if (users.isEmpty()) {
			return null;
		}
		user = users.get(0);
		if (user.getIsBanned()) {
			return null;
		}
		Token token = generateToken();
		user.setToken(token.getToken());
		user.setTokenDeadline(token.getTokenDeadline());
		try {
			userDAO.merge(user);
		} catch (Exception e) {
			return null;
		}
		return getTokenObject(user);
	}

	public static Token generateToken() {
		Token token = new Token();
		token.setToken(UUID.randomUUID().toString());
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.YEAR, Config.TIME_LENGTH_YEAR);
		calendar.add(Calendar.MONTH, Config.TIME_LENGTH_MONTH);
		calendar.add(Calendar.DAY_OF_YEAR, Config.TIME_LENGTH_DAY);
		Timestamp tokenDeadline = new Timestamp(calendar.getTimeInMillis());
		token.setTokenDeadline(tokenDeadline);
		return token;
	}

	public static boolean verifyToken(Token token) {
		if (token.getUserId() == null || token.getToken() == null) {
			return false;
		}
		UserDAO userDAO = SpringContextUtil.getBean("UserDAO");
		User user = userDAO.findById(token.getUserId());
		long time = new Date().getTime();
		if (user.getIsBanned() || user.getTokenDeadline().getTime() - time <= 0) {
			return false;
		} else if (user.getToken().equals(token.getToken())) {
			return true;
		}
		return false;
	}

	// 此方法传入的article的Keyword数组每个Keyword只含有Tag字段的tagId属性
	public static boolean saveArticle(int userId, Article article) {
		try {
			Set<Keyword> keywords = article.getKeywords();
			article.setKeywords(null);
			ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
			UserDAO userDAO = SpringContextUtil.getBean("UserDAO");
			User user = userDAO.findById(userId);
			article.setUser(user);
			article.setIsHidden(false);
			article.setUploadDateTime(new Timestamp(new Date().getTime()));
			articleDAO.save(article);
			for (Iterator iterator = keywords.iterator(); iterator.hasNext();) {
				Keyword keyword = (Keyword) iterator.next();
				KeywordId id = new KeywordId();
				id.setArticleArticleId(article.getArticleId());
				id.setTagTagId(keyword.getTag().getTagId());
				keyword.setId(id);
			}
			System.out.println(article.getContent());
			article.setReadNumber(0);
			article.setKeywords(keywords);
			articleDAO.save(article);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 点赞功能，点赞后增加用户相关标签兴趣的权重
	 * 
	 * @param userId
	 * @param articleId
	 * @return
	 */
	public static boolean like(Integer userId, Integer articleId) {
		LikeDAO likeDAO = SpringContextUtil.getBean("LikeDAO");
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		Like like = new Like();
		LikeId likeId = new LikeId(userId, articleId);
		like.setId(likeId);
		if (likeDAO.findById(likeId) != null) {
			return false;
		}
		likeDAO.save(like);
		// 点赞后为该用户相应标签增加兴趣权重
		Set<Keyword> keywords = articleDAO.findById(articleId).getKeywords();
		for (Iterator iterator = keywords.iterator(); iterator.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			setInterest(userId, keyword.getTag().getTagId(),
					Config.LIKE_INTEREST_VARIATION);
		}
		return true;
	}

	/**
	 * 设置用户对某标签兴趣权重的变化，variation为变化量
	 * 
	 * @param userId
	 * @param tagId
	 * @param variation
	 */
	public static void setInterest(Integer userId, Integer tagId,
			Integer variation) {
		InterestDAO interestDAO = SpringContextUtil.getBean("InterestDAO");
		InterestId id = new InterestId();
		id.setUserUserId(userId);
		id.setTagTagId(tagId);
		Interest interest = interestDAO.findById(id);
		if (interest == null) {
			interest = new Interest(null, null, null, 0);
		}
		interest.setId(id);
		interest.setWeight(interest.getWeight() + variation);
		interestDAO.merge(interest);
	}

	/**
	 * 收藏文章并增加兴趣权重
	 * 
	 * @param userId
	 * @param articleId
	 * @return
	 */
	public static boolean bookmark(Integer userId, Integer articleId) {
		ArticleDAO articleDAO = SpringContextUtil.getBean("ArticleDAO");
		BookmarkDAO bookmarkDAO = SpringContextUtil.getBean("BookmarkDAO");
		BookmarkId bookmarkId = new BookmarkId(userId, articleId);
		Bookmark bookmark = bookmarkDAO.findById(bookmarkId);
		if (bookmark != null) {
			return false;
		}
		bookmark = new Bookmark();
		bookmark.setId(bookmarkId);
		bookmark.setBookmarkDateTime(new Timestamp(new Date().getTime()));
		bookmarkDAO.save(bookmark);
		// 收藏后增加相应标签兴趣权重
		Set<Keyword> keywords = articleDAO.findById(articleId).getKeywords();
		for (Iterator iterator = keywords.iterator(); iterator.hasNext();) {
			Keyword keyword = (Keyword) iterator.next();
			setInterest(userId, keyword.getTag().getTagId(),
					Config.BOOKMARK_INTEREST_VARIATION);
		}
		return true;
	}

	public static List<ArticleSummary> recommend(Integer userId) {
		return null;

	}
}
