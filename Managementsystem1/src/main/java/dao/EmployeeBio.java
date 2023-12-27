package dao;
import java.util.List;
import models.Employee;

public interface EmployeeBio {
	int save(Employee e);
	int update(Employee e);
	int delete(int id);
	int delete(Employee e);
	Employee get(int id);
	List<Employee> getAll();
	

}
