package system;

import java.util.Vector;

import dataAccessLayer.*;
import domainobjects.IDSet;
import domainobjects.Label;

public class LabelManagement 
{
	public LabelManagement(IDatabase inDatabase)
	{
		database = inDatabase;
	}
	
	public IDSet getAllLabelIDs()
	{
		final Vector<Integer> ids = database.getAllLabelIDs();
		final int[] setData = new int[ids.size()];
		
		for(int i = 0; i < setData.length; i++)
		{
			setData[i] = ids.get(i).intValue();
		}

		final IDSet output = IDSet.createFromArray(setData);
		
		return output;
	}
	
	public Label getLabelByID(int inId)
	{
		return database.getLabelByID(inId);
	}
	
	public int registerLabel(Label inNewLabel)
	{
		return database.addLabel(inNewLabel);
	}
	
	public boolean updateLabel(int inId, Label inLabelToUpdate)
	{
		return database.updateLabel(inId, inLabelToUpdate);
	}
	
	public static LabelManagement getCurrent()
	{
		assert current != null : "Returning a null label management system";

		return current;
	}
	
	private IDatabase database;
	private static LabelManagement current = new LabelManagement(new StubDatabase());
}
