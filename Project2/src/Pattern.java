/**
 * Holds the Patterns that will be used to guess
 * 
 * @author Christian Wance 012306864
 *
 */
public class Pattern {
	/**
	 * The String pattern guess
	 */
	private String pattern;

	/**
	 * Constructor for Pattern
	 * 
	 * @param p String of pattern
	 */
	public Pattern(String p) {
		pattern = p;
	}

	/**
	 * Gets the String representation of the Pattern
	 * 
	 * @return String pattern
	 */
	public String getPattern() {
		return pattern;
	}

	@Override
	/**
	 * Overrides hashCode and Generates the Patterns hashCode
	 * 
	 * @return Integer result
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		return result;
	}

	@Override
	/**
	 * Overrides equals and Checks if Patterns are equal
	 * 
	 * @return Boolean
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pattern other = (Pattern) obj;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		return true;
	}

}
