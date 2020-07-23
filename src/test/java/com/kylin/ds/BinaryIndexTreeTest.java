
package com.kylin.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kylin.ds.model.BinaryIndexTree;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BinaryIndexTreeTest {

    private BinaryIndexTree tree;

    @Test
    public void testInitData() {
        tree = new BinaryIndexTree(new int[] {1,2,3,4,5,6,7,8});
        assertEquals(tree.getData()[0], 1);
        assertEquals(tree.getData()[7], 8);
    }

    @Test
    public void testInitTree() {
        tree = new BinaryIndexTree(new int[] {1,2,3,4,5,6,7,8});
        // expected tree[] is {0,1,3,3,10,5,11,7,36}
        assertEquals(tree.getTree()[0], 0);
        assertEquals(tree.getTree()[1], 1);
        assertEquals(tree.getTree()[4], 10);
        assertEquals(tree.getTree()[8], 36);
    }

    @Test
    public void testInsertIntoTree() {
        tree = new BinaryIndexTree(new int[] {1,2,3,4,5,6,7,8});
        tree.update(0, 5);
        assertEquals(tree.getData()[0], 5);
        // expected tree[] is {0,5,7,3,14,5,11,7,40}
        assertEquals(tree.getTree()[2], 7);
        assertEquals(tree.getTree()[4], 14);
        assertEquals(tree.getTree()[6], 11);
        assertEquals(tree.getTree()[8], 40);
    }

    @Test
    public void testInsertAndQueryTree() {
        tree = new BinaryIndexTree(new int[] {1,2,3,4,5,6,7,8});
        assertEquals(tree.query(6, 0), 0);
        assertEquals(tree.query(0, 2), 6);
        assertEquals(tree.query(3, 3), 4);
        assertEquals(tree.query(2, 6), 25);
        tree.update(0, 5);
        assertEquals(tree.query(0, 2), 10);
        assertEquals(tree.query(2, 6), 25);

    }

}