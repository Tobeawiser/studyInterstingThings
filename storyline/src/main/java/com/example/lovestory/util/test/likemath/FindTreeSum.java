package com.example.lovestory.util.test.likemath;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindTreeSum {
    public static void main(String[] args) {
        FindTreeSum findTreeSum = new FindTreeSum();
        TreeNode treeNode = findTreeSum.createTreeNode();
        findTreeSum.behindSearch(treeNode);
    }

    public List<int[]> hasPathSum(TreeNode root, int targetSum) {
        //1000
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            //i 代表一个 misson 问题
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                }
            };
            //将线程放入线程池
            executorService.submit(runnable);
        }

        //存储路径
        List<int[]> resultPath = new ArrayList<>();
        //记录路径
        int[] path = {};
        //广度优先搜索
        int count = 0;
        int val = root.val;
        path[count] = val;

        recursion(root, targetSum, resultPath, path, count);
        return resultPath;
    }

    private void recursion(TreeNode root, int targetSum, List<int[]> resultPath, int[] path, int count) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        //若左边没左右节点 当作子节点
        if (left.left == null && left.right == null) {
            count++;
            path[count] = left.val;
            if (countPath(left, path, targetSum)) {
                int[] r = path;
                resultPath.add(r);
            }
            //若右边没左右节点    当作子节点
        } else if (right.left == null && right.right == null) {
            count++;
            path[count] = right.val;
            if (countPath(right, path, targetSum)) {
                int[] r = path;
                resultPath.add(r);
            }
        } else {
            //当作root继续循环
            recursion(left, targetSum, resultPath, path, count);
            recursion(right, targetSum, resultPath, path, count);
        }
    }

    //计算路径和
    private boolean countPath(TreeNode treeNode, int[] path, int targetSum) {
        int sum = 0;
        for (int i = 0; i < path.length; i++) {
            sum = sum + path[i];
        }
        if (targetSum == sum) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 1
     * 2         3
     * 4    5     6    7
     * 8
     *
     * @return
     */
    public TreeNode createTreeNode() {
        TreeNode treeNode = new TreeNode(1);

        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);

        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode6 = new TreeNode(7);

        TreeNode treeNode7 = new TreeNode(8);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;

        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;

        treeNode3.left = treeNode7;
        return treeNode;
    }

    //构建二叉树

    //递归也还是有一个深度顺序的
    public void firstSearch(TreeNode root) {
        //先序 根左右 1 2 4 8 5 3 6 7
        //先序遍历

        System.out.println(root.val);
        //输出左还是根左右
        //System.out.println(root.left.val);
        if (root.left != null) {
            firstSearch(root.left);
        }
        //输出右还是跟左右
        if (root.right != null) {
            firstSearch(root.right);
        }
    }

    public void middleSearch(TreeNode root) {
        //中序 左根右  2 4 8 5 1 3 6 7
        //中序遍历
        //左
        if (root.left != null) {
            firstSearch(root.left);
        }
        System.out.println(root.val);
        //右
        if (root.right != null) {
            firstSearch(root.right);
        }
    }

    public void behindSearch(TreeNode root) {
        //中序 左根右  2 4 8 5 1 3 6 7
        //中序遍历
        //左
        if (root.left != null) {
            firstSearch(root.left);
        }
        //右
        if (root.right != null) {
            firstSearch(root.right);
        }
        System.out.println(root.val);
    }

    //广度优先搜索
    public void widthSearch(TreeNode root) {

    }

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
