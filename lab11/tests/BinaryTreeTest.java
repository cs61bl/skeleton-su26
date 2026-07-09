import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class BinaryTreeTest {
    @Test
    public void sampleHeightTest() {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        assertThat(emptyTree.height()).isEqualTo(0);

        BinaryTree<String> sample1 = BinaryTree.sampleTree1();
        assertThat(sample1.height()).isEqualTo(2);
    }

    @Test
    public void testEmptyHeight() {
        BinaryTree t = new BinaryTree();
        assertThat(t.height()).isEqualTo(0);
    }

    @Test
    public void testHeight() {
        BinaryTree<String> t = BinaryTree.sampleTree1();
        assertThat(t.height()).isEqualTo(2);
        t = BinaryTree.sampleTree2();
        assertThat(t.height()).isEqualTo(4);
        t = BinaryTree.sampleTree3();
        assertThat(t.height()).isEqualTo(4);
    }

    @Test
    public void testEmptyBalance() {
        BinaryTree t = new BinaryTree();
        assertThat(t.isCompletelyBalanced()).isTrue();
    }

    @Test
    public void testIsCompletelyBalanced() {
        BinaryTree<String> t = BinaryTree.sampleTree1();
        assertThat(t.isCompletelyBalanced()).isTrue();
        t = BinaryTree.sampleTree2();
        assertThat(t.isCompletelyBalanced()).isFalse();
        t = BinaryTree.sampleTree3();
        assertThat(t.isCompletelyBalanced()).isFalse();
    }

    @Test
    public void testFib5() {
        BinaryTree t = BinaryTree.fibTree(5);
        assertThat(t.getRoot().getItem()).isEqualTo(5);
        assertThat(t.getRoot().getLeft().getItem()).isEqualTo(3);
        assertThat(t.getRoot().getRight().getItem()).isEqualTo(2);
    }

    @Test
    public void testFib10() {
        BinaryTree<Integer> t = BinaryTree.fibTree(10);
        assertThat((int) t.getRoot().getItem()).isEqualTo(55);
        assertThat((int) t.getRoot().getLeft().getItem()).isEqualTo(34);
        assertThat((int) t.getRoot().getRight().getItem()).isEqualTo(21);
    }

    /* Tests that constructor creates expected tree given preorder and inorder. */
    @Test
    public void binaryTreeConstructorTest() {
        BinaryTree<String> x;
        ArrayList<String> pre = new ArrayList<String>();
        ArrayList<String> in = new ArrayList<String>();
        pre.add("A");
        pre.add("B");
        pre.add("C");
        pre.add("D");
        pre.add("E");
        pre.add("F");
        in.add("B");
        in.add("A");
        in.add("E");
        in.add("D");
        in.add("F");
        in.add("C");
        x = new BinaryTree<String>(pre, in);
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        BinaryTree.print(x, "x");
        System.setOut(oldOut);

        assertWithMessage("The preorder and/or inorder traversals for the given BinarySearchTree are incorrect")
                .that(outContent.toString().trim())
                .isEqualTo("x in preorder\nA B C D E F \nx in inorder\nB A E D F C \n\n".trim());

    }
}