package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DatabaseUtilities;

public class ItemsDao implements IDomainDao<Items>  {

	   public static final Logger LOGGER = LogManager.getLogger();


	   @Override
		public Items create(Items items) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("INSERT INTO items(item_name, item_price) VALUES (?, ?)");) {
	            statement.setString(1,items.getItemsName());
	            statement.setDouble(2, items.getItemsPrice());
	            statement.executeUpdate();
	            return readLatest();
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
		}
	   
	   public Items read(Long id) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE id_items  = ?");) {
	            statement.setLong(1, id);
	            ResultSet resultSet = statement.executeQuery();
	            resultSet.next();
	            return modelFromResultSet(resultSet);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }
	   
	   
	   
	@Override
	public List<Items> readAll() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
            List<Items> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(modelFromResultSet(resultSet));
            } 
            return items;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
	}

    public Items readLatest() {
    	   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                   Statement statement = connection.createStatement();
                   ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id_items DESC LIMIT 1");) {
              //Future reference : May actually be id
    		   resultSet.next();
               return modelFromResultSet(resultSet);
           } catch (Exception e) {
               LOGGER.debug(e);
               LOGGER.error(e.getMessage());
           }
           return null;
    }
	

	@Override
	public Items update(Items items) {
		 try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("UPDATE items SET item_name = ?, item_price = ? WHERE id_items = ?");) {
	            statement.setString(1, items.getItemsName());
	            statement.setDouble(2, items.getItemsPrice());
	            statement.setLong(3, items.getItemsId());
	            statement.executeUpdate();
	            return read(items.getItemsId());
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	}

	@Override
	public int delete(long itemsID) {
		  try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                Statement statement = connection.createStatement();) {
	            return statement.executeUpdate("delete from items where id_items = " + itemsID);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return 0;
	}

	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemsID = resultSet.getLong("id_items");
        String itemsName = resultSet.getString("item_name");
        Double itemsValue = resultSet.getDouble("item_price");
        return new Items(itemsID, itemsName, itemsValue);
        //push back a new "Items" object containing the column values
	}

}
