package com.himanshu.practice.y2018.sept.sept15;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SolutionC {

    public static final int MAX = 100000;
    public static final int BAD_INDEX = -1;

    public static final int IDX_MIN_POS = 0;
    public static final int IDX_MAX_POS = 1;
    public static final int IDX_ZERO_VALID_POS = 2;
    public static final int IDX_ONE_VALID_POS = 3;
    public static final int IDX_COUNT = 4;

    private static int[] getIntArray(DataInputStream in, int[] ret) {
        try {
            StringBuilder buff = new StringBuilder(32);
            int to = 0;
            for (int i = in.read(); i != -1; i = in.read()) {
                if (i == '\n')
                {
                    ret[to++] = Integer.parseInt(buff.toString());;
                    buff.delete(0, buff.length());
                    break;
                }
                if (i == ' ')
                {
                    ret[to++] = Integer.parseInt(buff.toString());;
                    buff.delete(0, buff.length());
                    continue;
                }
                buff.append((char)i);
            }
            if (buff.length() > 0)
            {
                ret[to++] = Integer.parseInt(buff.toString());;
                buff.delete(0, buff.length());
            }
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static int new_child(int tree[][])
    {
        int ret = tree[0][0]++;
        int[] chile = tree[ret];
        chile[IDX_ZERO_VALID_POS] = BAD_INDEX;
        chile[IDX_ONE_VALID_POS] = BAD_INDEX;
        chile[IDX_MIN_POS] = Integer.MAX_VALUE;
        chile[IDX_MAX_POS] = Integer.MIN_VALUE;
        return ret;
    }

    private static void initialize(int nod, int idx, int val, int tree[][], int mask)
    {
        int[] node = tree[nod];
        boolean current_bit_is_zero = (val&mask)==0;
        if (mask == 1)
        {
            int chile_idx = BAD_INDEX;
            boolean first_instance = false;
            if (current_bit_is_zero)
                chile_idx = (first_instance = node[IDX_ZERO_VALID_POS]==BAD_INDEX)?node[IDX_ZERO_VALID_POS]=new_child(tree):node[IDX_ZERO_VALID_POS];
            else
                chile_idx = (first_instance = node[IDX_ONE_VALID_POS]==BAD_INDEX)?node[IDX_ONE_VALID_POS]=new_child(tree):node[IDX_ONE_VALID_POS];

            int[] chile = tree[chile_idx];
            if (!first_instance)
            {
                int new_chile_idx = new_child(tree);
                chile = tree[new_chile_idx];
                chile[IDX_MAX_POS] = chile_idx;
                if (current_bit_is_zero)
                    node[IDX_ZERO_VALID_POS]=new_chile_idx;
                else
                    node[IDX_ONE_VALID_POS]=new_chile_idx;
            }
            else
            {
                chile[IDX_MAX_POS] = BAD_INDEX;
            }
            chile[IDX_MIN_POS] = Math.min(chile[IDX_MIN_POS], idx);
        }
        else
        {
            int chile_idx = BAD_INDEX;
            if (current_bit_is_zero)
                chile_idx = (node[IDX_ZERO_VALID_POS]==BAD_INDEX)?node[IDX_ZERO_VALID_POS]=new_child(tree):node[IDX_ZERO_VALID_POS];
            else
                chile_idx = (node[IDX_ONE_VALID_POS]==BAD_INDEX)?node[IDX_ONE_VALID_POS]=new_child(tree):node[IDX_ONE_VALID_POS];
            initialize(chile_idx, idx, val, tree, mask>>1);
        }
        node[IDX_MIN_POS] = Math.min(node[IDX_MIN_POS], idx);
        node[IDX_MAX_POS] = Math.max(node[IDX_MAX_POS] , idx);
    }

    private static int query(int nod, int xor, int tree[][], int i, int j, int mask)
    {
        int[] node = tree[nod];
        if ((node[IDX_MAX_POS] < i) || (node[IDX_MIN_POS] > j))
            return BAD_INDEX;

        boolean current_bit_is_zero = (xor&mask)==0;
        if (mask == 1)
        {
            if (current_bit_is_zero)
            {
                if (node[IDX_ONE_VALID_POS] != BAD_INDEX)
                {
                    int[] chile = null;
                    boolean isValid = false;
                    for (chile = tree[node[IDX_ONE_VALID_POS]];!isValid;chile = tree[chile[IDX_MAX_POS]]) {
                        if (chile[IDX_MIN_POS] < i) break;
                        isValid = (chile[IDX_MIN_POS] >= i) && (chile[IDX_MIN_POS] <= j);
                        if (isValid) break;
                        if (chile[IDX_MAX_POS] == BAD_INDEX) break;
                    }
                    if (isValid)
                        return chile[IDX_MIN_POS];
                }
                if (node[IDX_ZERO_VALID_POS] == BAD_INDEX) return BAD_INDEX;
                int[] chile = null;
                boolean isValid = false;
                for (chile = tree[node[IDX_ZERO_VALID_POS]];!isValid;chile = tree[chile[IDX_MAX_POS]]) {
                    if (chile[IDX_MIN_POS] < i) break;
                    isValid = (chile[IDX_MIN_POS] >= i) && (chile[IDX_MIN_POS] <= j);
                    if (isValid) break;
                    if (chile[IDX_MAX_POS] == BAD_INDEX) break;
                }
                if (isValid)
                    return chile[IDX_MIN_POS];
                return BAD_INDEX;
            }
            else
            {
                if (node[IDX_ZERO_VALID_POS] != BAD_INDEX)
                {
                    int[] chile = null;
                    boolean isValid = false;
                    for (chile = tree[node[IDX_ZERO_VALID_POS]];!isValid;chile = tree[chile[IDX_MAX_POS]]) {
                        if (chile[IDX_MIN_POS] < i) break;
                        isValid = (chile[IDX_MIN_POS] >= i) && (chile[IDX_MIN_POS] <= j);
                        if (isValid) break;
                        if (chile[IDX_MAX_POS] == BAD_INDEX) break;
                    }
                    if (isValid)
                        return chile[IDX_MIN_POS];
                }
                if (node[IDX_ONE_VALID_POS] == BAD_INDEX) return BAD_INDEX;
                int[] chile = null;
                boolean isValid = false;
                for (chile = tree[node[IDX_ONE_VALID_POS]];!isValid;chile = tree[chile[IDX_MAX_POS]]) {
                    if (chile[IDX_MIN_POS] < i) break;
                    isValid = (chile[IDX_MIN_POS] >= i) && (chile[IDX_MIN_POS] <= j);
                    if (isValid) break;
                    if (chile[IDX_MAX_POS] == BAD_INDEX) break;
                }
                if (isValid)
                    return chile[IDX_MIN_POS];
                return BAD_INDEX;
            }
        }
        else
        {
            if (current_bit_is_zero)
            {
                if (node[IDX_ONE_VALID_POS] != BAD_INDEX)
                {
                    int temp = query(node[IDX_ONE_VALID_POS], xor, tree, i, j, mask>>1);
                    if (temp > BAD_INDEX)
                        return temp;
                }

                if (node[IDX_ZERO_VALID_POS] != BAD_INDEX)
                {
                    return query(node[IDX_ZERO_VALID_POS], xor, tree, i, j, mask>>1);
                }

                return BAD_INDEX;
            }
            else
            {
                if (node[IDX_ZERO_VALID_POS] != BAD_INDEX)
                {
                    int temp = query(node[IDX_ZERO_VALID_POS], xor, tree, i, j, mask>>1);
                    if (temp > BAD_INDEX)
                        return temp;
                }

                if (node[IDX_ONE_VALID_POS] != BAD_INDEX)
                {
                    return query(node[IDX_ONE_VALID_POS], xor, tree, i, j, mask>>1);
                }

                return BAD_INDEX;
            }
        }
    }

    public static void main(String[] args)
    {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));

        int[] buff = new int[MAX];
        int[][] tree = new int[MAX*16][IDX_COUNT];

        int[] res = getIntArray(in, new int[4]);
        int T = res[0];
        for (int t = 0; t < T; ++t) {

            res = getIntArray(in, res);
            int N = res[0];
            int Q = res[1];
            buff = getIntArray(in, buff);
            tree[0][0] = 1;
            new_child(tree);
            for (int i = 0; i < N; i++)
                initialize(1, i, buff[i], tree, 0x8000);
            for (int q = 0; q < Q; ++q) {
                res = getIntArray(in, res);
                int xor = res[0];
                int start = res[1];
                int end = res[2];
                int idx = query(1, xor, tree, start-1, end-1, 0x8000);
                int result =  xor ^ buff[idx];
                out.println(result);
            }
        }
        out.flush();
    }
}