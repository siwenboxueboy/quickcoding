package com.quickcode.entity;

/**
 * @description: 链表节点
 * @author: 王斯博
 * @date: 2021-08-23 10:25
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
