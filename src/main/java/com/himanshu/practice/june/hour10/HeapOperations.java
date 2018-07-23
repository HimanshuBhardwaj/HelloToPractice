package com.himanshu.practice.june.hour10;

/**
 * Created by Himanshu Bhardwaj on 25/06/18.
 */

public class HeapOperations {
    public static void main(String[] args) {
        int size = 9;
        int arr[] = new int[size];


        for (int i = 0; i < size; i++) {
            arr[size - i - 1] = ((i + 1) * (i + 3) - 5) % (3 * size - 1);
        }
        System.out.println("Orignal array");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        Heap heap = new Heap(arr);
        heap.print();

        System.out.println();
        System.out.println();
        System.out.println("top element" + heap.delete());
        heap.print();

    }
}

//minHeap
class Heap {
    int heap[];
    int lastPos;
    int heapSize;


    public Heap(int[] arr) {
        int heapSize = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        heapSize = 2 * heapSize - 1;
        this.heapSize = heapSize;
        heap = new int[heapSize];


        for (int i = 0; i < heapSize; i++) {
            if (i < arr.length) {
                heap[i] = arr[i];
                lastPos = i;
            } else {
                heap[i] = Integer.MAX_VALUE;
            }
        }
        heapify();
    }

    public void heapify() {
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            heapifyTopDown(i);
        }
    }

    //assumes that both child are heap
    public void heapifyTopDown(int parent) {
        if (parent > lastPos || (2 * parent + 1) > lastPos) {
            return;
        }

        int child1 = 2 * parent + 1;
        int child2 = 2 * parent + 2;

        //both child valid
        if (child2 <= lastPos) {
            if (heap[child1] < heap[child2]) {
                if (heap[child1] < heap[parent]) {
                    int temp = heap[parent];
                    heap[parent] = heap[child1];
                    heap[child1] = temp;
                    heapifyTopDown(child1);
                }
            } else {
                //child2 is small
                if (heap[child2] < heap[parent]) {
                    int temp = heap[parent];
                    heap[parent] = heap[child2];
                    heap[child2] = temp;
                    heapifyTopDown(child2);
                }
            }

        } else if (child1 <= lastPos) {
            if (heap[child1] < heap[parent]) {
                int temp = heap[parent];
                heap[parent] = heap[child1];
                heap[child1] = temp;
                heapifyTopDown(child1);
            }
        }
    }

    void print() {
        System.out.println("Heap Size: " + heapSize);
        System.out.println("End Position: " + lastPos);
        for (int i = 0; i <= lastPos; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }


    public int delete() {
        if (lastPos == -1) {
            return Integer.MAX_VALUE;
        }
        int returnValue = heap[0];

        heap[0] = heap[lastPos--];
        heapifyTopDown(0);
        return returnValue;
    }


}