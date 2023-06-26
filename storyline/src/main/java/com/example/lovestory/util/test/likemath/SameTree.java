package com.example.lovestory.util.test.likemath;

import com.example.lovestory.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SameTree {


    public static List<Integer> pList = new ArrayList<>();
    public static List<Integer> qList = new ArrayList<>();

    public static void main(String[] args) {
        SameTree sameTree = new SameTree();
        sameTree.testZero();
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        //中序遍历 放list集合然后一个个比较
        //能否同时遍历比较
        if (p == null && q == null) {
            return true;
        }
        recursion(p, pList);
        recursion(q, qList);
        //  System.out.println(pList.size() + "--" + qList.size());
        if (pList.size() != qList.size()) {
            return false;
        } else {
            for (int i = 0; i < pList.size(); i++) {
                int integer = pList.get(i);
                int integer2 = qList.get(i);
                //System.out.println(integer + "--" + integer2);
                if (integer != integer2) {
                    return false;
                }
            }
        }

        return true;
    }

    //中序遍历  左根右     先序遍历    根左右     后序遍历    左右根1
    private void recursion(TreeNode node, List<Integer> list) {
        if (node == null) {
            list.add(Integer.MAX_VALUE);
            return;
        }
        list.add(node.val);
        recursion(node.left, list);
        recursion(node.right, list);


    }

    public void testZero() {
        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(0);

        boolean sameTree = isSameTree(treeNode1, treeNode2);
        System.out.println(sameTree);
    }
}
