import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.*;

public class TestGraph {

    @Test
    public void testPath1() {
        Graph g = new Graph(2);
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(0, 1)).isFalse();
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(1, 0)).isFalse();

        List<Integer> path1 = g.path(0, 1);
        List<Integer> path2 = g.path(1, 0);

        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path1.size() == 0).isTrue();
        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path2.size() == 0).isTrue();
    }

    // TODO: add more tests!
}
