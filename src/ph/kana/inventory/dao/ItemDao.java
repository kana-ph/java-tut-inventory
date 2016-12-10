package ph.kana.inventory.dao;


import ph.kana.inventory.Item;
import ph.kana.inventory.exception.DataAccessException;

public interface ItemDao {

	void save(Item item) throws DataAccessException;
}
