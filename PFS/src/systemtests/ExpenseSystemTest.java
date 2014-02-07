package systemtests;

import domainobjects.IDSet;
import system.ExpenseSystem;
import junit.framework.TestCase;

public class ExpenseSystemTest extends TestCase
{
	public void testGetCurrentSystem()
	{
		ExpenseSystem current = ExpenseSystem.getCurrent();
		assertNotNull("Current expense system should never be null", current);
	}
	
	public void testGetIDs()
	{
		ExpenseSystem current = ExpenseSystem.getCurrent();
		
		IDSet expenseIDs = current.getExpenseIDs();
		
		assertNotNull("System should never return a null set", expenseIDs);
		assertEquals("System should return empty set when it no data", 0, expenseIDs.getSize());
	}
	
	public void testNewExpense()
	{
		ExpenseSystem current = ExpenseSystem.getCurrent();
		
		int newExpenseID = current.newExpense();
		
		IDSet expenseIDs = current.getExpenseIDs();
		
		assertTrue("System should have entry for new expense", expenseIDs.contains(newExpenseID));
	}
	
	public void testDeleteExpense()
	{
		ExpenseSystem current = ExpenseSystem.getCurrent();
		
		int id = current.newExpense();
		
		current.deleteExpense(id);
		
		IDSet expenseIDs = current.getExpenseIDs();
		
		assertFalse("System should not have a deleted expense", expenseIDs.contains(id));
	}
}
