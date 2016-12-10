package ph.kana.inventory.service;


import ph.kana.inventory.Item;
import ph.kana.inventory.dao.FileItemDao;
import ph.kana.inventory.dao.ItemDao;
import ph.kana.inventory.exception.DataAccessException;

public class ItemService {

	ItemDao itemDao = new FileItemDao();

	public Item create(String name, int quantity) {
		Item item = new Item();
		item.setName(name);
		item.setQuantity(quantity);

		try {
			itemDao.save(item);
			return item;
		} catch (DataAccessException e) {
			// TODO handle by rethrow
		}
		return null; // TODO after implementing rethrow
	}
}
