import org.junit.Test;
import java.util.Arrays;
import static com.google.common.truth.Truth.assertWithMessage;

public class UserTest {
    @Test
    public void testComparable() {
        System.out.println("Test that the given example works."); // technically speaking this is no longer the given example
        User[] users = {
                new User(2, "Christine", ""),
                new User(4, "Kevin", ""),
                new User(5, "Alex", ""),
                new User(1, "Lauren", ""),
                new User(1, "Catherine", "")
        };
        User[] expected = {
                new User(1, "Catherine", ""),
                new User(1, "Lauren", ""),
                new User(2, "Christine", ""),
                new User(4, "Kevin", ""),
                new User(5, "Alex", "")
        };
        Arrays.sort(users);
        assertWithMessage("Sorted array of users is incorrect").that(users).isEqualTo(expected);
    }
}
