/**
 * The Interface for the Force attacks
 * 
 * @author Christian Wance 012306864
 *
 */
public interface Force {
	/**
	 * The menu for force attacks
	 */
	public static final String FORCE_MENU = "1. Force Push\n2. Force CHoke\n3. Force Slam";

	/**
	 * Used to use forcePush Attack
	 */
	public int forcePush();

	/**
	 * Used to use forceChoke Attack
	 */
	public int forceChoke();

	/**
	 * Used to use forceSlam Attack
	 */
	public int forceSlam();
}
