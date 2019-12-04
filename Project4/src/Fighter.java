import java.util.Random;

public class Fighter extends EnemyDecorator{
	Fighter(Enemy e) {
		super(e, " Fighter", 1);
	}

	@Override
	void attack(Entity e) {
		Random damage = new Random();
		int dam = damage.nextInt(getLevel() + 1 * damage.nextInt(getLevel())) + 1;
		e.takeDamage(dam);
		System.out.println(getName() + " attacks " + e.getName() + " for " + dam + " damage.");
	}

}
