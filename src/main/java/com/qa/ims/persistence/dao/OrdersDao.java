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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DatabaseUtilities;

public class OrdersDao implements IDomainDao<Orders> {
	
	  public static final Logger LOGGER = LogManager.getLogger();
	 
	  List<Long> itemsList = new ArrayList<>();
    
	   @Override
		public Orders create(Orders orders) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("INSERT INTO orders(fk_customers_id) VALUES (?)");) {
	            statement.setLong(1, orders.getOrderCustomer().getId());
	            statement.executeUpdate();
	            return readLatest();
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
		}
	   
	   
		public Orders AddItem(Orders orders, Long ordersID, Long itemsID) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection
	                        .prepareStatement("INSERT INTO order_items(fk_orders_id, fk_id_items) VALUES (?,?)");) {
	            statement.setLong(1, ordersID);
	            statement.setLong(2, itemsID);
	            statement.executeUpdate();
	            return readLatest();
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
		}
	   
		/*
		 * public List<Long> AddItemList(List<Long> itemsa, Long orderID) {
		 * 
		 * try (Connection connection = DatabaseUtilities.getInstance().getConnection();
		 * PreparedStatement statement = connection
		 * .prepareStatement("INSERT INTO order_items(fk_orders_id, fk_id_items) VALUES (?,?)"
		 * );) {
		 * 
		 * for(Long items : itemsa) { statement.setLong(1, orderID);
		 * statement.setLong(2, items); statement.executeUpdate(); itemsList.add(items);
		 * }
		 * 
		 * 
		 * return itemsList;
		 * 
		 * 
		 * } catch (Exception e) { LOGGER.debug(e); LOGGER.error(e.getMessage()); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * return null ;
		 * 
		 * 
		 * 
		 * }
		 */
	   
	   
	   public Orders read(Long id) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orders_id  = ?");) {
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
	   
	   
	   public Orders calculateTotalOrder(Long orderID) {
		   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
	                PreparedStatement statement = connection.prepareStatement("	SELECT orders.orders_id, customers.id, customers.first_name, customers.surname, items.id_items, items.item_name, items.item_price,\r\n"
	                		+ "             sum(case when orders.orders_id = ? then items.item_price else 0 end) as total_order_price\r\n"
	                		+ "          FROM orders\r\n"
	                		+ "          INNER JOIN customers ON Orders.fk_customers_id = customers.id\r\n"
	                		+ "          INNER JOIN order_items ON Orders.orders_id  = order_items.fk_orders_id\r\n"
	                		+ "          INNER JOIN items On order_items.fk_id_items = items.id_items\r\n"
	                		+ "		   where orders.orders_id = ?;\r\n"
	                		+ "	");) {
	            statement.setLong(1, orderID);
	            statement.setLong(2, orderID);
	            ResultSet resultSet = statement.executeQuery();
	            resultSet.next();
	            return totalPriceResultSet(resultSet);
	        } catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
	        return null;
	    }
	   
	   
	   
	   
	   
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT orders.orders_id, customers.id, customers.first_name, customers.surname, items.id_items, items.item_name, items.item_price\r\n"
               		+ "FROM orders\r\n"
               		+ "INNER JOIN customers ON Orders.fk_customers_id = customers.id\r\n"
               		+ "INNER JOIN order_items ON Orders.orders_id = order_items.fk_orders_id\r\n"
               		+ "INNER JOIN items On order_items.fk_id_items = items.id_items;  ");) {
           List<Orders> orders = new ArrayList<>();
           while (resultSet.next()) {
               orders.add(readModelFromResultSet(resultSet));
           }
           return orders;
       } catch (SQLException e) {
           LOGGER.debug(e);
           LOGGER.error(e.getMessage());
       }
       return new ArrayList<>();
	}

   public Orders readLatest() {
   	   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
                  Statement statement = connection.createStatement();
                  ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orders_id DESC LIMIT 1");) {
           
   		   resultSet.next();
              return modelFromResultSet(resultSet);
          } catch (Exception e) {
              LOGGER.debug(e);
              LOGGER.error(e.getMessage());
          }
          return null;
   }
	
   
   @Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
	   
	   Long customerID = resultSet.getLong("fk_customers_id");
	   //orders. customerID = resultSet.getLong("fk_customers_id");
	     
       return new Orders(customerID);
       //push back a new "Orders" object containing the column values
	}
   
  public Orders totalPriceResultSet(ResultSet resultSet) throws SQLException {
	   
	  String customerFirstName = resultSet.getString("first_name");
	   String customerSurname  = resultSet.getString("surname");
	   Long customerID = resultSet.getLong("id");
 	   Long ordersID = resultSet.getLong("orders_id");
 	   Long itemID = resultSet.getLong("id_items");
	   String itemName = resultSet.getString("item_name");
	   Double totalitemPrice =  resultSet.getDouble("total_order_price");
 	String dummyString = "";
 	   //orders. customerID = resultSet.getLong("fk_customers_id");
 	     
        return new Orders(ordersID, customerFirstName, customerSurname , customerID, itemID,  itemName, totalitemPrice, dummyString);
        //push back a new "Orders" object containing the column values
	}
   
   
   
  
  	public Orders readModelFromResultSet(ResultSet resultSet) throws SQLException {
  	   
	   String customerFirstName = resultSet.getString("first_name");
	   String customerSurname  = resultSet.getString("surname");
	   Long customerID = resultSet.getLong("id");
  	   Long ordersID = resultSet.getLong("orders_id");
  	   Long itemID = resultSet.getLong("id_items");
	   String itemName = resultSet.getString("item_name");
	   Double itemPrice =  resultSet.getDouble("item_price");
  	
  	   //orders. customerID = resultSet.getLong("fk_customers_id");
  	     
         return new Orders(ordersID, customerFirstName, customerSurname , customerID, itemID,  itemName, itemPrice);
         //push back a new "Orders" object containing the column values
  	}

   
 
   public int deleteWholeOrder(long orderID) {
	   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			   
			   PreparedStatement statement = connection
                       .prepareStatement("delete from order_items where fk_orders_id = (?) ");) {
           statement.setLong(1, orderID);
         statement.executeUpdate();
         
         PreparedStatement statement2 = connection
                 .prepareStatement("delete from orders where orders_id = (?) ");
     statement2.setLong(1, orderID);
         return statement2.executeUpdate();
       } catch (Exception e) {
           LOGGER.debug(e);
           LOGGER.error(e.getMessage()); 
       }
       return 0;
   }
   
   public int deleteSingleItemFromOrder(long itemID) {
	   try (Connection connection = DatabaseUtilities.getInstance().getConnection();
			   PreparedStatement statement = connection
                       .prepareStatement("delete from order_items where fk_id_items = (?) ");) {
           statement.setLong(1, itemID);
        return  statement.executeUpdate();
         
       } catch (Exception e) {
           LOGGER.debug(e);
           LOGGER.error(e.getMessage());
       }
       return 0;
   }
   
   
   
   

@Override
public Orders update(Orders orders) {
return null;
}

@Override
public int delete(long id) {
	// TODO Auto-generated method stub
	return 0;
}

}
