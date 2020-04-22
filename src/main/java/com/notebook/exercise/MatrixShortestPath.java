package com.notebook.exercise;

import java.util.LinkedList;

/**
 * 矩阵最短路径 Created by luorigong on 2020-04-21.
 */
public class MatrixShortestPath {

  //在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
  //返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
  //eg:[[0,0,0],
  //    [0,0,0],
  //    [0,0,0]]
  //输出：3
  public static int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] == 1) {
      return -1;
    }
    final int N = grid.length;
    final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1},
        {1, -1}, {1, 1}};
    //定义备忘录，用于记录已经访问的位置；
    boolean[][] visited = new boolean[N][N];
    // 定义队列
    LinkedList<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, 0});
    int depth = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      depth++;
      for (int i = 0; i < size; i++) {
        int[] node = queue.removeFirst();
        if (node[0] == N - 1 && node[1] == N - 1) {
          return depth;
        }
        for (int[] direction : DIRECTIONS) {
          int x = node[0] + direction[0];
          int y = node[1] + direction[1];
          // 排除：1、越界 2、已访问 3、阻塞
          if (x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || grid[x][y] == 1) {
            continue;
          }
          //更新备忘录
          visited[x][y] = true;
          //新位置加入队列
          queue.addLast(new int[]{x, y});
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[][] grid = new int[3][3];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = 0;
      }
    }
    System.out.println(shortestPathBinaryMatrix(grid));
  }
}
