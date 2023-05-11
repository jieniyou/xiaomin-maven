package com.xiaomin.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StrUtil {
    /**
     * 将字符串首字母进行大写并返回
     * @param fieldName 字符串
     * @return String
     */
    public static String getMethodName(String fieldName) {
        //获取首字母
        String first = ((Character) fieldName.charAt(0)).toString();
        return fieldName.replaceFirst(first,first.toUpperCase());
    }

    public static boolean isNotEmpty(String str){
        return str!=null&&!"".equals(str);
    }
    public static boolean isEmpty(String str){
        return !isNotEmpty(str);
    }

    public static int parseStrToInt(String str){
        //判断字符串是否为空
        if (isEmpty(str)){
            return -1;
        }
        try {
            int num=Integer.parseInt(str);
            return num;
        } catch (NumberFormatException e) {
            // 请输入合法的数值字符串
            e.printStackTrace();
        }
        return -1;
    }
    public static List<Integer> parseStrToArray(String str,String symbol){
        //检查参数
        if (isEmpty(str)||isEmpty(symbol)){
            return Collections.emptyList();
        }
        String[] strArray=str.split(symbol);
        //将集合数组转换为Stream流
        List<Integer> integerList = Arrays.stream(strArray)
                //数据的清洗
                .filter(string -> isNotEmpty(string))
                //数据的处理、类型转换
                .map(string -> parseStrToInt(string))
                //将Stream流转换为你需要的类型
                .collect(Collectors.toList());
                return integerList;
//    int[] numArray = new int[strArray.length];
//    for (int i = 0; i < strArray.length; i++) {
//      String s = strArray[i];
//      if(isEmpty(s)){
//        continue;
//      }
//      int num = Integer.parseInt(s);
//      numArray[0] = num;
//    }
//
//    return numArray;
    }
}
