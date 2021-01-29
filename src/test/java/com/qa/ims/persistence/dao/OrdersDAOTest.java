package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DatabaseUtilities;

public class OrdersDAOTest {
	
	private final OrdersDao DAO = new OrdersDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
        
    }

//    @Test
//    public void testCreate() {
//    	Customer customer = new Customer(1L, "Nick", "Johnson");
//       // final Orders created = new Orders(1L, "Nick", "Johnson", 1L, 0L,  "", 0D, "");
//    	long customerID = 1;
//      final Orders  created = (new Orders(new Customer(customerID, null, null), 0.0));
//        System.out.println("Created Result : " + created);
//        assertEquals(created, DAO.create(created));
//        
//    }
	
	
 
}
