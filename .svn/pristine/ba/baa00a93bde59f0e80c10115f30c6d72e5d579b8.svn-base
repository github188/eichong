package com.wanma.web.support.utils;

import com.wanma.model.TblCity;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 2015/4/3.
 */
public class PinYin4jUtil {

        /**
         * 首字母大写
         * @param chinese
         * @return
         */
        public static String getFirstLetter(String chinese) {
            if (chinese == null) {
                return null;
            }
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
            format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v
            try {
                if(chinese.length()>1){
                    chinese = chinese.substring(0,1);
                }
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(0), format);
                String s = array[0];// 不管多音字,只取第一个
                char c = s.charAt(0);// 大写第一个字母
                return String.valueOf(c).toUpperCase();
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            return null;
        }

    public static void main(String[] args) {
        PinyinHelper.toHanyuPinyinStringArray('a');
    }
}
