package systemTests;

import java.util.Arrays;
import java.util.Vector;

import org.jmock.integration.junit3.MockObjectTestCase;
import org.jmock.Expectations;

import system.PayToManagement;
import dataAccessLayer.IDatabase;
import domainobjects.IDSet;
import domainobjects.PayTo;

public class PayToManagementTests extends MockObjectTestCase
{
	protected void setUp() throws Exception 
	{
		payToMgmt = new PayToManagement(mockDatabase);
		super.setUp();
	}
	
	public void test_Get_all_payTo_Ids()
	{
		Integer[] array = {3, 6, 7};
		int [] intArray = {3, 6, 7};
		final Vector<Integer> ids = new Vector<Integer>(Arrays.asList(array));
		
		IDSet expectedResult = IDSet.createFromArray(intArray);
		IDSet actualResult;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).getAllPayToIDs(); will(returnValue(ids));
        }});
        
        actualResult = payToMgmt.getAllPayToIDs();
        assertTrue(expectedResult.equals(actualResult));
        verify();
	}
	
	public void test_Get_payTo_by_id()
	{
		final PayTo expectedPayTo = new PayTo("Indigo", "St. Vital");
		final int payToId = 10;
		PayTo actualPayTo;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).getPayToByID(payToId); will(returnValue(expectedPayTo));
        }});
        
        actualPayTo = payToMgmt.getPayToByID(payToId);
        assertEquals(expectedPayTo, actualPayTo);
        verify();
	}
	
	public void test_Register_payTo_successfully()
	{
		final PayTo payTo = new PayTo("Indigo", "Polo Park");
		final int expectedPayToId = 9;
		int actualPayToId;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).addPayTo(payTo); will(returnValue(expectedPayToId));
        }});
        
        actualPayToId = payToMgmt.registerPayTo(payTo);
        assertEquals(expectedPayToId, actualPayToId);
        verify();
	}
	
	public void test_Update_payTo_successfully()
	{
		final PayTo payTo = new PayTo("U of M", "Fort Garry");
		final int payToId = 12;
		final boolean expectedResult = true;
		boolean actualResult;
        
        //Expectations
        checking(new Expectations() {{
            oneOf (mockDatabase).updatePayTo(payToId, payTo); will(returnValue(expectedResult));
        }});
        
        actualResult = payToMgmt.updatePayTo(payToId, payTo);
        assertEquals(expectedResult, actualResult);
        verify();
	}
	
	private PayToManagement payToMgmt;
	final IDatabase mockDatabase = mock(IDatabase.class);
}
