package com.himanshu.practice.June13;

/**
 * Created by Himanshu Bhardwaj on 13/06/18.
 */
public class MinHeap {
    public static void main(String[] args) {

        int size = 8;
        int[] arr = new int[size];

        System.out.print("Orignal Array: ");
        for (int i = 0; i < size; i++) {
            arr[i] = ((i + 9) * (i + 11)) % (31)+3;
            System.out.printf(" %d", arr[i]);
        }
        System.out.println();


        Heap heap = new Heap(arr);
        heap.printHeap();

        heap.insert(5);
        heap.printHeap();

        for(int i=0;i<8;i++)
        heap.insert(i+1);

        heap.printHeap();;
    }


}


class Heap {
    int heap[];
    int heapSize; //this will always be in multiple of two
    int end;


    //tested
    public Heap(int arr[]) {
        heapSize = (int) Math.pow(2, (Math.ceil(Math.log(arr.length) / Math.log(2))));
        heapSize = 2 * heapSize - 1;
        System.out.printf("MinimumElement Size %d\n", heapSize);
        heap = new int[heapSize];

        for (int i = 0; i < heapSize; i++) {
            if (i < arr.length) {
                heap[i] = arr[i];
                end = i;
            } else {
                heap[i] = Integer.MAX_VALUE;
            }
        }
        System.out.printf("End %d\n", end);

        for (int i = 0; i < heapSize / 2; i++) {
            topDownHeapify(i);
        }
    }


    //tested
    public void topDownHeapify(int index) {
        if (index > end || (2 * index + 1) > end) {
            //leaf or invalid
            return;
        }
        int firstChild = 2 * index + 1;
        int secondChild = 2 * index + 2;

        if (secondChild <= end) {
            if (heap[secondChild] < heap[firstChild]) {
                if (heap[index] > heap[secondChild]) {
                    int temp = heap[secondChild];
                    heap[secondChild] = heap[index];
                    heap[index] = temp;
                }
            } else {
                if (heap[index] > heap[firstChild]) {
                    int temp = heap[firstChild];
                    heap[firstChild] = heap[index];
                    heap[index] = temp;
                }
            }
        } else if (firstChild <= end) {
            if (heap[index] > heap[firstChild]) {
                int temp = heap[firstChild];
                heap[firstChild] = heap[index];
                heap[index] = temp;
            }
        }
        topDownHeapify(firstChild);
        topDownHeapify(secondChild);
    }


    //tested
    public void printHeap() {
        for (int i = 0; i <= end; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }


    //tested
    public int getMin() {
        if (end >= 0) {
            return heap[0];
        } else {
            return Integer.MIN_VALUE;
        }
    }


    public void insert(int element) {
        if ((end + 1) == heap.length) {
            resize();
        }
        heap[++end] = element;

        int pos = end;

        while (pos > 0) {
            int parent = (pos - 1) / 2;
            if (heap[parent] > heap[pos]) {
                int temp = heap[pos];
                heap[pos] = heap[parent];
                heap[parent] = temp;
            } else {
                break;
            }
            pos = parent;
        }


    }

    private void resize() {
        System.out.println("Resizing:");
        int[] newHeap = new int[2 * heap.length+1];
        heapSize = 2 * heap.length+1;
        for (int i = 0; i < newHeap.length; i++) {
            if (i < heap.length) {
                newHeap[i] = heap[i];
            } else {
                newHeap[i] = Integer.MAX_VALUE;
            }
        }
        heap = newHeap;
    }


}
