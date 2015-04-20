package cn.edu.jmu.jyf.config;

public class Config {

	// 用户名和密码的限定长度
	public static final int USERNAME_LENGTH_MIN = 6;
	public static final int USERNAME_LENGTH_MAX = 12;
	public static final int PASSWORD_LENGTH_MIN = 6;
	public static final int PASSWORD_LENGTH_MAX = 12;

	// Token有效时长
	public static final int TIME_LENGTH_YEAR = 1;
	public static final int TIME_LENGTH_MONTH = 0;
	public static final int TIME_LENGTH_DAY = 30;

	/*
	 * 用户对每个标签都会有兴趣权重，点赞和收藏会增加对应标签的兴趣度
	 */
	// 点赞增加相应标签兴趣权重数值
	public static final int LIKE_INTEREST_VARIATION = 5;
	// 收藏增加相应标签兴趣权重数值
	public static final int BOOKMARK_INTEREST_VARIATION = 15;

	/*
	 * 文章权重参数：收藏数、点赞数、阅读量 分别在文章权重中的系数 文章的权重=X收藏数+Y点赞数+Z阅读量
	 */
	public static final Integer WEIGHT_OF_BOOKMARK_IN_ARTICLE = 20;
	public static final Integer WEIGHT_OF_LIKE_IN_ARTICLE = 4;
	public static final Integer WEIGHT_OF_READNUMBER_IN_ARTICLE = 1;

	/*
	 * 时间对文章热门的影响程度参数，在筛选热门文章时，会参考文章本身权重和发表时间。 文章热门度=K*发布时间*文章权重
	 * 每过一天，文章权重便会下降10%。请维护人员根据运营情况修改该数值。
	 */
	public static final Float WEIGHT_OF_TIME_IN_HOT = Float.valueOf("0.1");
}
