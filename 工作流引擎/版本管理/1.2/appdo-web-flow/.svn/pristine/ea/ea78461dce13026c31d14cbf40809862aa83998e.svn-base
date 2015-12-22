package org.zywx.appdo.flow.util;

import org.activiti.engine.identity.User;
import org.zywx.appdo.flow.entity.LoginUserInfo;

import javax.servlet.http.HttpSession;

/**
 * 用户工具类
 *
 */
public class UserUtil {

    public static final String USER = "userInfo";

    /**
     * 设置用户到session
     *
     * @param session
     * @param user
     */
    public static void saveUserToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
    }

    
    /**
     * 从Session获取当前用户信息
     *
     * @param session
     * @return
     */
    public static LoginUserInfo getUserFromSession(HttpSession session) {
        Object attribute = session.getAttribute(USER);
        return attribute == null ? null : (LoginUserInfo) attribute;
    }
    
    /**
     *
     */

}
