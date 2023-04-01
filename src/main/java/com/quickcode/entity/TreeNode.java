package com.quickcode.entity;

/**
 * @author 王斯博
 * 2021-09-09 16:55
 */
public class TreeNode {
    public int val;
    /**
     * 当节点表示二叉树时,该属性表示左子树
     */
    public TreeNode left;
    /**
     * 当节点表示二叉树时,该属性表示右子树
     */
    public TreeNode right;

    /**
     * 当节点表示多叉树时,该节点表示多叉树孩子节点
     */
    public TreeNode[] children;

    public TreeNode() {
    }

    public  TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
