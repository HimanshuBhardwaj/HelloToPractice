package com.himanshu.practice.Aug.aug26.barlays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by himanshubhardwaj on 26/08/18.
 */
public class FindingTheWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = new char[1]; //rough default length

        try {
            str = br.readLine().toCharArray();
        } catch (Exception e) {
            br.close();
            main(null);
        }

        char[] req = "barclays".toCharArray();

        int changesReq = Integer.MAX_VALUE;
        int pos = -1;

        for (int i = 0; i < str.length; i++) {
            int tChangesReq = getMinChangs(str, i, req);

            if (tChangesReq < changesReq) {
                changesReq = tChangesReq;
                pos = i;
            }
        }

        System.out.println(changesReq);
        for (int i = pos; i < (req.length + pos); i++) {
            if (str[i] != req[i - pos]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();


    }

    private static int getMinChangs(char[] str, int start, char[] req) {
        if ((start + req.length) > str.length) {
            return Integer.MAX_VALUE;
        }

        int count = 0;

        for (int i = start; i < (start + req.length); i++) {
            if (str[i] != req[i - start]) {
                count++;
            }
        }
        return count;
    }
}
