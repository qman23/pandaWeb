package com.shiro.realm;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.security.User;
import com.login.bussiness.UserService;

public class PandaRealm extends AuthorizingRealm {

	static Logger logger = Logger.getLogger(PandaRealm.class.getName());

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String currentUsername = (String) super
				.getAvailablePrincipal(principals);
		logger.debug("Begin get user role and permission from database-----------------");
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		if (null != currentUsername) {
			simpleAuthorInfo.addRole("admin");
			logger.debug("Begin add user role admin -----------------");
			//simpleAuthorInfo.addStringPermission("admin:worker");
		}
		logger.debug("Get user role and permission from database compelate-----------------");
		return simpleAuthorInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		logger.debug("--------------Current get token user:" + token.toString()
				+ "---------------");
		User user = userService.findByEmail(token.getUsername());
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
					user.getEmail(), user.getPassword(), this.getName());
			setSession("CurrentUser", user.getEmail());
			return authcInfo;
		}
		return null;
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}
