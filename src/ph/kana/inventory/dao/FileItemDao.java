package ph.kana.inventory.dao;

import ph.kana.inventory.exception.DataAccessException;
import ph.kana.inventory.model.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileItemDao implements ItemDao {

	private final List<Item> itemList = new ArrayList<>();

	private final String ITEM_FILE = "items.txt";

	public FileItemDao() {
		try {
			loadItemListFromFile();
		} catch (DataAccessException e) { }
	}

	@Override
	public Item findById(Long id) throws DataAccessException {
		for (Item item : itemList) {
			long itemId = item.getId();
			if (itemId == id) {
				return item;
			}
		}
		return null;
	}

	@Override
	public void save(Item item) throws DataAccessException {
		Long id = determineItemId();
		item.setId(id);
		itemList.add(item);
		saveItemListToFile();
	}

	@Override
	public void update(Item item) throws DataAccessException {
		saveItemListToFile();
	}

	@Override
	public List<Item> fetchAll() throws DataAccessException {
		return new ArrayList<>(itemList);
	}

	@Override
	public void delete(Item itemToDelete) throws DataAccessException {
		long deleteId = itemToDelete.getId();
		for (Item item : itemList) {
			long itemId = item.getId();

			if (itemId == deleteId) {
				itemList.remove(item);
				break;
			}
		}
		saveItemListToFile();
	}

	private void saveItemListToFile() throws DataAccessException {
		File file = new File(ITEM_FILE);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (Item item : itemList) {
				String itemCsv = formatItemAsCsv(item);
				writer.write(itemCsv);
				writer.write('\n');
			}
			writer.flush();
		} catch (IOException e) {
			throw new DataAccessException(e);
		}
	}

	private void loadItemListFromFile() throws DataAccessException {
		File file = new File(ITEM_FILE);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String read = reader.readLine();
			while (read != null) {
				Item item = parseCsvToItem(read);
				itemList.add(item);

				read = reader.readLine();
			}
		} catch (IOException e) {
			throw new DataAccessException(e);
		}
	}

	private String formatItemAsCsv(Item item) {
		return String.format("%d,%d,%s", item.getId(), item.getQuantity(), item.getName());
	}

	private Item parseCsvToItem(String csv) {
		String[] columns = csv.split(",");
		long id = Long.parseLong(columns[0]);
		int quantity = Integer.parseInt(columns[1]);
		String name = columns[2];

		Item item = new Item();
		item.setId(id);
		item.setQuantity(quantity);
		item.setName(name);

		return item;
	}

	private long determineItemId() {
		long highestId = 0;
		for (Item item : itemList) {
			long itemId = item.getId();
			if (itemId > highestId) {
				highestId = itemId;
			}
		}

		return highestId + 1;
	}
}
