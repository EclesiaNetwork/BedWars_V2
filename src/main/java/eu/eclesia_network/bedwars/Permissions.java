package eu.eclesia_network.bedwars;

public enum Permissions {

	COMMAND_TEST_HELP("command.help"),
	COMMAND_TEST_DEBUG("command.debug"),
	COMMAND_TEST_RELOAD("command.reload"),
	COMMAND_OPEN_INVENTORY("command.inventory");

	private final String perm;

	Permissions(String perm) {
		this.perm = perm;
	}

	public final String getPermission() {
		return "test." + this.perm;
	}

}
