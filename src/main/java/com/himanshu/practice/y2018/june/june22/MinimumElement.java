package com.himanshu.practice.y2018.june.june22;

/**
 * Created by Himanshu Bhardwaj on 22/06/18.
 */
public class MinimumElement {
    public static void main(String[] args) {
        int size = 15;
        int arr[] = new int[size];


        for (int i = 0; i < size; i++) {
            arr[i] = ((31 + i + i + 3) * (3 - i) + 7) % (31);
            System.out.print(arr[i] + " ");
        }

        System.out.println("Making heap");
        Heap heap = new Heap(arr);
        System.out.println();
        heap.printHeap();
        System.out.println();
        System.out.println("Adding -31");
        heap.addElement(-31);
        heap.printHeap();
        System.out.println();
        System.out.println("popping: " + heap.popMin());
        heap.printHeap();

    }
}


//min heap
class Heap {
    int[] heap;
    int lastPos = -1;
    int size;


    public Heap(int arr[]) {
        size = (int) Math.pow(2d, Math.floor((Math.log(arr.length) / Math.log(2))));
        size = 2 * size - 1;
        System.out.println();
        System.out.println("Size: " + size);
        heap = new int[size];

        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length) {
                heap[i] = arr[i];
                lastPos = i;
            } else {
                heap[i] = Integer.MAX_VALUE;
            }
        }
        bottumUPHeapify();
    }


    public void bottumUPHeapify() {
        System.out.println("@bottumUPHeapify");
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            topDownHeapify(i);
        }
    }

    //this assumes that all children and their subchildren of pos are proper heap
    public void topDownHeapify(int pos) {
        if (pos >= heap.length / 2) {
            return;
        }

        int j = pos;
        int child1 = 2 * j + 1;
        int child2 = 2 * j + 2;


        if (heap[child1] < heap[child2]) {
            if (heap[pos] > heap[child1]) {
                int temp = heap[pos];
                heap[pos] = heap[child1];
                heap[child1] = temp;
                topDownHeapify(child1);
            }
        } else {
            if (heap[pos] > heap[child2]) {
                int temp = heap[pos];
                heap[pos] = heap[child2];
                heap[child2] = temp;
                topDownHeapify(child2);
            }
        }
    }


    public void printHeap() {
        for (int i = 0; i <= lastPos; i++) {
            System.out.print(heap[i] + " ");
        }
    }


    public int min() {
        if (lastPos == -1) {
            return Integer.MAX_VALUE;
        }
        return heap[0];
    }


    public void addElement(int element) {
        if (lastPos == (heap.length - 1)) {
            System.out.println("resizing");
            resize();
            System.out.println("New Heap Size: " + heap.length);
        }
        heap[++lastPos] = element;

        for (int i = lastPos; i > 0; ) {//TODO:
            int parent = (i - 1) / 2;
            if (heap[parent] > heap[i]) {
                int temp = heap[parent];
                heap[parent] = heap[i];
                heap[i] = temp;
                i = parent;
            } else {
                break;
            }
        }
    }

    private void resize() {
        int newSize = 2 * size + 1;
        int[] newHeap = new int[newSize];
        for (int i = 0; i <= lastPos; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        size = newSize;
    }


    public int popMin() {
        int min = heap[0];
        heap[0] = heap[lastPos];
        heap[lastPos] = min;
        lastPos--;
        topDownHeapify(0);
        return min;
    }


}


