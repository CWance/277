/**
 * @author Christian Wance 012306864
 * 
 * Makes a Task class that is comparable
 */
public class Task implements Comparable<Task>{
	private String name;
	private Date date;
	
	/**
	 * Constructor
	 * @param n String name
	 */
	public Task(String n) {
		name = n;
		date = new Date();
	}
	
	/**
	 * Constructor
	 * @param n String name
	 * @param d	String Date
	 */
	public Task(String n, String d) {
		name = n;
		date = new Date(d);
	}
	
	/**
	 * Constructor
	 * @param n String name
	 * @param year String year
	 * @param month String month
	 * @param day String day
	 */
	public Task(String n, String year, String month, String day) {
		name = n;
		date = new Date(year, month,day);
	}
	
	/**
	 * COnstructor
	 * @param n String name
	 * @param year String year
	 * @param month String month
	 * @param day String day
	 * @param hour String hour
	 * @param minute String minute
	 */
	public Task(String n, String year, String month, String day, String hour, String minute) {
		name = n;
		date = new Date(year, month,day, hour, minute);
	}
	
	/**
	 * ToString function
	 * @return string Task
	 */
	public String toString() {
		return name + "," + date;
	}

	/**
	 * Gets name
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets date
	 * @return String date
	 */
	public Date getDate() {
		return date;
	}

	

	/**
	 * Overrides compareTo function
	 * @return int
	 */
	@Override
	public int compareTo(Task o) {
	Task t =  o;
	if(date.compareTo(t.getDate()) == 0){
		return name.compareTo(t.getName());
	}
	return date.compareTo(t.getDate());
	}

}
