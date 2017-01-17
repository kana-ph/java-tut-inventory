package ph.kana.inventory.dao;


import ph.kana.inventory.exception.DataAccessException;
import ph.kana.inventory.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresItemDao implements ItemDao {

	@Override
	public Item findById(Long id) throws DataAccessException {
		try (Connection connection = createDbConnection()) {
			String sql = "SELECT id, name, quantity FROM items WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getLong("id"));
				item.setName(resultSet.getString("name"));
				item.setQuantity(resultSet.getInt("quantity"));
				return item;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void save(Item item) throws DataAccessException {
		try (Connection connection = createDbConnection()) {
			String sequenceSql = "SELECT nextval('item_sequence') AS item_id";
			Statement statement = connection.createStatement();
			ResultSet sequenceResultSet = statement.executeQuery(sequenceSql);

			if (sequenceResultSet.next()) {
				Long id = sequenceResultSet.getLong("item_id");
				item.setId(id);
			}

			if (item.getId() != null) {
				String insertSql = "INSERT INTO items(id, name, quantity) VALUES (?, ?, ?)";
				PreparedStatement insertStatement = connection.prepareStatement(insertSql);
				insertStatement.setLong(1, item.getId());
				insertStatement.setString(2, item.getName());
				insertStatement.setInt(3, item.getQuantity());

				insertStatement.executeUpdate();
			} else {
				throw new DataAccessException(null);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void update(Item item) throws DataAccessException {
		try (Connection connection = createDbConnection()) {
			String sql = "UPDATE items SET name=?, quantity=? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, item.getName());
			statement.setInt(2, item.getQuantity());
			statement.setLong(3, item.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public List<Item> fetchAll() throws DataAccessException {
		try (Connection connection = createDbConnection()) {
			String sql = "SELECT id, name, quantity FROM items ORDER BY id";
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);
			List<Item> items = new ArrayList<>();

			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getLong("id"));
				item.setName(resultSet.getString("name"));
				item.setQuantity(resultSet.getInt("quantity"));

				items.add(item);
			}
			return items;
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	@Override
	public void delete(Item item) throws DataAccessException {
		try (Connection connection = createDbConnection()) {
			String sql = "DELETE FROM items WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, item.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	private Connection createDbConnection() throws SQLException {
		String connectionUrl = "jdbc:postgresql://localhost/inventory_tutorial";
		String username = "postgres";
		String password = "abcde12345";

		return DriverManager.getConnection(connectionUrl, username, password);
	}
}
