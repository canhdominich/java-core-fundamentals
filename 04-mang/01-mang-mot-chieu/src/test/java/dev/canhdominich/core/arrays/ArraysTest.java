package dev.canhdominich.core.arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ArraysTest {

    @Test
    void newArraysHoldTheDefaultValueOfTheirType() {
        assertThat(ArrayBasics.defaults()).isEqualTo("int [0, 0], double [0.0, 0.0], boolean [false, false], char codes 0, String [null, null]");
    }

    @Test
    void indexesOutsideZeroToLengthMinusOneThrow() {
        assertThat(ArrayBasics.outOfBounds(1)).isEqualTo("data[1] = 20");
        assertThat(ArrayBasics.outOfBounds(3)).isEqualTo("ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3");
        assertThat(ArrayBasics.outOfBounds(-1)).isEqualTo("ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 3");
    }

    @Test
    void aNegativeSizeIsRejectedAndSizeZeroIsAllowed() {
        assertThat(ArrayBasics.negativeSize(-1)).isEqualTo("NegativeArraySizeException: -1");
        assertThat(ArrayBasics.negativeSize(0)).isEqualTo("length 0");
    }

    @Test
    void anArrayOfReferencesStartsFullOfNulls() {
        assertThat(ArrayBasics.nullElement()).startsWith("NullPointerException");
        assertThat(ArrayBasics.nullArray()).startsWith("NullPointerException: Cannot read the array length");
    }

    @Test
    void theLargestPossibleSizeExceedsTheVmLimit() {
        assertThat(ArrayBasics.tooBig()).isEqualTo("OutOfMemoryError: Requested array size exceeds VM limit");
    }

    @Test
    void equalsOnAnArrayComparesIdentityButArraysEqualsComparesContent() {
        int[] first = {1, 2, 3};
        int[] second = {1, 2, 3};
        assertThat(first.equals(second)).isFalse();
        assertThat(java.util.Arrays.equals(first, second)).isTrue();
        assertThat(ArrayBasics.printedDirectly()).startsWith("[I@").endsWith("[1, 2, 3]");
    }

    @Test
    void assigningAnArrayCopiesTheReferenceButCloneCopiesTheElements() {
        assertThat(ArrayCopies.alias()).isEqualTo("a = [99, 2, 3], same array: true");
        assertThat(ArrayCopies.cloned()).isEqualTo("a = [1, 2, 3], c = [1, 77, 3]");
    }

    @Test
    void copyOfPadsWithDefaultsOrTruncates() {
        assertThat(ArrayCopies.copyOf()).isEqualTo("bigger [1, 2, 3, 0, 0], smaller [1, 2], range [2, 3]");
    }

    @Test
    void arraycopyCopiesARangeAndHandlesOverlap() {
        assertThat(ArrayCopies.arraycopy()).isEqualTo("[0, 0, 2, 3, 4, 0, 0]");
        assertThat(ArrayCopies.overlappingArraycopy()).isEqualTo("[1, 1, 2, 3, 4]");
        assertThat(ArrayCopies.arraycopyTooLong()).startsWith("ArrayIndexOutOfBoundsException: arraycopy: last destination index 3");
    }

    @Test
    void cloningAnArrayOfObjectsIsShallow() {
        assertThat(ArrayCopies.shallowCopy()).isEqualTo("original values 99, 2 | copy values 99, 50 | same first element: true");
        assertThat(ArrayCopies.deepCopy()).isEqualTo("original first 1, copy first 99");
    }

    @Test
    void commonTraversalsGiveTheExpectedResults() {
        int[] data = {4, 9, 1, 7};
        assertThat(ArrayTraversal.sum(data)).isEqualTo(21);
        assertThat(ArrayTraversal.maxOf(data)).isEqualTo(9);
        assertThat(ArrayTraversal.indexOf(data, 7)).isEqualTo(3);
        assertThat(ArrayTraversal.indexOf(data, 5)).isEqualTo(-1);
        assertThat(ArrayTraversal.reversed(data)).containsExactly(7, 1, 9, 4);
        assertThat(ArrayTraversal.runningSum(data)).containsExactly(4, 13, 14, 21);
        assertThatThrownBy(() -> ArrayTraversal.maxOf(new int[0])).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rotationWrapsAroundAndStepsAreTakenModuloTheLength() {
        int[] data = {4, 9, 1, 7};
        assertThat(ArrayTraversal.rotateLeft(data, 1)).containsExactly(9, 1, 7, 4);
        assertThat(ArrayTraversal.rotateLeft(data, 5)).containsExactly(9, 1, 7, 4);
    }

    @Test
    void theLoopVariableOfForEachIsACopyOfTheElement() {
        assertThat(ArrayTraversal.forEachCannotWrite(new int[] {4, 9, 1, 7})).containsExactly(4, 9, 1, 7);
    }

    @Test
    void aHistogramUsesTheCharacterAsAnIndex() {
        int[] counts = ArrayTraversal.histogram("Hello Java");
        assertThat(counts['a' - 'a']).isEqualTo(2);
        assertThat(counts['l' - 'a']).isEqualTo(2);
    }

    @Test
    void theCompilerRejectsMalformedArrayDeclarations() {
        assertThat(Javac.messages("A", "class A { int[] f() { return new int[3] {1, 2, 3}; } }"))
                .containsExactly("line 1: array creation with both dimension expression and initialization is illegal");
        assertThat(Javac.messages("A", "class A { void f(int[] a) { a.length = 5; } }"))
                .containsExactly("line 1: cannot assign a value to final variable length");
        assertThat(Javac.messages("A", "class A { int[] a = new int[]; }")).containsExactly("line 1: array dimension missing");
    }
}
