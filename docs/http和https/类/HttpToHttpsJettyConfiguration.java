package com.asiainfo.aifgw.security.oauth2;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/12/23 23:16
 */

public class HttpToHttpsJettyConfiguration extends AbstractConfiguration {
    // http://wiki.eclipse.org/Jetty/Howto/Configure_SSL#Redirecting_http_requests_to_https
    @Override
    public void configure(WebAppContext context) throws Exception {
        Constraint constraint = new Constraint();
        constraint.setDataConstraint(2);

        ConstraintMapping constraintMapping = new ConstraintMapping();
        constraintMapping.setPathSpec("/*");
        constraintMapping.setConstraint(constraint);

        ConstraintSecurityHandler constraintSecurityHandler = new ConstraintSecurityHandler();
        constraintSecurityHandler.addConstraintMapping(constraintMapping);

        context.setSecurityHandler(constraintSecurityHandler);
    }
}