package com.notebook.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by luorigong on 2020-04-21.
 */
public class SteamTest {

  public static void main(String[] args) {
    BufferedWriter bufferedWriter = null;
    BufferedReader bufferedReader = null;

    try {
      bufferedWriter = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream("./notebook.txt"), StandardCharsets.UTF_8));
      bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      String s = "";
      while ((s = bufferedReader.readLine()) != null) {
        if ("over".equals(s)) {
          break;
        }
        bufferedWriter.write(s.toUpperCase());
        bufferedWriter.newLine();
        bufferedWriter.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bufferedWriter.close();
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
