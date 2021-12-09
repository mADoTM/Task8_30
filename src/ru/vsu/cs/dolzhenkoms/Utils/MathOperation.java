package ru.vsu.cs.dolzhenkoms.Utils;

public class MathOperation {

    public static int[][] getNewDoubleArrayByRule(int[][] array) {
        int[][] newArray = new int[array.length][array[0].length];
        int count = 0;

        for(int y = 0; y < array.length; y++) {
            for(int x = 0; x < array[0].length; x++) {
                count = 1;
                int upLeftCornerX = x - 1, upLeftCornerY = y - 1;
                int upRightCornerX = x + 1, upRightCornerY = y - 1;
                int downLeftCornerX = x - 1, downLeftCornerY = y + 1;
                int downRightCornerX = x + 1, downRightCornerY = y + 1;

                boolean flag = true;
                while(flag == true) {
                    flag = false;
                    if(isPointInsideArray(upLeftCornerX, upLeftCornerY, array[0].length, array.length)) {
                        flag = true;
                        if(array[upLeftCornerY][upLeftCornerX] == array[y][x])
                            count++;
                        upLeftCornerX--; upLeftCornerY--;
                    }
                    if(isPointInsideArray(upRightCornerX, upRightCornerY, array[0].length, array.length)) {
                        flag = true;
                        if(array[upRightCornerY][upRightCornerX] == array[y][x])
                            count++;
                        upRightCornerX++; upRightCornerY--;
                    }
                    if(isPointInsideArray(downLeftCornerX, downLeftCornerY, array[0].length, array.length)) {
                        flag = true;
                        if(array[downLeftCornerY][downLeftCornerX] == array[y][x])
                            count++;
                        downLeftCornerX--; downLeftCornerY++;
                    }
                    if(isPointInsideArray(downRightCornerX, downRightCornerY, array[0].length, array.length)) {
                        flag = true;
                        if(array[downRightCornerY][downRightCornerX] == array[y][x])
                            count++;
                        downRightCornerX++; downRightCornerY++;
                    }
                }

                newArray[y][x] = count;
            }
        }

        return newArray;
    }

    private static boolean isPointInsideArray(int pointX, int pointY, int arrayLength, int arrayHeight) {
        if(pointX < 0 || pointX >= arrayLength)
            return false;
        if(pointY < 0 || pointY >= arrayHeight)
            return false;

        return true;
    }
}
