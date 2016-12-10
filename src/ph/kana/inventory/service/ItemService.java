package ph.kana.inventory.service;


import ph.kana.inventory.Item;
import ph.kana.inventory.dao.FileItemDao;
import ph.kana.inventory.dao.ItemDao;
import ph.kana.inventory.exception.DataAccessException;
import ph.kana.inventory.exception.ServiceException;

public class ItemService {

	private final ItemDao itemDao = new FileItemDao();

	public Item create(String name, int quantity) throws ServiceException {
		Item item = new Item();
		item.setName(name);
		item.setQuantity(quantity);

		try {
			itemDao.save(item);
			return item;
		} catch (DataAccessException e) {
			throw new ServiceException("Error saving item", e);
		}
	}
}
