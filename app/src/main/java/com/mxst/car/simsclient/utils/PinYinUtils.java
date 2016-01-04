package com.mxst.car.simsclient.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;


/**
 * 将汉字转换为拼音
 *
 * @author xiaozhifeng
 */
public class PinYinUtils {

    public static String getPinyin(String string) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 设置汉语拼音为大写
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 设置汉语拼音不显示声调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        // 将传入的汉字转化为char数组
        char[] cs = string.toCharArray();
        // 记录每一个字符
        String s = "";
        // 拼接所有的字符
        StringBuffer buffer = new StringBuffer();
        // 遍历所有的字符
        for (int i = 0; i < cs.length; i++) {

            char c = cs[i];
            // 检查是否是空格
            if (Character.isWhitespace(c)) {
                continue;
            }

            if (c > -127 && c < 128) {
                buffer.append(c);
            } else {
                try {
                    s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    buffer.append(s);
                } catch (Exception e) {
                    e.printStackTrace();
                    buffer.append(s);
                }
            }

        }

        return buffer.toString();

    }
}
