import java.util.Random;

/**
 * A ForceEnemy is an Entity that attacks the player, has force powers
 * 
 * @author Christian Wance 012306864
 *
 */
class ForceUser extends EnemyDecorator implements Force {

	ForceUser(Enemy e) {
		super(e, " Force User", 2);}

	/**
	 * Override the attack function found in Entity
	 * 
	 * @param e Entity The Entity being attacked
	 */
	@Override
	void attack(Entity e) {
		Random att = new Random();
		switch (att.nextInt(3) + 1) {
		case 1: {
			int dam = forcePush();
			e.takeDamage(dam * e.getLevel());
			System.out.println(getName() + " hits " + e.getName() + "  with a Force Push for " + dam + " damage.");
			break;
		}
		case 2: {
			int dam = forceChoke();
			e.takeDamage(dam * e.getLevel());
			System.out.println(getName() + " hits " + e.getName() + "  with a Force Choke for " + dam + " damage.");
			break;
		}
		case 3: {
			int dam = forceSlam();
			e.takeDamage(dam * e.getLevel());
			System.out.println(getName() + " hits " + e.getName() + "  with a Force Slam for " + dam + " damage.");
			break;
		}
		}
	}

	/**
	 * Used to use forcePush Attack
	 */
	@Override
	public int forcePush() {
		Random att = new Random();
		return att.nextInt(5) + 1;
	}

	/**
	 * Used to use forceChoke Attack
	 */
	@Override
	public int forceChoke() {
		Random att = new Random();
		return att.nextInt(10) + 1;
	}

	/**
	 * Used to use forceSlam Attack
	 */
	@Override
	public int forceSlam() {
		Random att = new Random();
		return att.nextInt(15) + 1;
	}
}
