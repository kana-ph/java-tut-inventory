package ph.kana.inventory.dao;


import ph.kana.inventory.Item;
import ph.kana.inventory.exception.DataAccessException;

import java.util.List;

public interface ItemDao {

	Item findById(Long id) throws DataAccessException;

	void save(Item item) throws DataAccessException;

	void update(Item item) throws DataAccessException;

	List<Item> fetchAll() throws DataAccessException;
}
