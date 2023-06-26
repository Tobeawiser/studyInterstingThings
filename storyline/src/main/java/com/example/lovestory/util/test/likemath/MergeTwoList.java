package com.example.lovestory.util.test.likemath;

public class MergeTwoList {
    public static void main(String[] args) throws Exception {
        MergeTwoList mergeTwoList = new MergeTwoList();
        ListNode l1 = new ListNode();
        l1.val = 1;
        l1.next = new ListNode(2, new ListNode(4));
        ListNode l2 = new ListNode();
        l2.val = 1;
        l2.next = new ListNode(3, new ListNode(4));
        //mergeTwoList.mergeTwoLists(l1,l2);
        mergeTwoList.mergeTwoListsChange(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(-1);
        //循环构建确实有问题

        //将 l1 l2 合并 下一个不用新构造，直接用l1，或者l2的
        //l1 第一个和 l2 第一个比较，谁小谁进入新链表next中 然后
        //小的那个往后移一位，相等则都进入同时往后移一位
        //谁先移动完

        //并不是一个个赋值，二十一层层赋值
        boolean flagL1 = false;
        boolean flagL2 = false;
        while (l1 != null || l2 != null) {
            if (l1.val <= l2.val) {
                newList.next = l1;
                l1 = l1.next;
                newList = newList.next;

                newList.val = l1.val;
                newList.next = l1.next;
                l2 = l2.next;
            } else {
                newList.val = l2.val;
                newList.next = l2.next;
                l2 = l2.next;
            }
            //对下面所有空间节点进行替换
            newList = newList.next;

            if (l1 == null) {
                flagL1 = true;
            }
            if (l2 == null) {
                flagL2 = true;
            }
        }
        //1移动完成,把2往后拼接
        if (flagL1) {
            if (l2 != null) {
                newList.next = l2;
            }
        }

        if (flagL2) {
            if (l1 != null) {
                newList.next = l1;
            }
        }
        return newList;


    }

    public ListNode mergeTwoListsImprove(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            //这一步发生了什么变化
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    public ListNode mergeTwoListsChange(ListNode l1, ListNode l2) {
        //定义一个头节点
        //定义一个哨兵节点，从头节点开始往下慢慢滑动,头节点实际上是有它来慢慢组成链表的
        //每次拿到最小的值添上，并继续往下移动更新整个链表
        ListNode head = new ListNode(-1);
        //开始指向head结点
        ListNode headPointer = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                //最小的拼上，然后往后移
                headPointer.next = l1;
                l1 = l1.next;
            } else {
                headPointer.next = l2;
                l2 = l2.next;
            }
            //继续往下更新
            headPointer = headPointer.next;
        }
        //最后
        if (l1 == null) {
            headPointer.next = l2;
        } else {
            headPointer.next = l1;
        }
        return head.next;
    }

}
