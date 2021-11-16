package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistance1() {
        Point p1 = new Point(1, 1, 5, 5);
        Assert.assertEquals((int) p1.distance(), 5);
        Assert.assertEquals((int) p1.distance(), 6);
    }

    @Test
    public void testDistance2() {
        Point p2 = new Point(0, 0, 0, 0);
        Assert.assertEquals(p2.distance(), 0);
    }

}
