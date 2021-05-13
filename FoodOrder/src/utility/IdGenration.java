package utility;


public class IdGenration {
	

	private static int itemId = 1;
	static int orderId = 1;
	

	public static String IdGen(String name, int pin) {
		String names = name.substring(0,3);
		String pins = String.valueOf(pin).substring(0,3);
		return names + pins + "";
	}

	public static int IdGenForOrder()
	{
		return orderId++;
	}

}
