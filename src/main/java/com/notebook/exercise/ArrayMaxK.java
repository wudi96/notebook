package com.notebook.exercise;

/**
 * 数组获取连续k个数的最大值
 * Created by luorigong on 2020-04-21.
 */
public class ArrayMaxK {

  public static void main(String[] args) {
    int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    System.out.println(max(a, 2));
  }

  private static int max(int[] array, int k) {
    int sum = 0;
    int length = array.length;
    if (length < k) {
      k = length;
    }
    for (int i = 0; i < k; i++) {
      sum += array[i];
    }
    int max = sum;
    if (length < k) {
      return max;
    }
    for (int i = k; i < length; i++) {
      sum = sum - array[i - k] + array[i];
      if (sum > max) {
        max = sum;
      }
    }
    return max;
  }
}
