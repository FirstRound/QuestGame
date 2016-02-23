enum ActionType { GO, PUSH, TAKE, SELF}

class Action {

	private ActionType type;
	private int id;
	private int value;

	public Action(ActionType type, int id, int value) {
		this.type = type;
		this.id = id;
		this.value = value;
	}

	public Action(ActionType type) {
		this.type = type;
	}

	public ActionType getActionType() {
		return type;
	}

	public int getValue() {
		return value;
	}
	
	public int getId() {
		return id;
	}
}