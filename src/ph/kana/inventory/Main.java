package ph.kana.inventory;

public class Main {

	public static void main(String[] args) {
		if (args.length < 1) {
			displayHelp();
		} else {
			executeCommand(args[0]);
		}
	}

	private static void executeCommand(String command) {
		switch (command.toLowerCase()) {
			case "add":
				// TODO add item logic
				break;

			case "edit":
				// TODO edit item logic
				break;

			case "list":
				//TODO list item logic
				break;

			case "delete":
				// TODO delete item logic
				break;

			default:
				displayHelp();
		}
	}

	private static void displayHelp() {
		System.out.println("* list");
		System.out.println("\tLists all saved items.");

		System.out.println("* add <name> <quantity>");
		System.out.println("\tAdds a new item.");

		System.out.println("* edit <id> <name> <quantity>");
		System.out.println("\tEdits an item entry given an id.");

		System.out.println("* delete <id>");
		System.out.println("\tDeletes an item given its id.");

	}
}
