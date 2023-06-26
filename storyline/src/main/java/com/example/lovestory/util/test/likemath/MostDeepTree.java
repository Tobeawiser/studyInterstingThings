package com.example.lovestory.util.test.likemath;

import com.example.lovestory.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MostDeepTree {


    public static boolean result = true;

    //1223443
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        MostDeepTree MostDeepTree = new MostDeepTree();
        int i = MostDeepTree.mostDeepTreeLength(root);
        System.out.println("最大层数: " + i);

    }

    public int mostDeepTreeLength(TreeNode root) {
        //广度搜索  队列
        Queue<TreeNode> queue = new LinkedList<>();
        //入队
        root.val = 1;
        int floor = 1;
        int max = 1;
        queue.add(root);
        //出队
        TreeNode peek = queue.poll();
        //直接把值处理成层数
        //继续下一轮处理
        while (peek != null) {
            //处理元素
            floor = peek.val;
            //出队子节点放进入
            if (peek.left != null) {
                peek.left.val = floor + 1;
                queue.add(peek.left);
            }
            if (peek.right != null) {
                peek.right.val = floor + 1;
                queue.add(peek.right);
            }
            //左右子节点为空情况
            if (floor > max) {
                max = floor;
            }

            //弹出
            peek = queue.poll();
        }

        return max;
    }

}
