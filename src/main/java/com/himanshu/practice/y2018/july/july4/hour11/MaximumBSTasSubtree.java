package com.himanshu.practice.y2018.july.july4.hour11;

import lombok.*;

/**
 * Created by Himanshu Bhardwaj on 04/07/18.
 */
public class MaximumBSTasSubtree {
    public static void main(String[] args) {
        Node tree1 = new Node(10);
        tree1 = tree1.insert(tree1, 12);
        tree1 = tree1.insert(tree1, 13);
        tree1 = tree1.insert(tree1, 11);
        tree1 = tree1.insert(tree1, 8);
        tree1 = tree1.insert(tree1, 9);
        tree1 = tree1.insert(tree1, 7);
        tree1.inorder(tree1);
        System.out.println();


        Node tree2 = new Node(110);
        tree2 = tree2.insert(tree2, 112);
        tree2 = tree2.insert(tree2, 113);
        tree2 = tree2.insert(tree2, 111);
        tree2 = tree2.insert(tree2, 118);
        tree2 = tree2.insert(tree2, 119);
        tree2 = tree2.insert(tree2, 117);

        tree2.inorder(tree2);
        System.out.println();

        Node tree = new Node(111);
        tree.attachTrees(tree, tree1);
        tree.attachTrees(tree, tree2);

        tree.inorder(tree);
        Information info = tree.maximumBSTSize(tree);
        System.out.println();
        System.out.println(info);


    }
}


class Node {
    int value;
    Node left = null;
    Node right = null;

    public Node(int value) {
        this.value = value;
    }

    Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (node.value > value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        return node;
    }

    Node attachTrees(Node root1, Node root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2.value < root1.value) {
            root1.left = attachTrees(root1.left, root2);
        } else {
            root1.right = attachTrees(root1.right, root2);
        }
        return root1;
    }


    public void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.value + ", ");
        inorder(node.right);
    }

    public Information maximumBSTSize(Node root) {
        if (root == null) {
            return null;
        }

        Information left = maximumBSTSize(root.left);
        Information right = maximumBSTSize(root.right);
        Information rootI = new Information();


        if (left == null && right == null) {
            //leaf
            rootI.setSize(1);
            rootI.setMaxValue(root.value);
            rootI.setMinValue(root.value);
            rootI.setCommulativeMAxSize(1);
            return rootI;
        }

        boolean isValidBSTNode = true;

        if (left == null) {
            if (checkIfValidBSTSubtree(right)) {
                if (right.minValue >= root.value) {
                    rootI.setSize(1 + right.getSize());
                    rootI.setMaxValue(right.maxValue);
                    rootI.setMinValue(root.value);
                    rootI.setCommulativeMAxSize(1 + right.commulativeMAxSize);
                } else {
                    isValidBSTNode = false;
                    invalidNodeInformation(rootI, left, right);

                }
            } else {
                isValidBSTNode = false;
                invalidNodeInformation(rootI, left, right);
            }
        } else if (right == null) {
            if (checkIfValidBSTSubtree(left)) {
                if (left.maxValue <= root.value) {
                    rootI.setSize(1 + left.getSize());
                    rootI.setMaxValue(root.value);
                    rootI.setMinValue(left.minValue);
                    rootI.setCommulativeMAxSize(1 + left.commulativeMAxSize);
                } else {
                    isValidBSTNode = false;
                    invalidNodeInformation(rootI, left, right);
                }
            } else {
                isValidBSTNode = false;
                invalidNodeInformation(rootI, left, right);
            }
        } else {
            //when both are not null
            if (checkIfValidBSTSubtree(left) && checkIfValidBSTSubtree(right) && left.maxValue <= root.value && right.minValue >= root.value) {
                rootI.setSize(1 + right.getSize() + left.getSize());
                rootI.setMaxValue(right.maxValue);
                rootI.setMinValue(left.minValue);
                rootI.setCommulativeMAxSize(1 + right.commulativeMAxSize + left.commulativeMAxSize);
            } else {
                invalidNodeInformation(rootI, left, right);
            }
        }

        return rootI;
    }

    public void invalidNodeInformation(Information info, Information left, Information right) {
        info.setSize(-1);
        info.setMaxValue(Integer.MIN_VALUE);
        info.setMinValue(Integer.MAX_VALUE);
        info.commulativeMAxSize = Math.max((left != null) ? left.commulativeMAxSize : 0, (right != null) ? right.commulativeMAxSize : 0);
        return;
    }

    boolean checkIfValidBSTSubtree(Information subtree) {
        if (subtree == null || ((subtree.minValue <= subtree.maxValue) && subtree.getSize() > 0)) {
            return true;
        }
        return false;
    }
}


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
class Information {
    int maxValue;
    int minValue;
    int size;
    int commulativeMAxSize;
}
