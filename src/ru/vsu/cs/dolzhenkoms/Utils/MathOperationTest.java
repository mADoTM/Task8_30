package ru.vsu.cs.dolzhenkoms.Utils;

import org.junit.Assert;
import org.junit.Test;

public class MathOperationTest {

    @Test
    public void getNewDoubleArrayByRule() {
        int[][] expectedArray = {
                {2,2,1,1},
                {1,1,3,1},
                {1,2,2,1}
        };
        int[][] testArray = MathOperation.getNewDoubleArrayByRule(new int[][]{
                {1,2,3,4},
                {3,2,2,0},
                {1,2,1,0}
        });

        Assert.assertEquals(testArray, expectedArray);
    }
}