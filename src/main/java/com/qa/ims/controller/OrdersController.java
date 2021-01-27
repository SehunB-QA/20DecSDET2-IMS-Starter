package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDao;
import com.qa.ims.persistence.dao.OrdersDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.JavaUtilities;


public class OrdersController implements ICrudController<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	 private OrdersDao ordersDao;
	 private CustomerDao customerDao;
	 private JavaUtilities javaUtilities;
	 
	 private Customer customer = new Customer();
	 private Items items = new Items();
	
	 
	 

	    public OrdersController(OrdersDao ordersDao, JavaUtilities javaUtilities) {
	        super();
	        this.ordersDao = ordersDao;
	        this.javaUtilities = javaUtilities;
	    }
	    
//	    public Long Search(Long customerID) 
//	    {
//	    	
//	    	  for (Customer id : customerList)
//	    	  {
//				  if( id.getId() != null && id.getId().equals(customerID)) {
//			              // System.out.println(name);
//			         }
//			  }
//			  
//			return customerID;
//		
//		}
//	    	
	    
	    

	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDao.readAll();
        for (Orders order : orders) {
            LOGGER.info(order);
        }
        return orders;
	}

	@Override
	public Orders create() {
		
		   LOGGER.info("Please enter the id of the customer");
	        Long customerID = javaUtilities.getLong();
	       // Search(customerID);
	       // LOGGER.info("Please enter the id of the item you woud like to order.");
	       // Long itemID = javaUtilities.getLong();
	        Orders orders = ordersDao.create(new Orders(new Customer(customerID, null, null), 0.0));
	        LOGGER.info("Order created");
	        return orders;
	}

	@Override
	public Orders update() {
	
		
		//to do: ask for an order id -store in variable, 
		LOGGER.info("Please enter your order id");
        Long orderID = javaUtilities.getLong();
        
        
		//ask to add or remove item
        LOGGER.info("Would you like to add item");
        
        //store answer in variable 
        String answer = javaUtilities.getString();
        
        
        
		
        
		//ask for item id
      //  LOGGER.info("What item would you like to add by id");
        
         // store item id in variable
       // Long itemID = javaUtilities.getLong();
        
        if(answer.equalsIgnoreCase("yes") )
        {
        	LOGGER.info("Add single item or add list?");
        	
        	String response = javaUtilities.getString();
        	
                      // Add Single Item \\         	
        	if(response .equalsIgnoreCase("single") )
        		
        	{
        	 LOGGER.info("What item ID would you like to add?");
        	 Long itemID = javaUtilities.getLong();
        	 Orders orders = ordersDao.AddItem(null, orderID, itemID);
             return orders; 
        	}
        	
        	  // Add multiple items \\ 
        	else if(response.equals("list") )
        	{
        		String input = "";
        		while(!input.equals("exit"))
        		{
        			  List<Long> itemsList = new ArrayList<>();
            		  LOGGER.info("What item ID would you like to add?"); 
            		  Long itemID  = javaUtilities.getLong();
            		  LOGGER.info("Type exit to exit!");
            		   input = javaUtilities.getString();
            		 ordersDao.AddItem(null, orderID, itemID);
            		
        			
        		}
        		 
        		
        	}
        	
        	//Orders orders = ordersDao.AddItemList(, orderID); 
	        //LOGGER.info("Order created");
	        //return orders;
        }
        
		//if add -> update add in dao = return object type = order
        
		// if remove -> update remove in dao  = return object type = order
        
		//run update method using returned order 
		
		return null;
	}

	@Override
	public int delete() {
		
		
		LOGGER.info("Would you like to delete your whole order or just an single item off your order?");
		   //store answer in variable 
        String answer = javaUtilities.getString();
		
        if(answer.equals("whole"))
        {
		        //Delete whole Order \\
		
		 //Output list of items for better experience
		readAll();
		LOGGER.info("");
		//
		LOGGER.info("Please enter the id of the order you would like to delete");
        Long ordersID = javaUtilities.getLong();
        LOGGER.info("Order has been deleted");
        return ordersDao.deleteWholeOrder(ordersID);
        }
        
        else if(answer.equals("single"))
        {
        	readAll();
    		LOGGER.info("");
    		//
    		LOGGER.info("Please enter the id of the item you would like to delete");
            Long itemID = javaUtilities.getLong();
            LOGGER.info("Item has been deleted from your Order");
           return ordersDao.deleteSingleItemFromOrder(itemID);
        }
        return 0;
	}
	
	

}
