package ru.vsu.cs.dolzhenkoms.Tests;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.dolzhenkoms.MathOperation;

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

    @Test
    public void getNewDoubleArrayByRule2() {
        int[][] expectedArray = {
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        };
        int[][] testArray = MathOperation.getNewDoubleArrayByRule(new int[][]{
                {3,4,4,3},
                {3,5,5,3},
                {3,4,4,3}
        });

        Assert.assertEquals(testArray, expectedArray);
    }

    @Test
    public void getNewDoubleArrayByRule3() {
        int[][] expectedArray = {
                {1,1,1,1},
        };
        int[][] testArray = MathOperation.getNewDoubleArrayByRule(new int[][]{
                {1,1,1,1},
        });

        Assert.assertEquals(testArray, expectedArray);
    }

    @Test
    public void getNewDoubleArrayByRule4() {
        int[][] expectedArray = {
                {1}
        };
        int[][] testArray = MathOperation.getNewDoubleArrayByRule(new int[][]{
                {1}
        });

        Assert.assertEquals(testArray, expectedArray);
    }
}