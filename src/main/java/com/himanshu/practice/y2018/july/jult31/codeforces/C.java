//package com.himanshu.practice.y2018.july.jult31.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by himanshubhardwaj on 31/07/18.
 */
public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        long m = Long.parseLong(str[1]);
        long commulativeCompressedSize = 0;
        long commulativeUnCompressedSize = 0;
        Song s[] = new Song[n];


        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            s[i] = new Song(Long.parseLong(str[0]), Long.parseLong(str[1]));
            commulativeUnCompressedSize += Long.parseLong(str[0]);
            commulativeCompressedSize += Long.parseLong(str[1]);

        }

//        System.out.println(commulativeUnCompressedSize+"\t"+commulativeCompressedSize+"\t"+m);


        if (commulativeCompressedSize > m) {
            System.out.println("-1");
            return;
        }

        long requiredSaving = commulativeUnCompressedSize - m;
        int count = 0;

        Arrays.sort(s);
//        for (Song ss : s) {
//            System.out.println(ss);
//        }


        for (int i = 0; (i < s.length) && (requiredSaving > 0); i++) {
            count++;
            requiredSaving = requiredSaving - s[i].saving;
        }

        System.out.print(count);


    }
}


class Song implements Comparable<Song> {
    long initialSize;
    long compressedSize;
    long saving;

    public Song(long l, long l1) {
        initialSize = l;
        compressedSize = l1;
        saving = Math.abs(initialSize - compressedSize);
    }

    @Override
    public int compareTo(Song o) {
        return (int) (o.saving - this.saving);
    }

    public String toString() {
        return "Song(initialSize=" + this.initialSize + ", compressedSize=" + this.compressedSize + ", saving=" + this.saving + ")";
    }
}