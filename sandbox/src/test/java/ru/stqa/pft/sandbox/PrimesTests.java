package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTests {

    @Test
    public void testPrimesSimple() {
        Assert.assertTrue(Primes.primeIs(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimesNotSimple() {
        Assert.assertFalse(Primes.primeIs(Integer.MAX_VALUE - 2));
    }

}
