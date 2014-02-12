package systemTests;

import java.util.Arrays;
import java.util.Vector;

import org.jmock.integration.junit3.MockObjectTestCase;
import org.jmock.Expectations;

import system.LabelManagement;
import dataAccessLayer.IDatabase;
import domainobjects.IDSet;
import domainobjects.Label;

public class LabelManagementTests extends MockObjectTestCase
{
	protected void setUp() throws Exception 
	{
		labelMgmt = new LabelManagement(mockDatabase);
		super.setUp();
	}
	
	public void test_Get_all_label_Ids()
	{
		Integer[] array = {1, 2, 4};
		int [] intArray = {1, 2, 4};
		final Vector<Integer> ids = new Vector<Integer>(Arrays.asList(array));
		
		IDSet expectedResult = IDSet.createFromArray(intArray);
		IDSet actualResult;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).getAllLabelIDs(); will(returnValue(ids));
        }});
        
        actualResult = labelMgmt.getAllLabelIDs();
        assertTrue(expectedResult.equals(actualResult));
        verify();
	}
	
	public void test_Get_label_by_id()
	{
		final Label expectedLabel = new Label("Food");
		final int labelId = 5;
		Label actualLabel;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).getLabelByID(labelId); will(returnValue(expectedLabel));
        }});
        
        actualLabel = labelMgmt.getLabelByID(labelId);
        assertEquals(expectedLabel, actualLabel);
        verify();
	}
	
	public void test_Register_label_successfully()
	{
		final Label label = new Label("Junk Food");
		final int expectedLabelId = 4;
		int actualLabelId;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).addLabel(label); will(returnValue(expectedLabelId));
        }});
        
        actualLabelId = labelMgmt.registerLabel(label);
        assertEquals(expectedLabelId, actualLabelId);
        verify();
	}
	
	public void test_Update_label_successfully()
	{
		final Label label = new Label("Healthy Food");
		final int labelId = 2;
		final boolean expectedResult = true;
		boolean actualResult;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).updateLabel(labelId, label); will(returnValue(expectedResult));
        }});
        
        actualResult = labelMgmt.updateLabel(labelId, label);
        assertEquals(expectedResult, actualResult);
        verify();
	}
	
	private LabelManagement labelMgmt;
	final IDatabase mockDatabase = mock(IDatabase.class);
}
