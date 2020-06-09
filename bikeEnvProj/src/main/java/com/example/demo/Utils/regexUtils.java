package com.example.demo.Utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class regexUtils {
    public boolean isContainChinese( String str) {
        String regex = "[\u4e00-\u9fa5]";   //汉字的Unicode取值范围
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        return match.find();
    }

}
