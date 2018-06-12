package com.himanshu.practice.com.himanshu.practice.june_12_2018;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree = Tree.insert(tree, 500);
        tree = Tree.insert(tree, -501);
        tree = Tree.insert(tree, 502);
        tree = Tree.insert(tree, -505);
        tree = Tree.insert(tree, 506);
        tree = Tree.insert(tree, -503);
        tree = Tree.insert(tree, 504);
        tree = Tree.insert(tree, -507);
        tree = Tree.insert(tree, 508);


        Tree.inOrder(tree);
        System.out.println();
        Tree.preOrder(tree);
        System.out.println();
        Tree.postOrder(tree);
        System.out.println();



        System.out.println("BFS: ");
        boolean result = Tree.breadthFirstSearch(tree, 504);
        System.out.println();
        System.out.println("BFS result " + result);
        System.out.println();
        System.out.println();
        System.out.println("DFS: ");
        result = Tree.depthFirstSearch(tree, 504);
        System.out.println("DFS result " + result);




    }
}


@NoArgsConstructor
class Tree {
    private TreeNode root;
    @Getter
    private int size;


    //delete this value
    static boolean delete(Tree tree, int value) {
        TreeNode.delete(tree.root, value);
        return true;
    }

    //insert this value into the tree
    static Tree insert(Tree tree, int value) {
        tree.root = TreeNode.insert(tree.root, value);
        return tree;
    }


    static boolean preOrder(Tree tree) {
        TreeNode.preOrder(tree.root);

        return false;
    }


    static boolean postOrder(Tree tree) {
        TreeNode.postOrder(tree.root);
        return false;
    }


    static boolean inOrder(Tree tree) {
        TreeNode.inOrder(tree.root);
        return false;
    }

    public static boolean breadthFirstSearch(Tree tree, int value) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(tree.root);

        return TreeNode.breadthFirstSearch(value, queue);
    }

    public static boolean depthFirstSearch(Tree tree, int value) {
        return TreeNode.depthFirstSearch(tree.root, value);
    }
}


@Getter
class TreeNode {
    @Setter
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
        left = null;
        right = null;
        value = 0;
    }

    public TreeNode(int value) {
        left = null;
        right = null;
        this.value = value;
    }

   static  boolean preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return true;
        }

        System.out.print(treeNode.value + " ");
        return preOrder(treeNode.left) && preOrder(treeNode.right);
    }


    static boolean postOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return true;
        }

        boolean b = postOrder(treeNode.left) && postOrder(treeNode.right);
        System.out.print(treeNode.value + " ");
        return b;
    }


    static boolean inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return true;
        }
        inOrder(treeNode.left);
        System.out.print(treeNode.value + " ");
        inOrder(treeNode.right);
        return true;
    }

    //return true if insertion succeed, false otherwise
    static TreeNode insert(TreeNode treeNode, int value) {
        if (treeNode == null) {
            treeNode = new TreeNode(value);
            return treeNode;
        }
        if (value > treeNode.value) {
            treeNode.right = insert(treeNode.right, value);
        }
        if (value < treeNode.value) {
            treeNode.left = insert(treeNode.left, value);
        }

        return treeNode;
    }

    static TreeNode delete(TreeNode treeNode, int value) {
        if (treeNode == null) {
            return null;
        }

        if (treeNode.value > value) {
            treeNode.left = delete(treeNode.left, value);
        }

        if (treeNode.value < value) {
            treeNode.right = delete(treeNode.right, value);
        }

        if (treeNode.value == value) {
            //Case 0: Leaf child
            if (treeNode.left == null && treeNode.right == null) {
                return null;
            }
            //Case 1: both child are there
            if (treeNode.left != null && treeNode.right != null) {
                TreeNode successor = treeNode.getSuccessor(treeNode);
                treeNode.value = successor.value;
                treeNode.delete(treeNode.right, successor.value);
            }
            //Only right child is there
            if (treeNode.left == null) {
                return treeNode.right;
            }
            if (treeNode.right == null) {
                return treeNode.left;
            }
        }
        return treeNode;
    }

    //Assumption while calling this method is that both child are not null
    static TreeNode getSuccessor(TreeNode treeNode) {
        TreeNode rightChild = treeNode.right;

        while (rightChild.left != null) {
            rightChild = rightChild.left;
        }

        return rightChild;
    }


    //Assumption while calling this method is that both child are not null
    static TreeNode getPredecessor(TreeNode treeNode) {
        TreeNode rightChild = treeNode.left;

        while (rightChild.right != null) {
            rightChild = rightChild.right;
        }


        return rightChild;
    }


    static boolean isLeafNode(TreeNode treeNode) {
        if (treeNode == null) {
            return false;
        }
        if (treeNode.left == null && treeNode.right == null) {
            return true;
        }
        return false;
    }

    static boolean depthFirstSearch(TreeNode treeNode, int value) {
        if(treeNode == null) {
            return false;
        }
        System.out.print(treeNode.value + " ");

        if(treeNode.value == value) {
            return true;
        }
        return depthFirstSearch(treeNode.left, value) || depthFirstSearch(treeNode.right, value);
    }

    static boolean breadthFirstSearch(int value, Queue<TreeNode> queue) {
        if(queue == null || queue.isEmpty()) {
            return false;
        }


        TreeNode treeNode = queue.poll();
        System.out.print(treeNode.value+ " ");
        if(treeNode.value == value) {
            return true;
        }
        if(treeNode.left != null) {
            queue.add(treeNode.left);
        }
        if(treeNode.right != null) {
            queue.add(treeNode.right);
        }
        return breadthFirstSearch(value, queue);
    }

}






