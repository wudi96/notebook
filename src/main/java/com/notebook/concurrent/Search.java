package com.notebook.concurrent;

/**
 * Created by luorigong on 2020-04-21.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并行搜索：查找数组中数据，返回位置下标；没有则返回-1
 *
 * @author wsz
 * @date 2018年1月3日
 */
public class Search implements Callable<Integer> {

  //该类实现Callable接口
  private int begin, end, search;

  public Search(int search, int begin, int end) {
    super();
    this.search = search;
    this.begin = begin;
    this.end = end;
  }

  @Override
  public Integer call() throws Exception {
    return search(search, begin, end);
  }

  //定义查询数据数组
  static int[] arr = {1, 5, 4, 879, 456465, 1321, 564, 8798, 154, 45646, 132, 1321, 48, 7, 87, 8, 8,
      7, 87, 98, 89, 546, 46};
  //线程池+线程数量+存放结果变量result
  static ExecutorService pool = Executors.newCachedThreadPool();
  static final int THREAD_NUM = 2;
  static AtomicInteger result = new AtomicInteger(-1);

  public static int search(int search, int begin, int end) {
    int i = 0;
    for (i = begin; i < end; i++) {
      if (result.get() > 0)  //判断其他线程是否已经得到结果，已得到则返回
      {
        return result.get();
      }

      if (arr[i] == search) {  //当前线程得到结果
        if (!result.compareAndSet(-1, i)) //使用CAS操作保存结果，如果失败则其他线程已先一步获取到结果。
        {
          return result.get();
        }
        return i;
      }
    }
    return -1;  //没有找到则返回-1
  }

  public static int pSearch(int search) throws InterruptedException, ExecutionException {
    int subArrSize = arr.length / THREAD_NUM + 1;  //根据线程数对数组arr划分
    List<Future<Integer>> re = new ArrayList<Future<Integer>>();
    for (int i = 0; i < arr.length; i += subArrSize) {
      int end = i + subArrSize;
      if (end >= arr.length) {
        end = arr.length;
      }
      re.add(pool.submit(new Search(search, i, end)));//划分后，建立任务、提交查找、保存结果
    }
    for (Future<Integer> future : re) {
      if (future.get() >= 0)    //如果不为-1，则找到结果并返回
      {
        return future.get();
      }
    }
    return -1;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    int pSearch = pSearch(46);   //返回位置下标22
//		int pSearch = pSearch(460);  -1即没有找到
    System.out.println(pSearch);
  }

}