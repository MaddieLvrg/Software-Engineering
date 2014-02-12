package system;

public interface IDataModifer
{
	int new();
	boolean update(int id, Object data);
	boolean delete(int id);
}
