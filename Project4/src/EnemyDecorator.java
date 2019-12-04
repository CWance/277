
abstract class EnemyDecorator extends Enemy{
	
	EnemyDecorator(Enemy e, String name, int addHp){
		super(e.getName()+name, e.getLevel(),e.getMaxHP()+addHp,e.getItem());
	}

	@Override
	void attack(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
