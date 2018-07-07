package com.himanshu.practice.june10.hour3;

/**
 * Created by Himanshu Bhardwaj on 10/06/18.
 */
public class HeapHelloWorld {
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(5);
        heap.insert(4);
        heap.insert(16);
        heap.insert(18);
        heap.insert(7);
        heap.insert(3);
        heap.insert(31);
        heap.insert(32);
        heap.insert(23);
        heap.insert(333);
        heap.insert(3);
        heap.insert(31);
        heap.insert(34);
        heap.insert(22);



        for (int i = 0; i <= heap.end; i++) {
            System.out.print(heap.arr[i] + " ");
        }
        System.out.println();

        heap.deleteTop();
        heap.deleteTop();

        for (int i = 0; i <= heap.end; i++) {
            System.out.print(heap.arr[i] + " ");
        }
    }
}


class Heap {
    int arr[];
    int end = -1; // we will always enter values into end+1 position; till end the heap is always filled
    int initialSize = 4; //this will always represent the mazimum size of the arr
    private int tempArr[] = new int[3]; //temp arr, to swap

    public Heap() {
        arr = new int[initialSize];
    }


    //Tested
    void insert(int value) {
        //if Even before inserting if end is at the end of arr
        if (arr.length == (end + 2)) {
            resize();
            costructHeap();
        }

        arr[end + 1] = value;
        end++;
        heapify(end);
    }


    //this position has to start from the root
    //tested
    private void heapify(int pos) {
        if (pos > end || pos < 0) {
            return;
        }
        int parent = (pos - 1) / 2;
        int firstChildIndex = 2 * parent + 1;
        int secondChildIndex = 2 * parent + 2;


        if (firstChildIndex <= end && secondChildIndex <= end) {
            if (arr[firstChildIndex] <= arr[secondChildIndex]) {
                if (arr[parent] > arr[firstChildIndex]) {
                    swap(arr, parent, firstChildIndex);
                    heapify(parent);
                }
            } else {
                if (arr[parent] > arr[secondChildIndex]) {
                    swap(arr, parent, secondChildIndex);
                    heapify(parent);
                }
            }
        } else if (firstChildIndex <= end) {
            if (arr[parent] > arr[firstChildIndex]) {
                swap(arr, firstChildIndex, parent);
                heapify(parent);
            }
        }
    }

    //tested
    private void swap(int[] arr, int pos, int firstChildIndex) {
        int temp = arr[pos];
        arr[pos] = arr[firstChildIndex];
        arr[firstChildIndex] = temp;
    }


    //tested
    int getmin() {
        if (end >= 0) {
            return arr[0];
        } //else throw exception
        return Integer.MIN_VALUE;
    }


    //tested
    void deleteTop() {
        if (end < 0) {
            System.out.println("No Element");
            return;
        }
        swap(arr, 0, end);
        end--; //now end position is free to take position
        topDownHeapyfy(0);
    }

    //initiall this pos will be zero
    //tested
    void topDownHeapyfy(int pos) {
        if (pos < 0 || pos > end) {
            return;
        }
        int child1 = 2 * pos + 1;
        int child2 = 2 * pos + 2;

        if (child1 <= end && child2 <= end) {
            if (arr[child1] < arr[child2]) {
                if (arr[pos] > arr[child1]) {
                    swap(arr, pos, child1);
                    topDownHeapyfy(child1);
                }
            } else {
                if (arr[pos] > arr[child2]) {
                    //in case of equality, enjoy
                    swap(arr, child2, pos);
                    topDownHeapyfy(child2);
                }
            }
        }
        if (child1 <= end) {
            if (arr[pos] > arr[child1]) {
                swap(arr, pos, child1);
                topDownHeapyfy(child1);
            }
        }
    }


    void resize() {
        System.out.println("resizing");
        int nTempArr[] = new int[2 * initialSize];

        for (int i = 0; i < arr.length; i++) {
            nTempArr[i] = arr[i];
        }
        arr = nTempArr;
        initialSize = initialSize * 2;
    }


    void costructHeap() {
        for (int i = (initialSize / 2); i >= 0; i--) {
            topDownHeapyfy(i);
        }

    }


    public int maximumSize( ) {
        return arr.length;
    }


}
