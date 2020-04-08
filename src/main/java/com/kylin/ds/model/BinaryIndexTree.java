package com.kylin.ds.model;

import java.util.Arrays;

/** Motivation:
 * 
 * Given a 1d array of n elements
 * 
 * use prefix sums:
 * query - o(1)
 * update - o(n)
 * 
 * Fenwick tree was proposed to solve the prefix sum problem
 * Idea: store partial sum in each node and get total sum by traversing the tree from leaf to root
 * The height of the tree is log(n)
 * 
 * query - o(logn)
 * update - o(logn)
 */

public class BinaryIndexTree {

    int n;
    int[] tree;
    int[] data;

    public BinaryIndexTree () {
    }

    public BinaryIndexTree (int[] data) {
        this.n = data.length;
        this.tree = new int[n+1];
        this.data = new int[n];
        Arrays.fill(this.data, 0);
        for (int i = 0; i < data.length; i++) {
            update(i, data[i]);
        }
    }

    int lowbit(int x) { return x & -x; }

    public void update(int i, int value) {
        int delta = value - data[i];
        data[i] = value;
        i = i + 1;
        while (i < tree.length) {
            tree[i] += delta;
            i += lowbit(i);
        }
    }

    int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    public int query (int start, int end) {
        if (start > end) return 0;
        return query(end+1) - query(start);
    }

    public int[] getTree() {
        return tree;
    }

    public int[] getData() {
        return data;
    }

}