package com.consult.utils;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean areAllEmpty(String... strs) {
        for (String str : strs) {
            if (!isEmpty(str)) return false;
        }
        return true;
    }
}
