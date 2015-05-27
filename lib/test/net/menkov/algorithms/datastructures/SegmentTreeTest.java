package net.menkov.algorithms.datastructures;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dev_il on 27.05.15.
 */
public class SegmentTreeTest {

    @Test
    public void buildTree() {
        int[] a = new int[]{1, 1, 1, 1, 1};

        SegmentTree tree = new SegmentTree(a.length);
        tree.build(a);
    }

    @Test
    public void sumAfterBuild() {
        int[] a = new int[]{1, 1, 1, 1, 1};

        SegmentTree tree = new SegmentTree(a.length);
        tree.build(a);

        Assert.assertEquals(4, tree.sum(0, 3));
        Assert.assertEquals(1, tree.sum(3, 3));
    }

    @Test
    public void sumAfterUpdate() {
        int[] a = new int[]{1, 1, 1, 1, 1};

        SegmentTree tree = new SegmentTree(a.length);
        tree.build(a);

        Assert.assertEquals(3, tree.sum(1, 3));
        Assert.assertEquals(2, tree.sum(0, 1));
        Assert.assertEquals(2, tree.sum(3, 4));

        tree.update(2, 5);

        Assert.assertEquals(7, tree.sum(1, 3));
        Assert.assertEquals(2, tree.sum(0, 1));
        Assert.assertEquals(2, tree.sum(3, 4));
    }
}
