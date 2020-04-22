package com.notebook.exercise;

import java.util.Stack;

/**
 * 算术表达式
 *
 * @author luorigong
 */
public class SuanShuCalculate {

  class Solution {

    public int calculate(String s) {
      if (s == null || s.length() == 0) {
        return 0;
      }
      s = s.replace(" ", "");
      Stack<Integer> num = new Stack<>();
      int n = 0;
      char op = '+';
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
          n = n * 10 + (c - '0');
        }
        if (!Character.isDigit(c) || i == s.length() - 1) {
          int pre;
          switch (op) {
            case '+':
              num.push(n);
              break;
            case '-':
              num.push(-n);
              break;
            case '*':
              pre = num.pop();
              num.push(pre * n);
              break;
            case '/':
              pre = num.pop();
              num.push(pre / n);
              break;
          }
          op = c;
          n = 0;
        }
      }
      int res = 0;
      while (!num.isEmpty()) {
        res += num.pop();
      }
      return res;
    }
  }
}
