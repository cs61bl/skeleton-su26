import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testBasicWithdraw() {
        System.out.println("Creating a new account with a balance of 1000.");
        Account account = new Account(1000);
        System.out.println("Withdraw 500 from the account.");
        assertTrue(account.withdraw(500));
        assertEquals(500, account.getBalance());
        System.out.println("Withdraw 500 from the account.");
        assertTrue(account.withdraw(500));
        assertEquals(0, account.getBalance());
        System.out.println("Withdraw 9999 from the account.");
        assertFalse(account.withdraw(9999));
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testMerge() {
        System.out.println("Create two new accounts each with a balance of 1000.");
        Account rich = new Account(1000);
        Account poor = new Account(1000);
        System.out.println("Merge one account into the other.");
        rich.merge(poor);
        System.out.println("Verify that the resulting balances are correct.");
        assertEquals(0, poor.getBalance());
        assertEquals(2000, rich.getBalance());
    }

    @Test
    public void testParent() {
        System.out.println("Making two accounts to test parent accounts");
        Account parent = new Account(1000);
        Account child = new Account(100, parent);
        assertTrue(child.withdraw(50));
        assertTrue(50 == child.getBalance());
        assertTrue(1000 == parent.getBalance());
        assertTrue(child.withdraw(55));
        assertTrue(0 == child.getBalance());
        assertTrue(995 == parent.getBalance());
    }

    @Test
    public void testParent2() {
        System.out.println("Create an account for kathy with a balance of 500.");
        Account kathy = new Account(500);
        System.out.println("Create an account for megan with a balance of 100 whose parent is kathy.");
        Account megan = new Account(100, kathy);
        System.out.println("Withdraw 50 from megan.");
        assertTrue(megan.withdraw(50));
        System.out.println("Verify that the resulting balances are correct.");
        assertEquals(50, megan.getBalance());
        assertEquals(500, kathy.getBalance());

        System.out.println("Test the successful overdraft scenario by withdrawing 150 from megan.");
        assertTrue(megan.withdraw(150));
        System.out.println("Verify that the resulting balances are correct.");
        assertEquals(0, megan.getBalance());
        assertEquals(400, kathy.getBalance());

        System.out.println("Attempt to withdraw 9001 from megan.");
        assertFalse(megan.withdraw(9001));
        System.out.println("Verify that the resulting balances are unchanged.");
        assertEquals(0, megan.getBalance());
        assertEquals(400, kathy.getBalance());
        System.out.println("Deposit 100 into megan.");
        megan.deposit(100);
        System.out.println("Attempt to withdraw 9001 from megan again.");
        assertFalse(megan.withdraw(9001));
        System.out.println("Verify that the resulting balances are unchanged.");
        assertEquals(100, megan.getBalance());
        assertEquals(400, kathy.getBalance());
    }

    @Test
    public void testGrandparent() {
        System.out.println("Making three accounts to test child, parent, and grandparent accounts");
        Account grandparent = new Account(400);
        Account parent = new Account(300, grandparent);
        Account child = new Account(100, parent);
        assertTrue(child.withdraw(500));
        assertTrue(0 == child.getBalance());
        assertTrue(0 == parent.getBalance());
        assertTrue(300 == grandparent.getBalance());
        assertTrue(child.withdraw(100));
        assertTrue(0 == child.getBalance());
        assertTrue(0 == parent.getBalance());
        assertTrue(200 == grandparent.getBalance());
    }

    @Test
    public void testGrandparent2() {
        System.out.println("Making three accounts to test child, parent, and grandparent accounts");
        Account grandparent = new Account(10);
        Account parent = new Account(5, grandparent);
        Account child = new Account(1, parent);
        assertFalse(child.withdraw(500));
        assertTrue(1 == child.getBalance());
        assertTrue(5 == parent.getBalance());
        assertTrue(10 == grandparent.getBalance());
        assertTrue(child.withdraw(4));
        assertTrue(0 == child.getBalance());
        assertTrue(2 == parent.getBalance());
        assertTrue(10 == grandparent.getBalance());
    }
}
