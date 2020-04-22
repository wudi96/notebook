package com.notebook.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 树遍历——前序遍历
 * @author luorigong
 */
public class PreOrderTraversal {

    int i = 0;

    public static void main(String[] args) {
        PreOrderTraversal preOrderTraversal = new PreOrderTraversal();
        TreeNode init = preOrderTraversal.init();
        System.out.println(preOrderTraversal.getHeight(init));
    }

    private List<Integer> preOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        LinkedList<Integer> output = new LinkedList<>();

        if (root == null) {
            return output;
        }
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            output.add(node.val);
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return output;
    }

    private TreeNode init() {
        int[] n = {1, 2, 3, 4};
        TreeNode treeNode = create(n);
        List<Integer> integers = preOrderTraversal(treeNode);
        integers.forEach(System.out::println);
        return treeNode;
    }

    private TreeNode create(int[] n) {
        TreeNode p = null;
        if (i < n.length) {
            int j = n[i];
            i++;
            p = new TreeNode(j);
            //遍历该结点的左孩子
            p.left = create(n);
            //遍历该结点的右孩子
            p.right = create(n);
        }
        return p;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
