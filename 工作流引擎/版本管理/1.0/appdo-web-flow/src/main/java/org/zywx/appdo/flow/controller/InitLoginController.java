package org.zywx.appdo.flow.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zywx.appdo.flow.entity.LoginUserInfo;
import org.zywx.appdo.utils.PropertyTools;

/**
 * 登录跳转页面
 * 
 * @author xingshen.zhao
 *
 */
@Controller
@RequestMapping(value = "/initLogin")
public class InitLoginController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "init", method = RequestMethod.POST)
	@ResponseBody
	public String init(@RequestBody LoginUserInfo users, HttpServletRequest request) {
		String retStr = "";
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", users);
		retStr = PropertyTools.getPropertyByKey("homeSite") + users.getSuffix();
		return (String) retStr;
	}
}
