package cn.edu.jmu.jyf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.bean.User;
import cn.edu.jmu.jyf.model.Token;
import cn.edu.jmu.jyf.requestModel.BookmarkModel;
import cn.edu.jmu.jyf.requestModel.LikeModel;
import cn.edu.jmu.jyf.requestModel.UploadArticleModel;
import cn.edu.jmu.jyf.responseModel.ErrorResponse;
import cn.edu.jmu.jyf.responseModel.RegisterSuccessResponse;
import cn.edu.jmu.jyf.responseModel.ResponseModel;
import cn.edu.jmu.jyf.responseModel.UserAuthenticationResponse;
import cn.edu.jmu.jyf.service.UserService;

@Controller
public class UserController {

	/**
	 * 用户注册接口 接收JSON数据，包含username，passwordHash，email，name字段
	 */
	@RequestMapping(value = "/api/user/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel register(@RequestBody User user) {
		if (user == null) {
			ErrorResponse response = new ErrorResponse();
			response.setResponseCode("0112");
			response.setMessage("参数有误");
			return response;
		}
		String responseCode = UserService.registerCheck(user);
		if (responseCode.equals("0101")) { // 检查通过
			User userSuc = UserService.register(user);
			if (userSuc == null) { // 写入数据库失败，返回错误代码0111.
				ErrorResponse response = new ErrorResponse();
				response.setResponseCode("0111");
				response.setMessage("写入数据库错误");
				return response;
			} else { // 写入成功，返回成功代码0101和Token。
				RegisterSuccessResponse response = new RegisterSuccessResponse();
				response.setResponseCode("0101");
				response.setToken(UserService.getTokenObject(userSuc));
				return response;
			}
		}

		// 检查没有通过，返回对应错误代码
		ErrorResponse response = new ErrorResponse();
		response.setResponseCode(responseCode);

		if (responseCode.equals("0102")) {
			response.setMessage("用户名已经存在。");
		} else if (responseCode.equals("0103")) {
			response.setMessage("用户名长度有误");
		} else if (responseCode.equals("0104")) {
			response.setMessage("用户名不能为空。");
		} else if (responseCode.equals("0105")) {
			response.setMessage("密码不能为空");
		} else if (responseCode.equals("0106")) {
			response.setMessage("密码长度有误");
		} else if (responseCode.equals("0107")) {
			response.setMessage("邮箱不能为空。");
		} else if (responseCode.equals("0108")) {
			response.setMessage("邮箱格式有误。");
		} else if (responseCode.equals("0109")) {
			response.setMessage("邮箱已经被注册。");
		} else if (responseCode.equals("0110")) {
			response.setMessage("昵称不能为空。");
		} else if (responseCode.equals("0113")) {
			response.setMessage("昵称已被注册.");
		}

		return response;
	}

	/**
	 * 授权接口
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/api/user/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel authenticate(
			@RequestParam(defaultValue = "") String username,
			@RequestParam(value = "passwordHash", defaultValue = "") String passwordHash) {
		// 用户名或密码为空
		if (username.equals("") || passwordHash.equals("")) {
			ErrorResponse response = new ErrorResponse();
			response.setResponseCode("0202");
			response.setMessage("用户名或密码为空.");
			return response;
		}
		// 尝试获取授权Token
		Token token = UserService.authenticate(username, passwordHash);
		// 授权失败
		if (token == null) {
			ErrorResponse response = new ErrorResponse();
			response.setResponseCode("0203");
			response.setMessage("用户名或密码错误");
			return response;
		}
		// 授权成功
		UserAuthenticationResponse response = new UserAuthenticationResponse();
		response.setResponseCode("0201");
		response.setToken(token);
		return response;
	}

	/**
	 * 文章上传接口 接收JSON字符串格式数据，其中包含两个对象：{Token,Article} 范例如下
	 * {"token":{"userId":1,"token":"....","tokenDeadline":1461340195279},
	 * "article":{"title":"....","content":"....","image":"....",
	 * "keywords":[{"tag":{"tagId":1}},{"tag":{"tagId":2}}]}}
	 */
	@RequestMapping(value = "/api/user/uploadarticle", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel uploadAriticle(
			@RequestBody UploadArticleModel uploadArticleModel) {
		Token token = uploadArticleModel.getToken();
		if (!UserService.verifyToken(token)) {
			return new ErrorResponse("0302", "Token无效");
		}
		Article article = uploadArticleModel.getArticle();
		if (!UserService.saveArticle(token.getUserId(), article)) {
			return new ErrorResponse("0303", "存入数据库失败。");
		}
		return new ResponseModel("0301");
	}

	/**
	 * 点赞接口，包含Token和LikeId两个对象。接收JSON字符串。 JSON范例如下：
	 * {"token":{"userId":1,"token":"25309d38-1adf-419e-9481-9b34e57cabba",
	 * "tokenDeadline"
	 * :1461393807828},"likeId":{"userUserId":1,"articleArticleId":24}}
	 * 
	 * @param likeModel
	 * @return
	 */
	@RequestMapping(value = "/api/user/like", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel like(@RequestBody LikeModel likeModel) {
		Token token = likeModel.getToken();
		if (!UserService.verifyToken(token)) {
			return new ErrorResponse("0402", "Token无效");
		}
		if (!UserService.like(likeModel.getLikeId().getUserUserId(), likeModel
				.getLikeId().getArticleArticleId())) {
			return new ErrorResponse("0403", "操作失败。");
		}
		return new ResponseModel("0401");
	}

	/**
	 * 收藏文章接口。 接收Token和articleId JSON格式如下：
	 * {"token":{"userId":1,"token":"0a868cd1-6c13-4cb8-b972-6bed2615b132",
	 * "tokenDeadline":1461399045935},"articleId":24}
	 * 
	 * @param bookmarkModel
	 * @return
	 */
	@RequestMapping(value = "/api/user/bookmark", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel bookmark(@RequestBody BookmarkModel bookmarkModel) {
		Token token = bookmarkModel.getToken();
		if (!UserService.verifyToken(token)) {
			return new ErrorResponse("0502", "Token验证失败。");
		}
		if (!UserService.bookmark(token.getUserId(),
				bookmarkModel.getArticleId())) {
			return new ErrorResponse("0503", "该文章已经收藏。");
		}
		return new ResponseModel("0501");
	}

}
