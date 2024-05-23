package com.devteria.profile.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.annotation.Nullable;
import org.codehaus.jackson.JsonProcessingException;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {

    public static boolean notNullOrEmpty(String text){
        return !nullOrEmpty(text);
    }
    public static boolean notNullOrEmpty(Collection collection){
        return !nullOrEmpty(collection);
    }
    public static boolean notNullOrEmpty(byte[] bytes){
        return bytes != null && bytes.length > 0;
    }
    public static boolean nullOrEmpty(Collection objects){
        return objects == null || objects.isEmpty();
    }
    public static boolean nullOrEmpty(Map<?, ?> map){
        return map == null || map.isEmpty();
    }
    public static boolean notNull(Collection collection){
        return collection != null;
    }
    public static boolean notNull(Map<?, ?> map){
        return map == null || map.isEmpty();
    }

    public static boolean notNull(Object object) {
        return nullObject(object);
    }
    public static boolean nullObject(Object object) {
        return object == null;
    }
    public static boolean nullOrZero (Long value) {
        return (value == null || value.equals(0L));
    }
    public static boolean nullOrZero(String value) {
        return value == null || "0".equals(value);
    }
    public static boolean nullOrZero(Integer value){
        return (value == null || value.equals(0));
    }
    public static String getErrorMessage (Exception e) {
        String message = "\n" +
        "Error in Business Validate Task " + " at Line " + e.getStackTrace()[1].getLineNumber() + e.getMessage();
        return message;
    }

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException (message);
        }
    }
    public static boolean equalsObj(Object obj1, Object obj2) {
        if (obj1 == null || obj2 == null) return false;
        return obj1.equals(obj2);
    }
    public static boolean validateOnlyCharacterAndNumber (String input) {
        String pattern = "\\w++";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher (input);
        return m.matches();
    }
    public static boolean validateSpecialCharacter (String input) {
        String pattern = "\\W";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher (input);
        List<String> lst = Arrays.asList("\\", "-", "_");
        while (m.find()) {
            if (!lst.contains(m.group())) {
                return false;
            }
        }
        return true;
    }
    public static Integer parseToInt(String value, Integer defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultVal;
        }
    }
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }
    public static boolean nullOrEmpty(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(CharSequence cs) {
        return nullOrEmpty(cs);
    }
    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    public static String objectToJson(Object obj) throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (obj == null) {
            return null;
        }
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    public static <T> List<T> jsonToList(String json, Class<T> classOutput) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return objectMapper.readValue(json, typeFactory.constructCollectionType(List.class, classOutput));
    }

    public static <T> T jsontoObject(String json, Class<T> classOutput) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (json == null || json.isEmpty()) {
            return null;
        }
        return mapper.readValue(json, classOutput);
    }

    public static LocalDate convertToLocalDateViaMiliSecond(Date dateToConvert){
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public static boolean isNullOrEmpty(String value){
        return value == null || value.trim().isEmpty();
    }
    public static boolean isNotNumber(String s){
        if (nullOrEmpty(s)) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String s){
        if (nullOrEmpty(s)) {
            return false;
        }
        BigDecimal bigDecimal = new BigDecimal(s);
        return bigDecimal.compareTo(BigDecimal.ZERO) >= 0;
    }
















}
