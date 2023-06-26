package com.example.lovestory.util.test.likemath;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeForEach {


    //左根右  中序遍历
    public static void reverse(TreeNode root, List<Integer> r) {

        //栈的形式 用迭代
        if (root.left != null) {
            reverse(root.left, r);
        }

        r.add(root.val);

        if (root.right != null) {
            reverse(root.right, r);
        }

    }

    //左根右
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList();
        if (root == null) {
            return r;
        }

        reverse(root, r);

        return r;

    }

    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
