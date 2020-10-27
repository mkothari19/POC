package designpatterns.builder;

public class TestBuilderDesignPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Computer computer=new Computer.ComputerBuilder("500 GB","2 GB").setBloothEnable(true).setGraphicCardEnable(false).build();
 
   System.out.println(computer.getHDD());
   System.out.println(computer.getRAM());
   System.out.println(computer.isBloothEnable());
   System.out.println(computer.isGraphicCardEnable());
      
	}

}
