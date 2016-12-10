package ph.kana.inventory.dao;

import ph.kana.inventory.Item;
import ph.kana.inventory.exception.DataAccessException;

import java.io.*;

public class FileItemDao implements ItemDao {

	@Override
	public void save(Item item) throws DataAccessException {
		try {
			File file = new File("items.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(itemCsv(item));

			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new DataAccessException(e);
		}
	}

	private String itemCsv(Item item) {
		return String.format("%d,%s", item.getQuantity(), item.getName());
	}
}
