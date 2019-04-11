package Server.Model;

/**
 * Account type class
 */
public class AccountType {

	private int typeId;
	private String typeName;

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
