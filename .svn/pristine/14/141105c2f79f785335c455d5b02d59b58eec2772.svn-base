package com.wanma.ims.util;

import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * base64转码工具
 * xyc
 */
public class Base64EncodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(Base64EncodeUtil.class);

    public static String getUrlDecodedString(String str, String encoding) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        try {
            return new String(Base64.decodeBase64(str.getBytes(encoding)));
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("decode string |" + str + " | " + encoding + " | failed");
            throw new RuntimeException("getUrlDecodedString error|" + str);
        }
    }

    public static String getUrlEncodedString(String str, String encoding) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        try {
            return Base64.encodeBase64String(str.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("encoded string |" + str + " | " + encoding + " | failed");
            throw new RuntimeException("getUrlEncodedString error|" + str);
        }
    }
}
