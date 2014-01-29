import java.util.Date;
import java.util.Set;

public class IDSetTest
{
	public static void main(String[] args)
	{
		int[] data0 = { 1, 2, 2, 3, 4, 5, 6, 7, 8, 9 };
		IDSet set0 = IDSet.CreateFromArray(data0);
 
 		// make sure a set has all the values that were in the original data
		for(int i = 0; i < data0.length; i++)
		{
			assert set0.contains(data0[0]);
		}

 		int[] data1 = { 2, 4, 6, 8, 10 };
 		IDSet set1 = IDSet.CreateFromArray(data1);

 		// make sure that copy to array works correctly. Data1 must be in order for this test to work
 		int[] data1Again = new int[data1.length];
 		set1.copyToArray(data1Again);

 		assert data1Again.length == data1.length;

 		for(int i = 0; i < data1Again.length; i++)
 		{
 			assert data1Again[i] == data1[i];
 		}

 		// make sure a union contains everything from both sets
 		final IDSet union = set0.union(set1);

		for(int i = 0; i < data0.length; i++)
		{
			assert union.contains(data0[0]);
		}
		for(int i = 0; i < data1.length; i++)
		{
			assert union.contains(data1[0]);
		}		

		// make sure that a self intersection and union do not change change a set
		assert set0.equals(set0.intersect(set0));
		assert set0.equals(set0.union(set0));

		// make sure that only values in the intersection are in both original sets
		final IDSet intersection = set0.intersect(set1);
		
		for(int i = 0; i < intersection.getSize(); i++)
		{
			final int value = intersection.getValue(i);
			assert set0.contains(value) && set1.contains(value);
		}

		System.out.println("Tests complete");
	}
}