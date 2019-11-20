/**
 * @author Christian Wance 012306864
 * 
 *         This is my custom date class, as I found the ones given to not be
 *         good for me
 */
public class Date implements Comparable{
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	/**
	 * Empty Constructor gives a big date
	 */
	public Date() {
		year = "9999";
		month = "12";
		day = "31";
		hour = "23";
		minute = "59";
	}
	
	/**
	 * Constuctor without hour or minute
	 * @param y String year
	 * @param m String month
	 * @param d String day
	 */
	public Date(String y, String m, String d) {
		year = y;
		month = m;
		day = d;
		hour = "00";
		minute = "00";
		
		
	}
	
	/**
	 * Constructor
	 * @param y String year
	 * @param m String month
	 * @param d String day
	 * @param h	String hour
	 * @param mn String minute
	 */
	public Date(String y, String m, String d, String h, String mn) {
		year = y;
		month = m;
		day = d;
		hour = h;
		minute = mn;
	}
	
	/**
	 * Constructor used for file 
	 * @param d String Date
	 */
	public Date(String d) {
		
		String[] date = d.split("/");
		month = date[0];
		day = date[1];
		String[] YHM = date[2].split(" ");
		year = YHM[0];
		String[] HM = YHM[1].split(":");
		hour = HM[0];
		minute = HM[1];
		
		
	}

	/**
	 * Gets hour
	 * @return hour
	 */
	public String getHour() {
		return hour;
	}
	/**
	 * Gets minute
	 * @return minute
	 */
	public String getMinute() {
		return minute;
	}

	/**
	 * Gets year
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Gets month
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Gets day
	 * @return day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * toString Function
	 * 
	 * @return Date String
	 */
	public String toString() {
		return month +"/" + day +  "/"  + year + " " + hour + ":" + minute;
	}
	
	/**
	 * Overrides compareTO function
	 * @return int
	 */
	@Override
	public int compareTo(Object o) {
		Date d = (Date) o;

		if(this.year.equals(d.getYear())) {
			if(this.month.contentEquals(d.getMonth())) {
				if(this.day.equals(d.getDay())) {
					if(this.hour.equals(d.getHour())) {
						if(this.minute.equals(d.getMinute())) {
							return 0;
						}

						return this.minute.compareTo(d.getMinute());
					}
					return this.hour.compareTo(d.getHour());
				}

				return this.day.compareTo(d.getDay());
			}
			
			return this.month.compareTo(d.getMonth());
		}
		
		return this.year.compareTo(d.getYear());
	}
	
	
}
