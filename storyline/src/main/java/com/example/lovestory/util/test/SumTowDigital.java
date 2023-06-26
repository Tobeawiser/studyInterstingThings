package com.example.lovestory.util.test;

public class SumTowDigital {
    public static void main(String[] args) {

    }

    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode newNode = new ListNode();
            boolean flagAdd = false;

            int value = l1.val + l2.val;
            if (value >= 10) {
                value = value % 10;
                flagAdd = true;
            } else {
                flagAdd = false;
            }
            newNode.val = value;
            while (l1.next != null && l2.next != null) {
                value = l1.next.val + l2.next.val;
                if (value >= 10) {
                    value = value % 10;
                    flagAdd = true;
                } else {
                    flagAdd = false;
                }
                if (flagAdd) {
                    newNode.next.val = value + 1;
                } else {
                    newNode.next.val = value;
                }
            }
            if (l1.next == null) {
                if (flagAdd) {
                    newNode.next.val = value + 1;
                }
            }
            return new ListNode();
        }
    }
}
