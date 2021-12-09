package ru.vsu.cs.dolzhenkoms.Utils;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArrayUtils {

    public static int[] readArrayFromLine(String str) {
        String[] splitString = str.split(" ");

        int[] array = new int[splitString.length];

        for(int i = 0; i < array.length; i++) {
            try {
                array[i] = Integer.parseInt(splitString[i]);
            }
            catch(Exception exc) {
                array[i] = 0;
            }
        }

        return array;
    }

    public static int[][] readArray2FromLines(String[] str) {
        int[][] array2 = new int[str.length][];

        for(int i = 0; i < str.length; i++) {
            array2[i] = readArrayFromLine(str[i]);
        }

        return array2;
    }

    public static void printArray(int[] array) {
        for (int j : array) {
            System.out.printf("%s ", j);
        }
        System.out.println();
    }

    public static void printArray2(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            printArray(array[i]);
            System.out.println();
        }
    }

    public static String convertArrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < array.length; i++) {
            sb.append(array[i]+ " ");
        }

        return sb.toString();
    }

    public static String convertArray2ToString(int[][] array) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < array.length; i++) {
            sb.append(convertArrayToString(array[i]));
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String[] toPrimitive(List<String> lines) {
        return lines.toArray(new String[lines.size()]);
    }

    public static int[] toPrimitive(Integer[] objectArray) {
        int[] primitiveArray = new int[objectArray.length];

        for(int i = 0; i < objectArray.length; i++) {
            primitiveArray[i] = objectArray[i];
        }

        return primitiveArray;
    }

    public static Integer[] toObject(int[] primitiveArray) {
        Integer[] objectArray = new Integer[primitiveArray.length];

        for(int i = 0; i < primitiveArray.length; i++) {
            objectArray[i] = primitiveArray[i];
        }

        return objectArray;
    }

    public static Integer[][] toObject(int[][] array) {
        Integer[][] objectArray = new Integer[array.length][];

        for(int i = 0; i < array.length; i++) {
            objectArray[i] = toObject(array[i]);
        }

        return objectArray;
    }

    public static int[] fillRandomValuesInArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            array[i] = (new Random()).nextInt(30 + 1);
        }

        return array;
    }

    public static int[][] get2ArrayFromTable(DefaultTableModel model) {
        int[][] array = new int[model.getRowCount()][model.getColumnCount()];

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++)
                array[i][j] = (int)model.getValueAt(i,j);
        }

        return array;
    }

    public static void fillTableModelBy2Array(DefaultTableModel model, int[][] array) {
        int rowCount = model.getRowCount();

        for(int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        model.setColumnCount(array[0].length);

        for(int i = 0; i < array.length; i++) {
            model.addRow(ArrayUtils.toObject(array[i]));
        }
    }
}
