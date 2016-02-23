abstract class AbstractItem {

	protected String name;
	protected String action_name;
	protected int price;
	protected int id;
	protected static int items_count;

	public AbstractItem(String name, int price) {
		this.name = name;
		this.price = price;
		this.id = items_count++;
	}

	public String getName() {
		return name;
	}
	
}