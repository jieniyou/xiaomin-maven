package com.xiaomin.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Auther: XiaoMin
 * @Date: 2022/9/21 09:39
 * @Description:
 */
public class CopyUtil {

    private final static String LIST_STR = "java.util.List";
    private final static String SERIAL_FIELD_STR = "serialVersionUID";
    private final static int LIST_STR_LEN = "java.util.List".length();

    @SuppressWarnings("all")
    public static <T> T copy(Object fromBean, Class<T> clz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            NoSuchFieldException, ClassNotFoundException, InstantiationException {
        if (fromBean == null || clz == null) {
            return null;
        }
        if (isNormalField(clz)) {
            if (fromBean.getClass() == clz) {
                // 类型一致
                return (T) fromBean;
            }
            throw new IllegalArgumentException("this fromBean is not " + clz + " type");
        }
        return copy(fromBean, clz.newInstance());
    }

    public static <T> List<T> copy(Collection<?> fromBeans, Class<T> cls)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            NoSuchFieldException, ClassNotFoundException, InstantiationException {
        if (CollectionUtil.isEmpty(fromBeans) || null == cls) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object fromBean : fromBeans) {
            T t = copy(fromBean, cls);
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * @Author XiaoMin
     * @Description 要求 bean 中对应字段的属性名必须一致，普通类型字段要求字段类型一致
     * @Date 10:12 2022/9/21
     * @Param [fromBean, toBean] 从 fromBean 复制到 toBean
     * @return E
     **/
    @SuppressWarnings("all")
    public static <T, E> E copy(Object fromBean, E toBean)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            NoSuchFieldException, ClassNotFoundException, InstantiationException {
        if (fromBean == null || toBean == null) return null;
        Class<?> fromClass = fromBean.getClass();
        Class<?> toClass = toBean.getClass();
        for (Field field : fromClass.getDeclaredFields()) {
            if (field.getName().equals(SERIAL_FIELD_STR)) {
                // 序列化字段，忽略
                continue;
            }
            try {
                if (toClass.getDeclaredField(field.getName()) == null) {
                    // toClass 中没有该属性字段
                    continue;
                }
                if (isNormalField(field.getType())
                        && toClass.getDeclaredField(field.getName()).getType() != field.getType()) {
                    // 属性是普通字段，且二者对应属性字段的类型不同
                    continue;
                }
            } catch (NoSuchFieldException e) {
                continue;
            }
            String getMethodName = "get" + StringUtils.capitalize(field.getName());
            String setMethodName = "set" + StringUtils.capitalize(field.getName());
            String isMethodName = "is" + StringUtils.capitalize(field.getName());
            Object fieldValue;
            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                // boolean 类型
                fieldValue = fromClass.getDeclaredMethod(isMethodName).invoke(fromBean);
            } else {
                fieldValue = fromClass.getDeclaredMethod(getMethodName).invoke(fromBean);
            }
            if (null == fieldValue) {
                // fromBean 无此属性值
                continue;
            }
            if (isNormalField(field.getType())) {
                Method method = toClass.getDeclaredMethod(setMethodName, field.getType());
                method.invoke(toBean, fieldValue);
            } else if (field.getType() == List.class) {
                List<T> list = (List<T>) fieldValue;
                String str = toClass.getDeclaredField(field.getName()).getGenericType().toString();
                List<Object> toList = getFieldIfList(list, str);
                Method method = toClass.getDeclaredMethod(setMethodName, List.class);
                method.invoke(toBean, toList);
            } else if (field.getType() == Map.class){
                // TODO Map 暂不处理
            } else {
                // bean 中 bean
                Class<?> newToClass = toClass.getDeclaredField(field.getName()).getType();
                Object newToBean = newToClass.newInstance();
                copy(fieldValue, newToBean);
                Method method = toClass.getDeclaredMethod(setMethodName, newToClass);
                method.invoke(toBean, newToBean);
            }
        }
        return toBean;
    }

    /**
     * @Author XiaoMin
     * @Description 获取当源属性类型是 {@link List} 时的目标属性值
     * @Date 12:13 2022/9/22
     * @Param [list, toBeanClassStr]
     * @return java.util.List<java.lang.Object>
     **/
    @SuppressWarnings("all")
    private static <T, E> List<Object> getFieldIfList(List<T> list, String toBeanClassStr)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException,
            NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        String newBeanClassStr = toBeanClassStr.substring(LIST_STR_LEN + 1,
                toBeanClassStr.length() - 1);
        if (newBeanClassStr.contains(LIST_STR)) {
            List<Object> result = new ArrayList<>();
            for (T t : list) {
                // 递归去掉 list 外壳
                List<Object> toT = getFieldIfList((List<E>) t, newBeanClassStr);
                result.add(toT);
            }
            return result;
        }
        Class<?> toBeanClass = Class.forName(newBeanClassStr);
        List<Object> toList = new ArrayList<>();
        if (isNormalField(toBeanClass)) {
            // 说明 list 里面的元素是一般属性字段
            toList.addAll(list);
        } else {
            for (T t : list) {
                Object toT = copy(t, toBeanClass.newInstance());
                toList.add(toT);
            }
        }
        return toList;
    }

    /**
     * @Author XiaoMin
     * @Description 返回该字段的类型是否是普通类型
     * @Date 10:14 2022/9/21
     * @Param [clazz]
     * @return boolean
     **/
    private static boolean isNormalField(Class clazz) {
        return clazz == int.class || clazz == String.class || clazz == float.class
                || clazz == double.class || clazz == char.class || clazz == byte.class
                || clazz == short.class || clazz == Date.class || clazz == boolean.class
                || clazz == Integer.class || clazz == Float.class
                || clazz == Double.class || clazz == Character.class || clazz == Byte.class
                || clazz == Short.class || clazz == Boolean.class
                || clazz == long.class || clazz == Long.class;
    }
}
