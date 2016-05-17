package examples.nbank;

import junit.framework.TestCase;

public class AccountTest
    extends TestCase
{
    public void testApply()
    {
        Customer customer = new Customer("Mary Smith", "111-11-1111");
        Account account = new Account(customer, 1000);
        DepositTransaction deposit = new DepositTransaction(500);
        account.apply(deposit);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200);
        account.apply(withdrawal);
        assertEquals(1300, account.getBalance());
        assertEquals(customer, account.getCustomer());
    }

    public void testApply2()
    {
        Customer customer = new Customer("Mary Smith", "111-11-1111");
        Account account = new Account(customer, 1000);
        DepositTransaction deposit = new DepositTransaction(500);
        account.apply(deposit);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200);
        account.apply(withdrawal);
        assertEquals(1300, account.getBalance());
    }
    
    public void testApply3()
    {
        Customer customer = new Customer("Mary Smith", "111-11-1111");
        Account account = new Account(customer, 1000);
        DepositTransaction deposit = new DepositTransaction(500);
        account.apply(deposit);
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(1501); // intended error to show assertion fail, it should be 1500
        account.apply(withdrawal);
        assertEquals(0, account.getBalance());
    }
}