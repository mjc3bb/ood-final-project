package gui.converter;

public class MoneyChange implements MoneyAdapter {
	private MoneySwitch money;
	private String type;
	
	//constructor
	public MoneyChange(MoneySwitch money,String type) {
		this.money = money;
		this.type = type;
	}
	
	//this is from the interface do not change
	@Override
	public double getCash() {
		if (type.contentEquals("Pounds")) {
			return UStoPounds(money.getCash());
		} else if(type.contentEquals("Yen")) {
			return UStoYen(money.getCash());
		} else if (type.contentEquals("Peso")) {
			return UStoPesos(money.getCash());
		} else if (type.contentEquals("Euros")) {
			return UStoEuros(money.getCash());
		} else {
			return 0;
		}
		
	}
	
	private double UStoYen(double c) {
		return c * 109.13;
	}

	private double UStoEuros (double c) {
		return c * .91;
	}

	private double UStoPounds (double c) {
		return c * .78;
	}
	private double UStoPesos(double c) {
		return c * 19.13;
	}

}
