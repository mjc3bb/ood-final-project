package gui.converter;

public class US implements MoneySwitch {
	private double money;
	
	public US (double m) {
		this.money = m;
	}
	public double getCash() {
		return money;
	}
}
