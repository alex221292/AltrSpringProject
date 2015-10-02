package com.altr.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoreTools {
    private static final Logger logger = LoggerFactory.getLogger(CoreTools.class);

    public static String getSymbol(int i, String text) {
        return text.substring(i, i + 1);
    }

    public static boolean isHidden(String text) {
        boolean result = false;
        if ("1".equals(getSymbol(0, text))) result = true;
        return result;
    }

    public static boolean isReadOnly(String text) {
        boolean result = false;
        if ("1".equals(getSymbol(1, text))) result = true;
        return result;
    }

    public static boolean isCalculable(String text) {
        boolean result = false;
        if ("1".equals(getSymbol(2, text))) result = true;
        return result;
    }

    public static void getPass(){
        String password = "123456";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        logger.info("Password: "+encodedPassword);
    }

    public static boolean isEmpty(String text){
        boolean result = false;
        if (text == null || "".equals(text)) result = true;
        return result;
    }

    public static String getCalculateQuery(String properties){
        String result = "";
        Pattern pattern = Pattern.compile("query='(.*)'");
        Matcher matcher = pattern.matcher(properties);
        if(matcher.find()){
            int count = matcher.groupCount();
            for(int i=1;i<=count;i++){
                result = matcher.group(i);
            }
        }
        return result;
    }

}
