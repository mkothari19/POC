package designpatterns.builder;

public class Computer {

private String RAM;
private String HDD;
private boolean isGraphicCardEnable;
private boolean isBloothEnable;
public String getRAM() {
	return RAM;
}
public String getHDD() {
	return HDD;
}

public boolean isGraphicCardEnable() {
	return isGraphicCardEnable;
}
public boolean isBloothEnable() {
	return isBloothEnable;
}
private Computer(ComputerBuilder builder) {
	this.HDD=builder.HDD;
	this.RAM=builder.RAM;
	this.isGraphicCardEnable=builder.isGraphicCardEnable;
	this.isBloothEnable=builder.isBloothEnable;
	
}

public static class ComputerBuilder{
	private String RAM;
	private String HDD;
	private boolean isGraphicCardEnable;
	private boolean isBloothEnable;
	
	public ComputerBuilder(String RAM,String HDD) {
		this.RAM=RAM;
		this.HDD=HDD;
	}

	public ComputerBuilder setGraphicCardEnable(boolean isGraphicCardEnable) {
		this.isGraphicCardEnable = isGraphicCardEnable;
		return this;
	}

	public ComputerBuilder setBloothEnable(boolean isBloothEnable) {
		this.isBloothEnable = isBloothEnable;
		return this;
	}
	public Computer build(){
		return new Computer(this);
	}

}
	

}
