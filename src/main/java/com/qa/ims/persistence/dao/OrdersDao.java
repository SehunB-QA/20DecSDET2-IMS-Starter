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
	  Orders orders = new Orders();
	  Customer customer  = new Customer();
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
	   
		public List<Long> AddItemList(List<Long> itemsa, Long orderID)
		{
			
			try (Connection connection = DatabaseUtilities.getInstance().getConnection();
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO order_items(fk_orders_id, fk_id_items) VALUES (?,?)");) {
				
				for(Long items : itemsa)
				{
					statement.setLong(1, orderID);
					statement.setLong(2, items);
					statement.executeUpdate();
					itemsList.add(items);
				}
				
				
				return itemsList;
				
				
			} 
		   catch (Exception e) {
	            LOGGER.debug(e);
	            LOGGER.error(e.getMessage());
	        }
			
			
			
			
				
			
			return null ;
			
			
			
		}
		
	   
	   
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
	   
	   
	   
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DatabaseUtilities.getInstance().getConnection();
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
           List<Orders> orders = new ArrayList<>();
           while (resultSet.next()) {
               orders.add(modelFromResultSet(resultSet));
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
	   
	   Long ordersID = resultSet.getLong("orders_id");
	   //orders. customerID = resultSet.getLong("fk_customers_id");
       return new Orders(ordersID,orders.getOrderItems(), customer, orders.getTotalOrderPrice());
       //push back a new "Orders" object containing the column values
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
