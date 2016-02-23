class GameController {

	private RoomController room;
	private HeroController hero;
	private boolean is_first_enter;

	public GameController() {

		hero = new HeroController();
		room = new RoomController();
		is_first_enter = true;

	}

	public void play() {
		while(hero.isAlive()) {
			if (is_first_enter) {
				room.printRoomInfo();
				is_first_enter = false;
			}
			hero.setEnemiesList(room.getEnemiesList());
			Action hero_action = hero.makeMove();
			switch(hero_action.getActionType()) {
				case GO:
					room.changeRoom();
					is_first_enter = true;
					continue;
				case PUSH:
					room.applyPush(hero_action);
					break;
				case TAKE:
					// take smth
					break;
				case SELF:
					// self action
					continue;
			}
			Action enemy_action = room.pushHero();
			hero.applyMove(enemy_action);
		}
	}	
}