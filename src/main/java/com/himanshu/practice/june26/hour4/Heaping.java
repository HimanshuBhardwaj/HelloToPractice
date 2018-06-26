package com.himanshu.practice.june26.hour4;

import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 26/06/18.
 */
public class Heaping {
    public static void main(String[] args) {
        int size = 6;
        int arr[] = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = ((i + i) + 32 * i + 39) % 21;
            System.out.print(arr[i] + " ");
        }


        Heap heap = new Heap(arr);
        heap.print();
        System.out.println(heap.delete());
        heap.print();
        heap.add(19);
        heap.add(20);
        heap.add(6);
        heap.print();
    }
}


class Heap {
    int heap[];
    int endPos;


    public Heap(int[] arr) {
        int size = (int) Math.pow(2d, Math.ceil(Math.log(arr.length) / Math.log(2)));
        size = 2 * size - 1;
        heap = new int[size];

        for (int i = 0; i < size; i++) {
            if (i < arr.length) {
                heap[i] = arr[i];
                endPos = i;
            } else {
                heap[i] = Integer.MAX_VALUE;
            }
        }
        System.out.println();
        System.out.println("Size: " + size);
        System.out.println("End Pos: " + endPos);

        for (int i = (heap.length / 2) - 1; i >= 0; i--) {
            topDownHeapify(i);
        }
    }


    //assume all children of index are proper heap
    void topDownHeapify(int index) {
        if (index >= heap.length / 2) {
            return;
        }

        int child1 = 2 * index + 1;
        int child2 = 2 * index + 2;

        if (child2 <= endPos) {
            if (heap[child1] < heap[child2]) {
                if (heap[index] > heap[child1]) {
                    int temp = heap[child1];
                    heap[child1] = heap[index];
                    heap[index] = temp;
                    topDownHeapify(child1);
                }
            } else {
                if (heap[index] > heap[child2]) {
                    int temp = heap[child2];
                    heap[child2] = heap[index];
                    heap[index] = temp;
                    topDownHeapify(child2);
                }
            }
        } else if (child1 <= endPos) {
            if (heap[index] > heap[child1]) {
                int temp = heap[child1];
                heap[child1] = heap[index];
                heap[index] = temp;
                topDownHeapify(child1);
            }
        }
    }

    public void print() {
        System.out.println();
        for (int i = 0; i <= endPos; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public int delete() {
        int returnValue = heap[0];
        heap[0] = heap[endPos];
        heap[endPos] = Integer.MAX_VALUE;
        endPos--;
        topDownHeapify(0);
        return returnValue;
    }

    public void add(int element) {
        if (endPos == (heap.length - 1)) {
            resize();
        }
        heap[++endPos] = element;
        int parent = (endPos - 1) / 2;

        while (parent >= 0) {
            int child1 = 2 * parent + 1;
            int child2 = 2 * parent + 2;

            if (child2 <= endPos) {
                if (heap[child1] < heap[child2]) {
                    if (heap[parent] > heap[child1]) {
                        swap(parent, child1);
                    } else {
                        break;
                    }
                } else {
                    if (heap[parent] > heap[child2]) {
                        swap(parent, child2);
                    } else {
                        break;
                    }
                }
            } else if (child1 <= endPos) {
                if (heap[parent] > heap[child1]) {
                    swap(parent, child1);
                } else {
                    break;
                }
            }
            parent = (parent - 1) / 2;
        }


    }

    private void swap(int parent, int child) {
        int temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    private void resize() {
        int[] tempHeap = new int[2 * heap.length + 1];

        for (int i = 0; i <= endPos; i++) {
            tempHeap[i] = heap[i];
        }
        heap = tempHeap;
    }
}