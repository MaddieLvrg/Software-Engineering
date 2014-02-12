package system;

import java.util.Vector;

import dataAccessLayer.*;
import domainobjects.IDSet;
import domainobjects.PayTo;

public class PayToManagement
{
	public PayToManagement(IDatabase inDatabase)
	{
		database = inDatabase;
	}
	
	public IDSet getAllPayToIDs()
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
	
	public PayTo getPayToByID(int inId)
	{
		return database.getPayToByID(inId);
	}
	
	public int registerPayTo(PayTo inNewPayTo)
	{
		return database.addPayTo(inNewPayTo);
	}
	
	public boolean updatePayTo(int inId, PayTo inPayToToUpdate)
	{
		return database.updatePayTo(inId, inPayToToUpdate);
	}
	
	private IDatabase database;
}
