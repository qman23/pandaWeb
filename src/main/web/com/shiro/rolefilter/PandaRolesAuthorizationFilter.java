package com.shiro.rolefilter;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 
 * @author Allen
 * 自己实现role控制代替shiro中的filter
 */
public class PandaRolesAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            //no roles specified, so nothing to check - allow access.
            return true;
        }
        Set<String> roles = CollectionUtils.asSet(rolesArray);
        Iterator<String> roleIterator=roles.iterator();
        boolean flag=false;
        while(roleIterator.hasNext()){
        	if( subject.hasRole(roleIterator.next())){
        		flag=true;
        	}
        }
		return flag;
	}

}
