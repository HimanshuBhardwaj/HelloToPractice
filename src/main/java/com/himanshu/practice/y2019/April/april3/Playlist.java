package com.himanshu.practice.y2019.April.april3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by himanshubhardwaj on 04/04/19.
 * Statement: https://codeforces.com/contest/1140/problem/C
 * Algo:
 *
 * TODO: Complete
 */
public class Playlist {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        ArrayList<Song> songs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = br.readLine().split(" ");
            songs.add(new Song(Long.parseLong(str[0]), Long.parseLong(str[1])));
        }
        Comparator<Song> comparator = new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return (int) (o2.beauty - o1.beauty);
            }
        };

        Collections.sort(songs, comparator);


        long length = 0;
        long maxE = 0;

        TreeSet<Song> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            set.add(songs.get(i));
            length += songs.get(i).time;
            if (set.size() > k) {
                Song first = set.first();
                set.remove(first);
                length -= first.time;
            }
            maxE = Math.max(maxE, length * songs.get(i).beauty);
        }
        System.out.print(maxE);


    }


}



class Song implements Comparable<Song> {
    long time;
    long beauty;


    public Song(long time, long beauty) {
        this.time = time;
        this.beauty = beauty;
    }

    @Override
    public int compareTo(Song o) {
        return (int) (this.time - o.time);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Song)) return false;
        final Song other = (Song) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.time != other.time) return false;
        if (this.beauty != other.beauty) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $time = this.time;
        result = result * PRIME + (int) ($time >>> 32 ^ $time);
        final long $beauty = this.beauty;
        result = result * PRIME + (int) ($beauty >>> 32 ^ $beauty);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Song;
    }
}