package com.quickcode.builder;

import com.quickcode.entity.ListNode;
import com.quickcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王斯博
 * 2022-01-19 15:46
 */
public class Builder {

    private Builder() {
    }

    /**
     * 占用空间少
     * */
    public static List<Integer>[] graphList(int size) {
        return new List[size];
    }

    /**
     * 可快速判断两个节点是否相邻
     * */
    public static boolean[][] graphArray(int size) {
        return new boolean[size][size];
    }

    /**
     * @param str 二维数组字符串,形如 [[1,2,3],[4,5,6],[7,8,9]]
     */
    public static int[][] buildDimensionalIntsArray(String str) {
        if (str.indexOf("[[") >= 0) {
            str = str.substring(2, str.length() - 2);
        }
        String[] splitStr = str.split("],\\[");
        int[][] res = new int[splitStr.length][];
        for (int i = 0; i < splitStr.length; i++) {
            res[i] = buildInt(splitStr[i]);
        }
        return res;
    }

    public static char[][] buildDimensionalCharsArray(String str) {
        if (str.indexOf("[[\"") >= 0) {
            str = str.substring(3, str.length() - 3);
        }
        String[] splitStr = str.split("\"],\\[\"");
        char[][] res = new char[splitStr.length][];
        for (int i = 0; i < splitStr.length; i++) {
            res[i] = buildChar(splitStr[i]);
        }
        return res;
    }

    public static List<String> buildList(String str) {
        if (str.indexOf("[\"") >= 0) {
            str = str.substring(2, str.length() - 2);
        }
        String[] split = str.split("\",\"");
        return Arrays.stream(split).collect(Collectors.toList());
    }

    public static char[] buildChar(String str) {
        if (str.indexOf("[\"") >= 0) {
            str = str.substring(2, str.length() - 2);
        }

        String[] split = str.split("\",\"");
        char[] res = new char[split.length];
        for (int i = 0; i < split.length; i++) {
            char c = split[i].charAt(0);
            res[i] = c;
        }
        return res;
    }

    public static int[] buildInt(String str) {
        if (str.indexOf('[') >= 0) {
            str = str.substring(1, str.length() - 1);
        }
        String[] split = str.split(",");
        int[] ints = Arrays.stream(split).mapToInt(Integer::valueOf).toArray();
        return ints;
    }

    public static int[] buildInt(int... ints) {
        return ints;
    }

    public static ListNode buildListNode(int... values) {
        if (values == null || values.length == 0) return null;
        return buildListNode(values, 0);
    }

    private static ListNode buildListNode(int[] values, int index) {
        if (index >= values.length) return null;
        ListNode node = new ListNode(values[index]);
        node.next = buildListNode(values, ++index);
        return node;
    }

    public static TreeNode buildTreeNode(Integer... values) {
        ArrayList<Integer> valueList = new ArrayList<>(values.length);
        for (Integer value : values) {
            valueList.add(value);
        }
        return buildTreeNode(valueList, 0);
    }

    // 完全二叉树构造方法
    private static TreeNode buildTreeNode(ArrayList<Integer> valueList, int index) {
        if (index >= valueList.size()) return null;
        Integer value = valueList.get(index);
        // 为什么要这样优化？
        //
        if (value == null) {
            // 要加入子节点的null值 确保有空间容纳 不然会指针越界异常
            valueList.ensureCapacity(valueList.size() + 2);
            int left = 2 * index + 1;
            if (left < valueList.size()) {
                valueList.add(left, null);
            }
            int right = left + 1;
            if (right < valueList.size()) {
                valueList.add(right, null);
            }
            return null;
        }
        TreeNode treeNode = new TreeNode(value);
        // 左子树
        treeNode.left = buildTreeNode(valueList, 2 * index + 1);
        // 右子树
        treeNode.right = buildTreeNode(valueList, 2 * index + 2);
        return treeNode;
    }

}
