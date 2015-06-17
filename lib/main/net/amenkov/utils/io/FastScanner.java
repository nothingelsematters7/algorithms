package net.amenkov.utils.io;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by dev_il on 16.06.15.
 */
public class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
    }

    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public int[] readIntArray(int n) {
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = nextInt();
        }

        return ar;
    }

    public long[] readLongArray(int n) {
        long[] ar = new long[n];
        for (int i = 0; i < n; i++) {
            ar[i] = nextLong();
        }

        return ar;
    }
}