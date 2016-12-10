package ph.kana.inventory.service;


import ph.kana.inventory.Item;
import ph.kana.inventory.dao.FileItemDao;
import ph.kana.inventory.dao.ItemDao;
import ph.kana.inventory.exception.DataAccessException;
import ph.kana.inventory.exception.ServiceException;

import java.util.List;

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

	public Item update(Long id, String newName, int newQuantity) throws ServiceException {
		try {
			Item item = itemDao.findById(id);

			if (item != null) {
				item.setName(newName);
				item.setQuantity(newQuantity);

				itemDao.update(item);
				return item;
			} else {
				throw new ServiceException("Item not found for given id!");
			}
		} catch (DataAccessException e) {
			throw new ServiceException("Error updating item", e);
		}
	}

	public List<Item> fetchAll() throws ServiceException {
		try {
			return itemDao.fetchAll();
		} catch (DataAccessException e) {
			throw new ServiceException("Error fetching item data", e);
		}
	}
}
