package system;

import java.util.Vector;
import java.util.Date;

import dataAccessLayer.IDatabase;
import dataAccessLayer.StubDatabase;
import domainobjects.Expense;
import domainobjects.IDSet;
import domainobjects.PaymentMethod;

public class ExpenseManagement
{
	public ExpenseManagement(IDatabase inDatabase)
	{
		database = inDatabase;
	}
	
	public IDSet getAllExpenseIDs()
	{
		final Vector<Integer> ids = database.getAllExpenseIDs();
		final int[] setData = new int[ids.size()];
		
		for(int i = 0; i < setData.length; i++)
		{
			setData[i] = ids.get(i).intValue();
		}
		
		final IDSet output = IDSet.createFromArray(setData);
		
		return output;
	}

	public Expense getExpenseByID(int inId)
	{
		return database.getExpenseByID(inId);
	}

	public boolean updateExpense(int inId, Expense inNewValue)
	{
		return database.updateExpense(inId, inNewValue);
	}

	public boolean deleteExpense(int inId)
	{
		return database.deleteExpense(inId);
	}

	public int newExpense()
	{
		int newId = 0;
		final int[] emptySetData = new int[0];
		final IDSet emptySet = IDSet.createFromArray(emptySetData);
		Expense newExpense = new Expense(new Date(), 0, PaymentMethod.CASH, "", -1, emptySet);
		
		newId = database.addExpense(newExpense);
		
		return newId;
	}
	
	private IDatabase database;
}
