package dataAccessLayer;

import java.util.Vector;

import domainobjects.Expense;
import domainobjects.Label;
import domainobjects.PayTo;

public interface IDatabase 
{
	void close();
	
	Expense getExpenseByID(int inId);
	Vector<Integer> getAllExpenseIDs();
	int addExpense(Expense inNewValue);
	boolean updateExpense(int inId, Expense inNewValue);
	boolean deleteExpense(int inId);
	
	Label getLabelByID(int inId);
	Vector<Integer> getAllLabelIDs();
	int addLabel(Label inNewValue);
	boolean updateLabel(int inId, Label inNewValue);
	
	PayTo getPayToByID(int inId);
	Vector<Integer> getAllPayToIDs();
	int addPayTo(PayTo inNewValue);
	boolean updatePayTo(int inId, PayTo inNewValue);
}