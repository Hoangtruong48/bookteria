package com.devteria.identity.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public abstract class MessageUtils {
    private final static String BASE_NAME = "messages";
    public static String getMessage(String code, Locale locale) {
        return getMessage(code, locale, null);
    }

    public static String getMessage(String code, Locale locale, Object... args){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
        String message;
        try {
            message = resourceBundle.getString(code);
            message = MessageFormat.format(message, args);
        } catch (Exception ex){
            log.info(">>> Can not get message for code {}", code);
            log.info(ex.getMessage());
            message = code;
        }
        return message;
    }

    public static String getMessage(String code){
        return getMessage(code, LocaleContextHolder.getLocale(), null);
    }

    public static String getMessage(String code, Object... args){
        return getMessage(code, LocaleContextHolder.getLocale(), args);
    }

}
