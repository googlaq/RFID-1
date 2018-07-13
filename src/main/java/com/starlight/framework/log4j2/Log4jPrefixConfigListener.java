package com.starlight.framework.log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.Assert;

public class Log4jPrefixConfigListener implements ServletContextListener {

    public static final String WEB_APP_ROOT_KEY_PARAM = "webAppRootKey";

    public static final String LOG4J_PREFIX_KEY = "log4jPrefix";

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.getProperties().remove(LOG4J_PREFIX_KEY);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext servletContext = event.getServletContext();
        Assert.notNull(servletContext, "ServletContext must not be null");

        String param = servletContext.getInitParameter(WEB_APP_ROOT_KEY_PARAM);
        if (param != null && !param.equalsIgnoreCase("") && param.indexOf(".") > 0) {
            System.setProperty("log4jPrefix", param.substring(0, param.indexOf('.')));
        } else {
            System.setProperty("log4jPrefix", "starlight");
        }
    }

}
