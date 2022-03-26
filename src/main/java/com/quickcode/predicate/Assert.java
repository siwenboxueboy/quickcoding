package com.quickcode.predicate;

import com.quickcode.builder.Builder;
import com.quickcode.entity.ListNode;
import com.quickcode.entity.TreeNode;
import com.quickcode.print.Console;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author 王斯博
 * 2022-03-08 13:56
 */
public class Assert {

    private Assert(){
    }

    // todo List支持可以对乱序的List进行比较

    public static <T, S> void check(T obj1, S obj2, BiPredicate<T, S> predicate) {
        if (predicate.test(obj1, obj2)) {
            Console.logWithColor("=========================== 结果 [正确] ===========================\n{}\n{}\n", Console.green, Console.normal, obj1, obj2);
        } else {
            Console.logWithColor("=========================== 结果 [异常] ===========================\n{}\n{}\n", Console.red, Console.normal, obj1, obj2);
        }
    }

    // 空方法,用于代码模板
    public static void checkEqual() {
    }

    public static <T> void checkEqual(List<T> list, T... element) {
        List<T> collect = Arrays.stream(element).collect(Collectors.toList());
        check(list, collect, List::equals);
    }

    public static <T> void checkEqualDisorder(List<T> list, T... element) {
        List<T> collect = Arrays.stream(element).collect(Collectors.toList());
        check(list, collect, Assert::equalsDisorder);
    }

    public static void checkEqual(ListNode listNode, int... listNodes) {
        ListNode node = Builder.buildListNode(listNodes);
        check(listNode, node, Assert::traverse);
    }

    public static void checkEqual(List<String> list, String str) {
        List<String> stringList = Builder.buildList(str);
        check(list, stringList, List::equals);
    }

    public static void checkEqualDisorder(List<String> list, String str) {
        List<String> stringList = Builder.buildList(str);
        check(list, stringList, Assert::equalsDisorder);
    }

    private static <E, T extends Collection<E>> boolean equalsDisorder(T t1, T t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.containsAll(t2) && t2.containsAll(t1);
    }

    private static boolean traverse(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null && listNode2 == null) return true;
        if (listNode1 == null || listNode2 == null) return false;
        return listNode1.val == listNode2.val && traverse(listNode1.next, listNode2.next);
    }

    public static void checkEqual(TreeNode treeNode, Integer... treeNodes) {
        TreeNode node = Builder.buildTreeNode(treeNodes);
        check(treeNode, node, Assert::preTraverse);
    }

    private static boolean preTraverse(TreeNode treeNode1, TreeNode treeNode2) {
        if (treeNode1 == null && treeNode2 == null) return true;
        if (treeNode1 == null || treeNode2 == null) return false;
        return treeNode1.val == treeNode2.val && preTraverse(treeNode1.left, treeNode2.left) && preTraverse(treeNode1.right, treeNode2.right);
    }

    public static void checkEqual(int[] array1, int... array2) {
        check(array1, array2, Objects::deepEquals);
    }

    public static void checkEqual(int[][] array1, String array2) {
        int[][] ints = Builder.buildDimensionalIntsArray(array2);
        check(array1, ints, Objects::deepEquals);
    }

    public static void checkEqual(char[][] array1, String array2) {
        char[][] chars = Builder.buildDimensionalCharsArray(array2);
        check(array1, chars, Objects::deepEquals);
    }
}
