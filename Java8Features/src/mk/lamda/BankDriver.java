package mk.lamda;

public class BankDriver {
	public static void main(String[] args) {
	
		
		Bank axis=(rate)->{System.out.println("Axis Bank of Intrest:::"+rate);};
		Bank icici=(rate)->{System.out.println("ICICI Bank of Intrest:::"+rate);};
		Bank yes=(rate)->{System.out.println("Yes Bank of Intrest:::"+rate);};
		Bank sbi=(rate)->{System.out.println("SBI Bank of Intrest:::"+rate);};
		
		axis.intrestRate(7.1);
		icici.intrestRate(7.5);
		yes.intrestRate(7.5);
		sbi.intrestRate(6.5);
		
		
	}

}
