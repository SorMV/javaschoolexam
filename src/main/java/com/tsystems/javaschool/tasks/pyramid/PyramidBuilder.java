package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        if(inputNumbers.contains(null)) throw new CannotBuildPyramidException("Can't build pyramid from this list of numbers");
        int n = isPyramid(inputNumbers.size());
        if (n != 0) {
            int[][] mas = new int[n][2 * n - 1];
            Collections.sort(inputNumbers);
            int indx = 0;
            for (int i = 0; i < n; i++) {
                int k = n - i - 1;
                for (int j = 0; j <= i; j++) {
                    mas[i][k] = inputNumbers.get(indx);
                    indx += 1;
                    k += 2;
                }
            }
            return mas;
        }
        throw new CannotBuildPyramidException("Can't build pyramid from this list of numbers");
    }

    private int isPyramid(int size) {
        if (size == 0) return 0;
        for (int i = 1; i <= size / 2 + 1; i++) {
            if (size == i * (i + 1) / 2) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        PyramidBuilder pb = new PyramidBuilder();
        int[][] mas = pb.buildPyramid(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println();
        }
    }
}
