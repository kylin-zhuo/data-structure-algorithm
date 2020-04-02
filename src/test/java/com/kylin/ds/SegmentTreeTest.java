package com.kylin.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kylin.ds.model.SegmentTree;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SegmentTreeTest {

    private SegmentTree segTree;

    @Test
    public void initializeSegTree() {

        int[] arr = new int[] {1, 3, 5, 7, 9, 11};
        segTree = new SegmentTree(arr);
        assertTrue(segTree != null);
        assertEquals(segTree.getnNumbers(), 6);
        assertEquals(segTree.getnNodes(), 15);
    }

    @Test 
    public void testGetSum() {

        int[] arr = new int[] {1, 3, 5, 7, 9, 11};
        segTree = new SegmentTree(arr);
        assertEquals(segTree.sum(0, 2), 9);
        assertEquals(segTree.sum(0, 5), 36);
        assertEquals(segTree.sum(3, 3), 7);
        assertEquals(segTree.sum(2, 0), 0);
    }

    @Test 
    public void testInsertValueWithinRange() {

        int[] arr = new int[] {1, 3, 5, 7, 9, 11};
        segTree = new SegmentTree(arr);
        assertEquals(segTree.sum(0, 2), 9);
        assertEquals(segTree.sum(0, 5), 36);
        segTree.update(0, 2);
        assertEquals(segTree.sum(0, 2), 10);
        assertEquals(segTree.sum(0, 5), 37);
    }

    @Test 
    public void testInsertValueOutOfRange() {
        int[] arr = new int[] {1, 3, 5, 7, 9, 11};
        segTree = new SegmentTree(arr);
        assertEquals(segTree.sum(0, 5), 36);
        segTree.update(100, 2);
        assertEquals(segTree.sum(0, 5), 36);
    }

}