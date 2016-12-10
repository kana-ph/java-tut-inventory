package ph.kana.inventory;

import ph.kana.inventory.exception.ServiceException;
import ph.kana.inventory.service.ItemService;

public class Main {

	private static final ItemService itemService = new ItemService();

	public static void main(String[] args) {
		if (args.length < 1) {
			displayHelp();
		} else {
			try {
				executeCommand(args);
			} catch (ServiceException e) {
				String errorMessage = String.format("Error: %s", e.getMessage());
				System.err.println(errorMessage);

				System.exit(1);
			}
		}
	}

	private static void executeCommand(String[] args) throws ServiceException {
		String command = args[0].toLowerCase();
		switch (command) {
			case "add":
				String name = args[1];
				int quantity = Integer.parseInt(args[2]);

				Item item = itemService.create(name, quantity);
				String message = String.format("Item [%s] has been saved successfully!", item.getName());
				System.out.println(message);

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
