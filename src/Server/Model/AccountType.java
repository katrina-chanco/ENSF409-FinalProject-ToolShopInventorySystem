package Server.Model;

/**
 * Account type class
 */
public class AccountType {

	/**
	 * account type id
	 */
	private int typeId;
	/**
	 * account type name
	 */
	private String typeName;

	/**
	 * constructor
	 * @param typeId
	 * @param typeName
	 */
	public AccountType(int typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeName() {
		return typeName;
	}
}
