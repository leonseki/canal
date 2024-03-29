package com.alibaba.otter.canal.admin.connector;

import java.util.function.Function;

import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.alibaba.otter.canal.admin.config.SpringContext;

public class SimpleAdminConnectors {

    private static final Logger logger = LoggerFactory.getLogger(SimpleAdminConnectors.class);

    public static <R> R execute(String ip, int port, Function<AdminConnector, R> function) {
        Environment env = (Environment) SpringContext.getBean(Environment.class);
        String defaultUser = env.getProperty("canal.adminUser", "admin");
        String defaultPasswd = env.getProperty("canal.adminPasswd", "admin");

        return execute(ip, port, defaultUser, defaultPasswd, function);
    }

    public static <R> R execute(String ip, int port, String user, String passwd, Function<AdminConnector, R> function) {
        SimpleAdminConnector connector = new SimpleAdminConnector(ip, port, user, passwd);
        try {
            connector.connect();
            return function.apply(connector);
        } catch (Exception e) {
            logger.error("connect to ip:{},port:{},user:{},password:{}, failed",
                    ip, port, user, getDesensitizationPassword(passwd));
            logger.error(e.getMessage());
        } finally {
            connector.disconnect();
        }

        return null;
    }

    private static String getDesensitizationPassword(String password) {
        String defaultPassword = "*****";

        if (Strings.isNullOrEmpty(password) || password.length() < 5) {
            return defaultPassword;
        } else {
            return String.format("%s*****", password.substring(0, 3));
        }
    }
}
