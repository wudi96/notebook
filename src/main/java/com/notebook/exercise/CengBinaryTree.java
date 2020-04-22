package com.notebook.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的层序遍历
 *
 * @author luorigong
 */
public class CengBinaryTree {

  private List<List<Integer>> list = new ArrayList<List<Integer>>();

  private List<List<Integer>> helper(TreeNode node, int level) {
    if (list.size() == level) {
      list.add(new ArrayList<Integer>());
    }
    list.get(level).add(node.val);
    if (node.left != null) {
      helper(node.left, level + 1);
    }
    if (node.right != null) {
      helper(node.right, level + 1);
    }
    return list;
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return list;
    }
    helper(root, 0);
    return list;
  }
}
