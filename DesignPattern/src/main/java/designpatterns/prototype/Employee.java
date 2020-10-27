package designpatterns.prototype;

import java.util.ArrayList;
import java.util.List;

public class Employee implements Cloneable{

	private List<String>empList;
	
	public Employee() {
		empList=new ArrayList<>();
	}
	public Employee(List<String> empList) {
	this.empList=empList;	
	}
	
	public void loadData() {
		empList.add("Maneesh");
		empList.add("Ram");
		empList.add("Nitin");
		empList.add("Deepak");
		empList.add("Ramesh");
		
	}
	
	public Object clone() throws CloneNotSupportedException{
		List<String> templist=new ArrayList<>();
		for(String emp:empList) {
			templist.add(emp);
		}
		return new Employee(templist);
		
	}
	
	public List<String>getEmployeeList(){
		return empList;
	}
}
