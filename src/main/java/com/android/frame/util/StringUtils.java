package com.android.frame.util;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     author: yangshenglong
 *     time  : 2016/8/16
 *     desc  : 字符串相关工具类
 * </pre>
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 判断字符串是否为null或长度为0
     *
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(String string) {
        if (string != null && string.trim().length() > 0) {
            return false;
        }
        return true;
    }


    public static String toMD5String(String string) {

        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};

        byte[] btInput = string.getBytes();
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 0xf];
                str[k++] = md5String[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static String genNewMsgCountString(int newMsgCount) {
        if (newMsgCount != 0) {
            if (newMsgCount > 99) {
                return "99+";
            } else {
                return "" + newMsgCount;
            }
        } else {
            return "";
        }
    }

    /**
     * 过滤字符串的前后空格以及所有的回车换行符
     *
     * @param src
     * @return
     */
    public static String trim(String src) {
        src = src.trim(); //先过滤掉前后的空格

        Pattern pattern = Pattern.compile("(\r\n|\r|\n|\n\r)");
        //正则表达式的匹配一定要是这样，否则单个替换\r|\n的时候会错误
        Matcher matcher = pattern.matcher(src);
        String dest = matcher.replaceAll(" "); //将回车或者换行符替换为空字符串

        return dest;
    }

    public static boolean verifyPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(phoneNumber);

        return m.matches();
    }

    /**
     * 字符串转换成十六进制值
     *
     * @param bin String 我们看到的要转换成十六进制的字符串
     * @return
     */
    public static String bin2hex(String bin) {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     *
     * @param hex String 十六进制
     * @return String 转换后的字符串
     */
    public static String hex2bin(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return new String(bytes);
    }

    /**
     * java字节码转字符串
     *
     * @param b
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String byte2hex(byte[] b) { //一个字节的数组，

        // 转成16进制字符串

        String hs = "";
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            //整数转成十六进制表示

            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                hs = hs + "0" + tmp;
            } else {
                hs = hs + tmp;
            }
        }
        tmp = null;
        return hs.toUpperCase(); //转成大写

    }

    /**
     * 字符串转java字节码
     *
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节

            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        b = null;
        return b2;
    }

    public static int genMinusUniqueId() {
        long l = System.currentTimeMillis();
        String s = String.valueOf(l);
        int i = -Integer.parseInt(s.substring(4));

        return i;
    }

    /**
     * 在字符串特定位置插入字符串
     *
     * @param src      原字符串
     * @param dec      插入字符串
     * @param position 插入位置
     * @return
     */
    public static String insertStringInParticularPosition(String src, String dec, int position) {
        StringBuffer stringBuffer = new StringBuffer(src);

        return stringBuffer.insert(position, dec).toString();

    }

    public static BigDecimal digitalDisplay(Object value) {
        BigDecimal digital = convToDecimal(value);
        BigDecimal digitalDisp = new BigDecimal("0");
        if (digital.compareTo(new BigDecimal("0")) == 0) {
            return digital;
        }

        // 大于亿的场合,以亿为单位;大于万的以万为单位;否则以元为单位
        if (digital.compareTo(new BigDecimal("100000000")) >= 0) {
            digitalDisp = divide(digital, new BigDecimal("100000000")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        } else if (digital.compareTo(new BigDecimal("10000")) >= 0) {
            digitalDisp = divide(digital, new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        } else {
            digitalDisp = digital;
        }

        return digitalDisp;
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null || divisor == null) {
            throw new RuntimeException("不能除 NULL值。");
        }
        BigDecimal result = null;

        try {
            result = dividend.divide(divisor);
        } catch (ArithmeticException ex) {
            result = dividend.divide(divisor, 29, BigDecimal.ROUND_HALF_EVEN);
        }

        return result;
    }

    /**
     * 转换值为BigDecimal型。
     *
     * @return BigDecimal型
     */
    public static BigDecimal convToDecimal(Object value) {
        BigDecimal dec = new BigDecimal("0");
        if (value == null) {
            return dec;
        } else if (value instanceof BigDecimal) {
            dec = (BigDecimal) value;
        } else {
            try {
                dec = new BigDecimal(String.valueOf(value).trim().replace(",", ""));
            } catch (Exception ex) {
                return dec;
            }
        }
        return dec;
    }

    public static String digitalUnitDisplay(BigDecimal digital) {
        String digitalStyleDisp = "元";
        if (digital.compareTo(new BigDecimal("0")) == 0) {
            return digitalStyleDisp;
        }

        // 大于亿的场合,以亿为单位;大于万的以万为单位;否则以元为单位
        if (digital.compareTo(new BigDecimal("100000000")) >= 0) {
            digitalStyleDisp = "亿";
        } else if (digital.compareTo(new BigDecimal("10000")) >= 0) {
            digitalStyleDisp = "万";
        } else {
            digitalStyleDisp = "元";
        }

        return digitalStyleDisp;
    }

    public static String convToMoney(Object pValue) {
        return String.format("%1$,.2f", convToDecimal(pValue));
    }
    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }
}