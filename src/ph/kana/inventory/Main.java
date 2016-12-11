package ph.kana.inventory;

import ph.kana.inventory.exception.ServiceException;
import ph.kana.inventory.model.Item;
import ph.kana.inventory.service.ItemService;

import java.util.List;

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
				add(args);
				break;

			case "edit":
				edit(args);
				break;

			case "list":
				list();
				break;

			case "delete":
				// TODO delete item logic
				break;

			default:
				displayHelp();
		}
	}

	private static void add(String[] args) throws ServiceException {
		String name = args[1];
		int quantity = Integer.parseInt(args[2]);

		Item item = itemService.create(name, quantity);
		String message = String.format("Item [%s] has been saved successfully! id=%d", item.getName(), item.getId());
		System.out.println(message);
	}

	private static void edit(String[] args) throws ServiceException {
		Long id = Long.parseLong(args[1]);
		String name = args[2];
		int quantity = Integer.parseInt(args[3]);

		Item item = itemService.update(id, name, quantity);
		String message = String.format("Item #%d is now updated with name=%s and quantity=%d", item.getId(), item.getName(), item.getQuantity());
		System.out.println(message);
	}

	private static void list() throws ServiceException {
		List<Item> items = itemService.fetchAll();

		System.out.printf("%3s %4s %s\n\n", "id", "Qty", "Name");

		for (Item item : items) {
			System.out.printf("%3d %4d %s\n", item.getId(), item.getQuantity(), item.getName());
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
