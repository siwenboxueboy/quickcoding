package com.quickcode.print;

import com.quickcode.builder.Builder;
import com.quickcode.entity.ListNode;
import com.quickcode.entity.TreeNode;

import java.util.*;

/**
 * @author 王斯博
 * 2022-01-18 14:26
 */
public class Console {

    private Console() {
    }

    // n
    public final static int bold = 1;
    public final static int normal = 2;
    public final static int italics = 3; // 斜体
    public final static int underline = 4; // 下划线

    // code
    public final static int red = 31;
    public final static int green = 32;
    public final static int yellow = 33;
    public final static int blue = 34;
    public final static int pink = 35;

    //background Code
    // ...

    public static void log() {

    }

    /**
     * 数组,集合类型,基本类型的输出
     *
     * @param obj 传入要输出的对象
     */
    public static void log(Object obj) {
        System.out.println(parse(obj));
    }

    /**
     * 数组,集合类型,基本类型的输出 <br>
     * 占位符: {}
     *
     * @param obj  传入要输出的对象
     * @param objs 传入要输出的参数
     */
    public static void log(Object obj, Object... objs) {
        System.out.println(Objects.nonNull(objs) ? load(parse(obj), objs) : parse(obj));
    }

    public static void logWithColor(Object obj, int code, int n) {
        printSingleColor(code, n, obj);
    }

    public static void logWithColor(Object obj, int code, int n, Object... objs) {
        printSingleColor(code, n, Objects.nonNull(objs) ? load(parse(obj), objs) : parse(obj));
    }

    /**
     * @param code    颜色代号：背景颜色代号(41-46)；前景色代号(31-36)
     * @param n       数字+m：1加粗；3斜体；4下划线
     * @param content 要打印的内容
     */
    private static void printSingleColor(int code, int n, Object content) {
        System.out.format("\33[%d;%dm%s", code, n, content);
    }

    /**
     * 数组,集合类型,基本类型的输出
     */
    public static String parse(Object obj) {
        if (obj == null) return "null";
        if (obj.getClass().isArray()) {
            // 转成二维数组,这样可以使用deepToString去输出
            Object[] out = {obj};
            String string = Arrays.deepToString(out);
            return string.substring(1, string.length() - 1);
        }
        if (obj instanceof Map) {
            StringBuilder builder = new StringBuilder();
            builder.append("[ ");
            ((Map<?, ?>) obj).forEach((k, v) -> builder.append("k: " + parse(k) + "   v: " + parse(v) + " \n"));
            builder.append(" ]");
            return builder.toString();
        }
        if (obj instanceof TreeNode) {
            // 树的遍历
            StringBuilder builder = new StringBuilder();
            builder.append("[ ");
            bfs((TreeNode) obj, builder);
            builder.append(" ]");
            return builder.toString();
        }
        if (obj instanceof ListNode) {
            // 链表的遍历
            StringBuilder builder = new StringBuilder();
            builder.append("[ ");
            traverse((ListNode) obj, builder);
            builder.append("]");
            return builder.toString();
        }
        // 普通输出 集合输出
        return obj.toString();
    }

    private static void traverse(TreeNode node, StringBuilder builder) {
        if (node == null) return;
        traverse(node.left, builder);
        builder.append(node.val).append(", ");
        traverse(node.right, builder);
    }

    private static void traverse(ListNode node, StringBuilder builder) {
        if (node == null) return;
        builder.append(node.val).append(", ");
        traverse(node.next, builder);
    }

    private static void bfs(TreeNode node, StringBuilder builder) {
        if (node == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode frontNode = queue.poll();
            if (frontNode == null) {
                builder.append("null, ");
                continue;
            } else {
                builder.append(frontNode.val).append(", ");
            }
            queue.offer(frontNode.left);
            queue.offer(frontNode.right);
            boolean canReturn = true;
            for (TreeNode treeNode : queue) {
                if (treeNode != null) {
                    canReturn = false;
                    break;
                }
            }
            if (canReturn) {
                return;
            }
        }
    }

    private static String load(String str, Object... objs) {
        int i = 0;
        while (str.indexOf("{}") >= 0 && i < objs.length) {
            str = str.replaceFirst("\\{\\}", parse(objs[i++]));
        }
        return str.replaceAll("\\{\\}", "");
    }

}
