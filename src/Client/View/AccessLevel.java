package Client.View;

/**
 * class for access level comboBox
 */
public class AccessLevel {
	/**
	 * Id of level
	 */
	private int id;
	/**
	 * level name
	 */
	private String description;

	public AccessLevel(int id, String description)
	{
		this.id = id;
		this.description = description;
	}

	public int getId()
	{
		return id;
	}

	public String getDescription()
	{
		return description;
	}

	public String toString()
	{
		return description;
	}
}
