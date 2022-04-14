package com.quickcode.debug;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.quickcode.print.Console.*;

/**
 * 递归,回溯算法调试输出Util
 */
public class Recursion {

    private Recursion() {
    }

    // for链式调用构造参数params
    private static final Recursion instance = new Recursion();
    // 默认栈深度
    private static final int defaultStackDeepLen = 0;
    private static final boolean defaultNeedColor = true;
    private static final boolean defaultNeedRecursion = true;
    private static final boolean defaultNeedSpace = true;
    // 栈深度
    private static int stackDeepLen = defaultStackDeepLen;
    // print 颜色开关
    private static boolean needColor = defaultNeedColor;
    // 开启递归调试模式 开关
    private static boolean needRecursion = defaultNeedRecursion;
    // 标记仅仅开头指定长度,内部字段,不允许自定义设置
    private static boolean needSpace = defaultNeedSpace;
    // 参数key列表
    private static final List<String> paramsNames = new ArrayList<>();
    // 变量追踪map,只追踪对象类型变量
    private static final Map<String, Object> trackingMap = new LinkedHashMap<>();

    // 可选择的颜色 有几种颜色 则进行几种颜色的轮换 排除红色,绿色
    private static final int[] fontColors = {
            yellow, blue, pink
    };

    public static Recursion getInstance() {
        return instance;
    }

    public Recursion setNeedRecursion(boolean needRecursion) {
        Recursion.needRecursion = needRecursion;
        return instance;
    }

    public Recursion setNeedColor(boolean needColor) {
        Recursion.needColor = needColor;
        return instance;
    }

    /**
     * 跟踪对象类型并输出
     */
    public Recursion tracking() {
        if (trackingMap == null || trackingMap.isEmpty()) {
            return instance;
        }
        trackingMap.forEach(this::print);
        return instance;
    }

    public Recursion trackingAc() {
        if (trackingMap == null || trackingMap.isEmpty()) {
            return instance;
        }
        trackingMap.forEach(this::printAc);
        return instance;
    }

    public Recursion trackingFail() {
        if (trackingMap == null || trackingMap.isEmpty()) {
            return instance;
        }
        trackingMap.forEach(this::printFail);
        return instance;
    }

    /**
     * 默认无参,仅仅支持栈深度,用于清除记录数据
     */
    public Recursion push() {
        needSpace = defaultNeedSpace;
        ++stackDeepLen;
        print("push\n");
        return instance;
    }

    public Recursion pushNoNewLine() {
        needSpace = defaultNeedSpace;
        ++stackDeepLen;
        return instance;
    }

    /**
     * 用于清除记录数据
     */
    public Recursion pop() {
        needSpace = defaultNeedSpace;
        popCheck();
        --stackDeepLen;
        print("pop\n");
        return instance;
    }

    /**
     * 用于清除记录数据
     */
    public Recursion popNoNewLine() {
        needSpace = defaultNeedSpace;
        popCheck();
        --stackDeepLen;
        return instance;
    }

    /**
     * 检查不合法情况,比如先pop后push
     */
    private void popCheck() {
        if (stackDeepLen == defaultStackDeepLen) {
            logWithColor("栈深度为-1,异常", red, normal);
        }
    }

    /**
     * 用于执行多个测试用例时重置栈
     */
    public Recursion reset() {
        needSpace = defaultNeedSpace;
        needColor = defaultNeedColor;
        needRecursion = defaultNeedRecursion;
        stackDeepLen = defaultStackDeepLen;
        paramsNames.clear();
        trackingMap.clear();
        return instance;
    }

    public <T> Recursion print(String key, T value) {
        // 每次调用一个方法就要进行输出
        trackingMap.putIfAbsent(key, value);
        print(buildHeaderText(), "stack deep length is");
        print(buildText(key, value), key);
        return instance;
    }

    /**
     * 指定绿色 不受颜色启动{@code needColor}参数控制
     */
    public <T> Recursion printAc(String key, T value) {
        trackingMap.putIfAbsent(key, value);
        print(buildHeaderText(), "stack deep length is");
        CharSequence charSequence = buildText(key, value);
        logWithColor(charSequence, green, bold);
        return instance;
    }

    /**
     * 指定红色 不受颜色启动{@code needColor}参数控制
     */
    public <T> Recursion printFail(String key, T value) {
        trackingMap.putIfAbsent(key, value);
        print(buildHeaderText(), "stack deep length is");
        CharSequence charSequence = buildText(key, value);
        logWithColor(charSequence, red, underline);
        return instance;
    }

    private static <T> CharSequence buildText(String key, T value) {
        String parseValue = parse(value);
        return "『 " + key + " ☞ " + parseValue + " 』 ";
    }

    public Recursion print(CharSequence key) {
        print(key, key.toString());
        return instance;
    }

    private static void print(CharSequence charSequence, String key) {
        if (needColor) {
            printWithRotateColor(charSequence, key);
            return;
        }
        System.out.print(charSequence);
    }

    private static void printWithRotateColor(CharSequence charSequence, String key) {
        int fontColorIndex = getRotateColor(key, fontColors.length);
        logWithColor(charSequence, fontColors[fontColorIndex], normal);
    }

    private static int getRotateColor(String key, int arrayLength) {
        int paramsIndex = paramsNames.indexOf(key);
        if (paramsIndex >= 0) {
            return paramsIndex % arrayLength;
        }
        paramsNames.add(key);
        return getRotateColor(key, arrayLength);
    }

    private static CharSequence buildHeaderText() {
        // 根据栈深度 构建输出前缀(空格)
        StringBuilder spaceBuilder = new StringBuilder();
        if (needRecursion && needSpace) {
            int builderCount = stackDeepLen;
            spaceBuilder.append("stack deep length is ");
            spaceBuilder.append(builderCount);
            spaceBuilder.append(':');
            while (builderCount > defaultStackDeepLen) {
                spaceBuilder.append('.').append(' ').append(' ').append(' ').append(' ');
                builderCount--;
            }
            needSpace = !defaultNeedSpace;
        }
        return spaceBuilder;
    }

}
