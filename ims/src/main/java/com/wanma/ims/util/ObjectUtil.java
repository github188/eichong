package com.wanma.ims.util;

import java.math.BigDecimal;
import java.util.Collection;

public final class ObjectUtil {

    public static boolean isEmpty(Object obj) {
        return !(isNotEmpty(obj));
    }

    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Number) {
            long num = ((Number) obj).longValue();
            return num > 0;
        }
        if (obj instanceof String) {
            String arg = (String) obj;
            return arg.trim().length() > 0;
        }

        if (obj instanceof Collection<?>) {
            Collection<?> list = (Collection<?>) obj;
            return !list.isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] arg = (Object[]) obj;
            return arg.length > 0;
        }
        return obj != null;
    }

    public static String nvlStrEmpty(Object obj) {
        return obj != null ? (obj.toString()) : "";
    }

    public static String emptyStrNvl(Object obj) {
        if (obj == null) {
            return null;
        }
        
        return obj != "" ? (obj.toString()) : null;
    }

    public static String nvlStrZero(Object obj) {
        return obj != null ? (obj.toString()) : "0";
    }

    public static Long nvlLong(Object obj) {
        return obj != null ? (Long.parseLong(obj.toString())) : 0L;
    }

    public static Integer nvlInteger(Object obj) {
        return obj != null ? (Integer.parseInt(obj.toString())) : 0;
    }

    public static String getValue(Object obj) {
        if (obj == null) {
            return "";
        }
        if ((obj instanceof BigDecimal)) {
            return String.valueOf(obj.toString());
        }
        if ((obj instanceof Integer)) {
            return String.valueOf(obj.toString());
        }
        if ((obj instanceof Long)) {
            return String.valueOf(obj.toString());
        }
        if ((obj instanceof String)) {
            if (isEmpty(obj.toString())) {
                return "";
            }
            return obj.toString();
        }
        return "";
    }
}