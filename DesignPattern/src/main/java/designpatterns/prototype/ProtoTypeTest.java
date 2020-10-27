package designpatterns.prototype;

import java.util.List;

public class ProtoTypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
    Employee emp=new Employee();
    emp.loadData();
    
    Employee emp1=(Employee) emp.clone();
    Employee emp2=(Employee) emp.clone();
    List<String> list1=emp1.getEmployeeList();
    list1.remove("Maneesh");
    System.out.println(list1);
    List<String> list2=emp2.getEmployeeList();
    list2.add("Kothari");
    System.out.println(list2);
	}

}
