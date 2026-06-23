import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertThat;
public class ArrayOperationsTest {

    @Test
    public void testInsert() {
        int[] values = {1, 2, 3, 4, 5};
        ArrayOperations.insert(values, 0, 0);
        int[] afterInsert1 = {0, 1, 2, 3, 4};
        assertThat(check(afterInsert1, values)).isTrue();

        ArrayOperations.insert(values, 4, 7);
        int[] afterInsert2 = {0, 1, 2, 3, 7};
        assertThat(check(afterInsert2, values)).isTrue();
    }

    @Test
    public void testDelete() {
        int[] values = {1, 2, 3, 4, 5};
        ArrayOperations.delete(values, 4);
        int[] afterDelete1 = {1, 2, 3, 4, 0};
        assertThat(check(afterDelete1, values)).isTrue();

        values[4] = 5;
        ArrayOperations.delete(values, 2);
        int[] afterDelete2 = {1, 2, 4, 5, 0};
        assertThat(check(afterDelete2, values)).isTrue();

        values[4] = 7;
        ArrayOperations.delete(values, 0);
        int[] afterDelete3 = {2, 4, 5, 7, 0};
        assertThat(check(afterDelete3, values)).isTrue();
    }

    @Test
    public void testCatenate() {
        int[] A = {1, 2, 3};
        int[] B = {4, 5};
        int[] firstValues = ArrayOperations.catenate(A, B);
        int[] afterCat1 = {1, 2, 3, 4, 5};
        assertThat(check(afterCat1, firstValues)).isTrue();

        int[] C = {4, 7, 9, 0};
        int[] D = {2, 4, 8, 9, 0, 9, 3};
        int[] secondValues = ArrayOperations.catenate(C, D);
        int[] afterCat2 = {4, 7, 9, 0, 2, 4, 8, 9, 0, 9, 3};
        assertThat(check(afterCat2, secondValues)).isTrue();
    }

    public static boolean check(int[][] expected, int[][] actual) {
        if (expected.length != actual.length) {
            System.out.format("Array length did not match expected length:\n"
                            + "      Expected: %d\n"
                            + "      Got: %d\n",
                    expected.length, actual.length);
            return false;
        }
        for (int k = 0; k < expected.length; k++) {
            if (!check(expected[k], actual[k])) {
                return false;
            }
        }
        return true;
    }

    public static boolean check(int[] expected, int[] actual) {
        if(actual == null) {
            System.out.format("You \"actual\" array is null. Have you implemented the method you are testing?\n\n");
            return false;
        }
        if (expected.length != actual.length) {
            System.out.format("Array length did not match expected length:\n"
                            + "      Expected: %d\n"
                            + "      Got: %d\n",
                    expected.length, actual.length);
            return false;
        }
        for (int k = 0; k < expected.length; k++) {
            if (expected[k] != actual[k]) {
                System.out.format("Array contents different at index %d:\n"
                                + "    Expected: %d\n"
                                + "    Got: %d\n",
                        k, expected[k], actual[k]);
                return false;
            }
        }
        return true;
    }
}