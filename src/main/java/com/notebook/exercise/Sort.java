package com.notebook.exercise;

import java.util.Arrays;

/**
 * 排序算法
 *
 * @author luorigong
 */
public class Sort {
    //快排
    private static void quickSprt(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;
        //设立基准点
        int pivot = array[left];
        while (left < right) {
            //从右向左遍历，大数位置不变
            while (left < right && array[right] >= pivot) {
                right--;
            }
            //从左向右，小数位置不变
            while (left < right && array[left] <= pivot) {
                left++;
            }
            //左右互换
            swap(array, left, right);

        }
        swap(array, low, left);
        //使划分好的数分布在基数两侧
        quickSprt(array, low, left - 1);
        quickSprt(array, left + 1, high);
    }

    private static void swap(int n[], int i, int j) {
        int temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }

    //归并排序
    public static void mergeSort(int[] arrayList) {
        if (arrayList == null || arrayList.length == 0) {
            return;
        }

        sort(arrayList, 0, arrayList.length - 1);
    }

    private static void sort(int[] arrayList, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }

        // 找出中间索引即左边数组的末尾位置
        int leftEnd = (leftStart + rightEnd) >> 1;

        // 右边数组的起始位置在左边数组末尾右侧
        int rightStart = leftEnd + 1;

        // 对左边数组进行递归
        sort(arrayList, leftStart, leftEnd);

        // 对右边数组进行递归
        sort(arrayList, rightStart, rightEnd);

        // 合并
        merge(arrayList, leftStart, leftEnd, rightStart, rightEnd);
    }

    private static void merge(int[] arrayList, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int[] tempArray = new int[arrayList.length];
        int tempIndex = leftStart;
        int resultIndex = leftStart;

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            // 从两个数组中取出最小的放入临时数组

            boolean b = arrayList[leftStart] <= arrayList[rightStart];
            if (b) {
                tempArray[tempIndex] = arrayList[leftStart];
                tempIndex++;
                leftStart++;
            } else {
                tempArray[tempIndex] = arrayList[rightStart];
                tempIndex++;
                rightStart++;
            }
        }

        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
        while (leftStart <= leftEnd) {
            tempArray[tempIndex] = arrayList[leftStart];
            tempIndex++;
            leftStart++;
        }

        while (rightStart <= rightEnd) {
            tempArray[tempIndex] = arrayList[rightStart];
            tempIndex++;
            rightStart++;
        }

        // 将临时数组中的内容拷贝回原数组中
        //（原left-right范围的内容被复制回原数组）
        while (resultIndex <= rightEnd) {
            arrayList[resultIndex] = tempArray[resultIndex];
            resultIndex++;
        }
    }

    //堆排序

    /**
     * 调整为大顶堆
     *
     * @param arr    待调整的数组
     * @param parent 当前父节点的下标
     * @param length 需要对多少个元素进行调整
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //临时保存父节点
        int temp = arr[parent];
        //左子节点的下标
        int child = 2 * parent + 1;
        //如果子节点的下标大于等于当前需要比较的元素个数，则结束循环
        while (child < length) {
            //判断左子节点和右子节点的大小,若右边大，则把child定位到右边
            if (child + 1 < length && arr[child] < arr[child + 1]) {
                child++;
            }
            //若child大于父节点，则交换位置，否则退出循环
            if (arr[child] > temp) {
                //父子节点交换位置
                arr[parent] = arr[child];
                //因为交换位置之后，不能保证当前的子节点是它子树的最大值，所以需要继续向下比较，
                //把当前子节点设置为下次循环的父节点，同时，找到它的左子节点，继续下次循环
                parent = child;
                child = 2 * parent + 1;
            } else {
                //如果当前子节点小于等于父节点，则说明此时的父节点已经是最大值了，
                //因此无需继续循环
                break;
            }
        }
        //把当前节点值替换为最开始暂存的父节点值
        arr[parent] = temp;
    }

    public static void main(String[] args) {
        //快排
        int n[] = {6, 5, 2, 7, 3, 9, 8, 4, 10, 1, 9, 9, 7};
        quickSprt(n, 0, n.length - 1);
        for (int m : n) {
            System.out.print(m);
        }
        System.out.println("");

        //归并
        n = new int[]{6, 5, 2, 7, 3, 9, 8, 4, 10, 1, 9, 9, 7};
        mergeSort(n);
        for (int m : n) {
            System.out.print(m);
        }

        //堆排序
        int[] arr = {4, 1, 9, 3, 7, 8, 5, 6, 2};
        //构建一个大顶堆，从最下面的非叶子节点开始向上遍历
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        int length = arr.length;
        for (int i = arr.length - 1; i >= 1; i--) {
            //将第一个最大的元素移到后面，并且在adjustHeap的过程中通过减小length忽略它
            swap(arr, i, 0);
            length--;
            adjustHeap(arr, 0, length);
        }
        System.out.println(Arrays.toString(arr));
    }
}
