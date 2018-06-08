package com.himanshu.practice.june_12_2018;

/**
 * Created by himanshubhardwaj on 12/06/18.
 */
public class MergingTwoLinklists {

    public static void main(String[] args) {
        LList list1 = null;
        LList list2 = null;
        LList list3 = null;


        list1 = LList.insert(list1, 3);
        list1 = LList.insert(list1, 111);
        list1 = LList.insert(list1, 43);
        list1 = LList.insert(list1, 33);
        list1 = LList.insert(list1, 43);

        list2 = LList.insert(list2, 4);
        list2 = LList.insert(list2, 133334);
        list2 = LList.insert(list2, 24);
        list2 = LList.insert(list2, 34);

        LList.print(list1);
        LList.print(list2);
        list3 = LList.merge(list1, list2);
        System.out.println();
        LList.print(list3);
        System.out.println("sorting ka time coming");

        LList.print(LList.mergesort(list3));


    }

}


class LList {
    NNode head;

    static public LList mergesort(LList list) {
        if (list == null || list.head == null || list.head.next == null) {
            return list;
        }
        list.head = NNode.mergeSort(list.head);
        return list;
    }

    static public void print(LList lList) {
        if (lList == null) {
            System.out.println("Empty List...returning");
            return;
        }
        System.out.println("Printing list");
        NNode.print(lList.head);
        System.out.println();
    }

    static public LList insert(LList lList, int value) {
        if (lList == null) {
            lList = new LList();
        }
        NNode node = new NNode(value);
        node.next = lList.head;
        lList.head = node;
        return lList;
    }

    static public LList mergeSort(LList lList) {
        if (lList == null || lList.head == null) {
            return lList;
        }
        lList.head = NNode.mergeSort(lList.head);
        return lList;
    }

    //Sorted in decreasing
    public static LList merge(LList list1, LList list2) {
        LList list3 = new LList();
        list3.head = NNode.merge(list1.head, list2.head, null);
        return list3;
//        NNode current1, current2, current3;
//        current1 = list1.head;
//        current2 = list2.head;
//        current3 = list3.head;
//
//
//        while (current1 != null && current2 != null) {
//            //both are not null here
//            if (current1.value > current2.value) {
//                if (current3 == null) {
//                    list3.head = current1;
//                    current3 = current1;
//                } else {
//                    current3.next = current1;
//                    current3 = current3.next;
//                }
//                current1 = current1.next;
//            } else {
//                if (current3 == null) {
//                    list3.head = current2;
//                    current3 = current2;
//                } else {
//                    current3.next = current2;
//                    current3 = current3.next;
//                }
//                current2 = current2.next;
//            }
//        }
//        if (current2 == null) {
//            if (current3 == null) {
//                list3.head = current1;
//            } else {
//                current3.next = current1;
//            }
//        } else if (current1 == null) {
//            if (current3 == null) {
//                list3.head = current2;
//            } else {
//                current3.next = current2;
//            }
//        }
//        //Both can not become null simultaniously
//
//
    }

}


class NNode {
    int value;
    NNode next;

    public NNode(int value) {
        this.value = value;
        next = null;
    }

    static NNode add(NNode node, int value) {
        NNode nNode = new NNode(value);
        if (node == null) {
            node = nNode;
        }
        nNode.next = node;
        node = nNode;
        return node;
    }

    static void print(NNode nNode) {
        if (nNode == null) {
            return;
        }
        System.out.print(nNode.value + "-->");
        print(nNode.next);
    }

    //this method assumes that there is no cycle
    //this function returns node1
    static NNode middleElement(NNode node) {
        NNode nNode1 = node;
        NNode nNode2 = node;

        while (nNode2 != null && nNode2.next != null) {
            nNode1 = nNode1.next;
            nNode2 = nNode2.next.next;
        }
        return nNode1;
    }

    static NNode endElement(NNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        return endElement(node.next);
    }


    static NNode merge(NNode node1, NNode node2, NNode node3) {
        NNode current1 = node1;
        NNode current2 = node2;
        NNode current3 = node3;
        NNode head = null;


        while (current1 != null && current2 != null) {
            //both are not null here
            if (current1.value > current2.value) {
                if (current3 == null) {
                    head = current1;
                    current3 = current1;
                } else {
                    current3.next = current1;
                    current3 = current3.next;
                }
                current1 = current1.next;
            } else {
                if (current3 == null) {
                    head = current2;
                    current3 = current2;
                } else {
                    current3.next = current2;
                    current3 = current3.next;
                }
                current2 = current2.next;
            }
        }
        if (current2 == null) {
            if (current3 == null) {
                head = current1;
            } else {
                current3.next = current1;
            }
        } else if (current1 == null) {
            if (current3 == null) {
                head = current2;
            } else {
                current3.next = current2;
            }
        }
        //Both can not become null simultaniously

        return head;
    }


    static NNode mergeSort(NNode node) {
        if (node == null) {
            return null;
        }
        NNode start = node;
        NNode end = endElement(node);

        return actualMergeSort(start, end);

    }

    private static NNode actualMergeSort(NNode start, NNode end) {
        if (start == end || end == null) {
            return start;
        }

        if (start == null) {
            return end;
        }
        if(start.next == end) {
            //simplify corner case
            if(start.value >= end.value) {
                return start;
            } else {
                int temp = end.value;
                end.value = start.value;
                start.value = temp;
            }
            return start;
        }

        NNode middle = middleElement(start);
        NNode secondListHead = middle.next;
        middle.next = null;

//        System.out.print("start:" + start.value);
//        System.out.print(" middle:" + middle.value);
//        System.out.print("\t");
//        System.out.print("start:" + secondListHead.value);
//        System.out.print(" end:" + end.value);
//        System.out.println();

        NNode leftList = actualMergeSort(start, middle);
        NNode rightList = actualMergeSort(secondListHead, end);
        return merge(leftList, rightList, null);
    }
}