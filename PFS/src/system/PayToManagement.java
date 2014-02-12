package system;

import java.util.Vector;

import dataAccessLayer.*;
import domainobjects.IDSet;
import domainobjects.PayTo;

public class PayToManagement implements IIDReader, IDataReader, IDataModifer
{
	public PayToManagement(IDatabase inDatabase)
	{
		assert inDatabase != null : "Must provide non-null database";

		database = inDatabase;
	}
	
	public IDSet getAllIDs()
	{
		final Vector<Integer> ids = database.getAllPayToIDs();
		final int[] setData = new int[ids.size()];
		
		for(int i = 0; i < setData.length; i++)
		{
			setData[i] = ids.get(i).intValue();
		}

		final IDSet output = IDSet.createFromArray(setData);
		
		return output;
	}
	
	public Object getByID(int inId)
	{
		assert inId >= 0 : "Invalid id";

		return database.getPayToByID(inId);
	}
	
	public int new()
	{
		PayTo newPayTo = new PayTo("Somewhere New");

		return database.addPayTo(newPayTo);
	}
	
	public boolean update(int inId, Object inNewValue)
	{
		assert inId >= 0 : "Invalid ID";
		assert inNewValue != null : "Cannot update with null value";
		assert inNewValue instanceof PayTo : "Can only update with PayTo objects";

		return database.updatePayTo(inId, inNewValue);
	}
	
	public boolean delete(int inID)
	{
		assert false : "Cannot delete a payto location, do not call this function";

		return false;
	}

	private IDatabase database;
}
