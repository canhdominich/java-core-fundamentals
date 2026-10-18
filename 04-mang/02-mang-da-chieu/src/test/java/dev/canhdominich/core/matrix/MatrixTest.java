package dev.canhdominich.core.matrix;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class MatrixTest {

    private final int[][] a = {{1, 2, 3}, {4, 5, 6}};
    private final int[][] c = {{1, 2}, {3, 4}, {5, 6}};

    @Test
    void aTwoDimensionalArrayIsAnArrayOfRows() {
        assertThat(Matrices.shape(a)).isEqualTo("2 rows, first row has 3 columns");
        int[][] grid = new int[3][4];
        assertThat(grid.length).isEqualTo(3);
        assertThat(grid[0].length).isEqualTo(4);
    }

    @Test
    void addTransposeAndMultiplyFollowTheMathRules() {
        assertThat(Matrices.add(a, new int[][] {{10, 20, 30}, {40, 50, 60}})).isDeepEqualTo(new int[][] {{11, 22, 33}, {44, 55, 66}});
        assertThat(Matrices.transpose(a)).isDeepEqualTo(new int[][] {{1, 4}, {2, 5}, {3, 6}});
        assertThat(Matrices.multiply(a, c)).isDeepEqualTo(new int[][] {{22, 28}, {49, 64}});
    }

    @Test
    void incompatibleShapesAreRejectedWithAClearMessage() {
        assertThatThrownBy(() -> Matrices.multiply(a, a))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("columns of the left matrix (3) must equal rows of the right matrix (2)");
        assertThatThrownBy(() -> Matrices.add(a, c)).isInstanceOf(IllegalArgumentException.class).hasMessage("shapes differ: 2x3 and 3x2");
    }

    @Test
    void rowSumsWorkWithRowsOfAnyLength() {
        assertThat(Matrices.rowSums(a)).containsExactly(6, 15);
        assertThat(Matrices.rowSums(new int[][] {{1}, {1, 2, 3}, {}})).containsExactly(1, 6, 0);
    }

    @Test
    void jaggedRowsCanHaveDifferentLengths() {
        assertThat(Matrices.pascal(5)).isDeepEqualTo(new int[][] {{1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 6, 4, 1}});
    }

    @Test
    void neighbourCountingStaysInsideTheGrid() {
        int[][] grid = {{1, 0, 1}, {0, 1, 0}, {1, 1, 0}};
        assertThat(Matrices.neighbours(grid, 0, 0)).isEqualTo(1);
        assertThat(Matrices.neighbours(grid, 1, 1)).isEqualTo(4);
    }

    @Test
    void fillingWithOneRowSharesThatRowEverywhere() {
        assertThat(Matrices.sharedRowBug()).isEqualTo("[[1, 0, 0], [1, 0, 0], [1, 0, 0]], all rows are one object: true");
        assertThat(Matrices.correctRows()).isEqualTo("[[1, 0, 0], [0, 0, 0], [0, 0, 0]]");
    }

    @Test
    void cloneCopiesTheRowReferencesNotTheRows() {
        assertThat(Matrices.shallowClone()).isEqualTo("original [[99, 2], [3, 4]], same row object: true");
        assertThat(Matrices.deepClone()).isEqualTo("original [[1, 2], [3, 4]], copy [[99, 2], [3, 4]]");
    }

    @Test
    void anUnallocatedRowIsNullAndThrowsWhenUsed() {
        assertThat(Matrices.unallocatedRow()).isEqualTo("NullPointerException: Cannot load from int array because \"jagged[1]\" is null");
    }

    @Test
    void toStringOfATwoDimensionalArrayShowsAddressesButDeepToStringShowsValues() {
        assertThat(Arrays.toString(a)).startsWith("[[I@");
        assertThat(Arrays.deepToString(a)).isEqualTo("[[1, 2, 3], [4, 5, 6]]");
    }

    @Test
    void bothTraversalOrdersGiveTheSameTotal() {
        int[][] matrix = new int[50][70];
        for (int r = 0; r < 50; r++) {
            for (int col = 0; col < 70; col++) {
                matrix[r][col] = r + col;
            }
        }
        assertThat(TraversalOrder.byRows(matrix)).isEqualTo(TraversalOrder.byColumns(matrix));
    }

    @Test
    void theCompilerChecksTheNumberOfIndexesAndTheElementType() {
        assertThat(Javac.messages("A", "class A { int f(int[][] m) { return m[0][0][0]; } }")).containsExactly("line 1: array required, but int found");
        assertThat(Javac.messages("A", "class A { void f(int[][] m) { m[0] = 5; } }")).containsExactly("line 1: incompatible types: int cannot be converted to int[]");
        assertThat(Javac.messages("A", "class A { int f(int[][] m) { return m[0][0].length; } }")).containsExactly("line 1: int cannot be dereferenced");
    }
}
