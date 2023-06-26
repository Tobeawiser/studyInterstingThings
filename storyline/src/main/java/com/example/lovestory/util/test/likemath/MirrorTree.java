package com.example.lovestory.util.test.likemath;

import com.example.lovestory.model.TreeNode;

public class MirrorTree {


    public static boolean result = true;

    //1223443
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//
//        root.left.left =   new TreeNode(3);
//        root.left.right =   new TreeNode(4);
//
//        root.right.left =   new TreeNode(4);
//        root.right.right =   new TreeNode(3);

        MirrorTree mirrorTree = new MirrorTree();
        boolean mirrorTree1 = mirrorTree.isMirrorTree(root);
        System.out.println(mirrorTree1);

    }

    public boolean isMirrorTree(TreeNode root) {

        //中序遍历 放list集合然后一个个比较
        //能否同时遍历比较
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        recursion(left, right);


        return result;
    }

    //中序遍历  左根右     * 先序遍历    根左右     后序遍历    左右根
    private void recursion(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            //为false 不用递归
        } else if (left == null && right != null) {
            result = false;
        } else if (left != null && right == null) {
            result = false;
        } else if (left.val != right.val) {
            result = false;
        } else {
            recursion(left.left, right.right);
            recursion(left.right, right.left);
        }

    }

}
