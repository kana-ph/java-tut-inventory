package ph.kana.inventory.dao;

import ph.kana.inventory.exception.DataAccessException;
import ph.kana.inventory.model.Item;

import java.util.List;

public interface ItemDao {

	Item findById(Long id) throws DataAccessException;

	void save(Item item) throws DataAccessException;

	void update(Item item) throws DataAccessException;

	List<Item> fetchAll() throws DataAccessException;

	void delete(Item item) throws DataAccessException;
}
