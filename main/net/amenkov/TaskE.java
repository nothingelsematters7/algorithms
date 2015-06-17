package net.amenkov;

import net.amenkov.utils.Pair;
import net.amenkov.utils.io.FastScanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskE {
    Pair<Long, Integer>[] a;
    long[] sum;
    int[] index;
    int n, q;

    public void sort(int from, int to) {
        to = Math.min(to, n);
        if (from > to) return;
        Arrays.sort(a, from, Math.min(to, n), new Comparator<Pair<Long, Integer>>() {
            @Override
            public int compare(Pair<Long, Integer> o1, Pair<Long, Integer> o2) {
                return o1.first.compareTo(o2.first);
            }
        });
        updateIndices(from, to);
    }

    public void updateIndices(int from, int to) {
        for (int i = from; i < to; i++) {
            index[a[i].second] = i;
        }
    }

    private int search(int len, int j, long toFind) {
        int from = j * len, to = Math.min((j + 1) * len, n);
        if (from > to) return -1;

        return Arrays.binarySearch(a, from, to,
                new Pair<>(toFind, 0),
                new Comparator<Pair<Long, Integer>>() {
                    @Override
                    public int compare(Pair<Long, Integer> o1, Pair<Long, Integer> o2) {
                        return o1.first.compareTo(o2.first);
                    }
                });
    }

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        n = in.nextInt();
        q = in.nextInt();

        long[] ar = in.readLongArray(n);
        int len = (int) Math.sqrt(n + 0.0) + 1;
        sum = new long[len];
        index = new int[n];
        a = new Pair[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Pair<>(ar[i], i);
            index[i] = i;
        }

        Arrays.fill(sum, 0);

        for (int i = 0; i < len; i++) {
            sort(i * len, (i + 1) * len);
        }

        for (int i = 0; i < q; i++) {
            int type = in.nextInt();
            if (type == 1) { // update
                int l = in.nextInt() - 1, r = in.nextInt() - 1, x = in.nextInt();
                for (int j = l; j <= r;) {
                    if (j % len == 0 && j + len - 1 <= r) {
                        sum[j / len] += x;
                        j += len;
                        sort(j, j + len);
                    } else {
                        a[index[j]].first += x;
                        if ((j + 1) % len == 0) {
                            sort(j + 1 - len, j + 1);
                        } else if (j == r) {
                            sort(j - j % len, j - j % len + len);
                        }
                        ++j;
                    }
                }
            } else { // get
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                long y = in.nextLong();
                for (int j = 0; j < len; j++) { // iterate blocks
                    long toFind = y - sum[j];
                    int ind = search(len, j, toFind);

                    if (ind < 0) continue;

                    int ind1 = ind;
                    while (ind1 >= j * len && a[ind1].first.equals(toFind)) {
                        min = Math.min(min, a[ind1].second);
                        max = Math.max(max, a[ind1].second);
                        --ind1;
                    }

                    int ind2 = ind, to = Math.min(n, (j + 1) * len);
                    while (ind2 < to && a[ind2].first.equals(toFind)) {
                        min = Math.min(min, a[ind2].second);
                        max = Math.max(max, a[ind2].second);
                        ++ind2;
                    }
                }

                if (min == Integer.MAX_VALUE) {
                    if (max != Integer.MIN_VALUE) throw new RuntimeException();
                    out.println(-1);
                } else {
                    out.println(max - min);
                }
            }
        }

    }
}
