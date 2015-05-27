package net.menkov.algorithms.datastructures;

/**
 * Created by dev_il on 27.05.15.
 */
public class SegmentTree {
    int n;
    int[] t;


    public SegmentTree(int n) {
        this.n = n;
        t = new int[4 * n];
    }

    public void build(int[] a) {
        build(a, 1, 0, n - 1);
    }

    private void build(int[] a, int v, int tl, int tr) {
        if (tl == tr) {
            t[v] = a[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(a, 2 * v, tl, tm);
            build(a, 2 * v + 1, tm + 1, tr);
            t[v] = t[2 * v] + t[2 * v + 1];
        }
    }

    public int sum(int l, int r) {
        return sum(1, 0, n - 1, l, r);
    }

    private int sum(int v, int tl, int tr, int l, int r) {
        if (l > r)
            return 0;

        if (l == tl && r == tr)
            return t[v];

        int tm = (tl + tr) / 2;
        return sum(2 * v, tl, tm, l, Math.min(tm, r)) + sum(2 * v + 1, tm + 1, tr, Math.max(tm + 1, l), r);
    }

    public void update(int index, int val) {
        update(1, 0, n - 1, index, val);
    }

    private void update(int v, int tl, int tr, int pos, int newVal) {
        if (tl == tr) {
            t[v] = newVal;
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm)
                update(2 * v, tl, tm, pos, newVal);
            else
                update(2 * v + 1, tm + 1, tr, pos, newVal);
            t[v] = t[2 * v] + t[2 * v + 1];
        }
    }
}
