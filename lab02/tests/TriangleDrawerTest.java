import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TriangleDrawerTest {
    @Test
    public void testTriangleDrawer() {
        TriangleDrawer.drawTriangle();
        // Visually check the console output!
    }

    @Test
    public void testTriangleDrawer2() {
        TriangleDrawer2.drawTriangle();
        // Visually check the console output!
    }
}