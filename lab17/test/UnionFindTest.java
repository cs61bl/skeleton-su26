import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.fail;

public class UnionFindTest {

    /*
    Initial state test
    */
    @Test
    public void test0() {
        UnionFind uf = new UnionFind(4);
        assertThat(uf.connected(0, 1)).isFalse();
        assertThat(uf.connected(0, 2)).isFalse();
        assertThat(uf.connected(0, 3)).isFalse();
        assertThat(uf.connected(1, 2)).isFalse();
        assertThat(uf.connected(1, 3)).isFalse();
        assertThat(uf.connected(2, 3)).isFalse();
    }

    /*
    Illegal find test
    */
    @Test
    public void test1() {
        UnionFind uf = new UnionFind(4);
        try {
            uf.find(10);
            fail("Cannot find an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
        try {
            uf.union(1, 10);
            fail("Cannot union with an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /*
    Basic union test
    */
    @Test
    public void test2() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        assertThat(uf.find(0)).isEqualTo(1);
        uf.union(2, 3);
        assertThat(uf.find(2)).isEqualTo(3);
        uf.union(0, 2);
        assertThat(uf.find(1)).isEqualTo(3);

        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(4, 8);
        uf.union(4, 6);

        assertThat(uf.find(5)).isEqualTo(9);
        assertThat(uf.find(7)).isEqualTo(9);
        assertThat(uf.find(8)).isEqualTo(9);

        uf.union(9, 2);
        assertThat(uf.find(3)).isEqualTo(9);
    }

    /*
    Same union
    */
    @Test
    public void test3() {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 1);
        for (int i = 0; i < 4; i += 1) {
            assertThat(uf.find(i)).isEqualTo(i);
        }
    }

    /*
    Complete test
    */
    @Test
    public void test4() {
        UnionFind uf = new UnionFind(5);
        uf.union(0, 1);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.find(1)).isEqualTo(1);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.find(0)).isEqualTo(1);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.sizeOf(1)).isEqualTo(2);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.sizeOf(0)).isEqualTo(2);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(0, 1)).isTrue();
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(0, 2)).isFalse();
        uf.union(3, 2);
        assertWithMessage("Merging vertices 3 and 2 causes error!").that(uf.find(2)).isEqualTo(2);
        assertWithMessage("Merging vertices 3 and 2 causes error!").that(uf.find(3)).isEqualTo(2);
        assertWithMessage("Merging vertices 3 and 2 causes error!").that(uf.sizeOf(2)).isEqualTo(2);
        assertWithMessage("Merging vertices 3 and 2 causes error!").that(uf.sizeOf(3)).isEqualTo(2);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(0, 1)).isTrue();
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(3, 2)).isTrue();
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(0, 2)).isFalse();
        uf.union(2, 1);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.parent(3)).isEqualTo(2);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.find(3)).isEqualTo(1);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.parent(2)).isEqualTo(1);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.find(2)).isEqualTo(1);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.sizeOf(2)).isEqualTo(4);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.sizeOf(1)).isEqualTo(4);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.sizeOf(0)).isEqualTo(4);
        assertWithMessage("Merging vertices 2 and 1 causes error!").that(uf.sizeOf(3)).isEqualTo(4);
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(0, 1)).isTrue();
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(3, 2)).isTrue();
        assertWithMessage("Merging vertices 0 and 1 causes error!").that(uf.connected(0, 2)).isTrue();
        uf.union(0, 4);
        uf.union(4, 0);
        assertWithMessage("Check your logic for union!").that(uf.find(0)).isEqualTo(1);
        assertWithMessage("Check your logic for union!").that(uf.find(4)).isEqualTo(1);
        assertWithMessage("Check your logic for union!").that(uf.find(3)).isEqualTo(1);
        assertWithMessage("Check your logic for union!").that(uf.sizeOf(2)).isEqualTo(5);
        assertWithMessage("Check your logic for union!").that(uf.sizeOf(4)).isEqualTo(5);
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                assertWithMessage("Fully connected disjoint set not showing connected for all!").that(uf.connected(i, j)).isTrue();
            }
        }
    }

    /*
    Path Compression Test
    */
    @Test
    public void test5() {
        UnionFind uf = new UnionFind(8);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);

        uf.union(0, 2);
        uf.union(5, 7);
        uf.union(7, 3);

        String errorMsg = "Union calls that have been made, in this order: \n";
        errorMsg += "union(0, 1)\nunion(2, 3)\nunion(4, 5)\nunion(6, 7)\nunion(0, 2)\nunion(5, 7)\nunion(7, 3)\n";

        String methodCalls = "";
        assertWithMessage(errorMsg + "\nfind(0) returns incorrect output\n").that(uf.find(0)).isEqualTo(3);

        methodCalls += "\nMethod calls that have been made up this point:\n";
        methodCalls += "find(0)\n";

        assertWithMessage(errorMsg + methodCalls + "\nfind(4) returns incorrect output\n").that(uf.find(4)).isEqualTo(3);
        methodCalls += "find(4)\n";

        assertWithMessage(errorMsg + methodCalls + "\nfind(6) returns incorrect output\n").that(uf.find(6)).isEqualTo(3);
        methodCalls += "find(6)\n";

        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                assertWithMessage(errorMsg + methodCalls + "\nparent(" + i + ") returns incorrect output\n")
                        .that(uf.parent(i)).isEqualTo(3);
            } else {
                assertWithMessage(errorMsg + methodCalls + "\nparent(" + i + ") returns incorrect output\n")
                        .that(uf.parent(i)).isEqualTo(-8);
            }
        }
    }

}
