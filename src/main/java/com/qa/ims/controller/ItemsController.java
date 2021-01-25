package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.JavaUtilities;

public class ItemsController  implements ICrudController<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();

    private ItemsDao itemsDao;
    private JavaUtilities javaUtilities;

    public ItemsController(ItemsDao itemsDao, JavaUtilities javaUtilities) {
        super();
        this.itemsDao = itemsDao;
        this.javaUtilities = javaUtilities;
    }

	@Override
	public List<Items> readAll() {
		 List<Items> items = itemsDao.readAll();
	        for (Items item : items) {
	            LOGGER.info(item);
	        }
	        return items;
	}

	@Override
	public Items create() {
		 LOGGER.info("Please enter the item's name");
	        String itemName = javaUtilities.getString();
	        LOGGER.info("Please enter the item's value");
	        Double itemValue = javaUtilities.getDouble();
	        Items items = itemsDao.create(new Items(itemName, itemValue));
	        LOGGER.info("Item created");
	        return items;
	}

	@Override
	public Items update() {
		//Output list of items for better experience
		readAll();
		LOGGER.info("");
		//
		
		LOGGER.info("Please enter the id of the item you would like to update");
        Long itemsID = javaUtilities.getLong();
        LOGGER.info("Please enter a new item name");
        String itemName = javaUtilities.getString();
        LOGGER.info("Please enter a new item price");
        Double itemPrice = javaUtilities.getDouble();
        Items items = itemsDao.update(new Items(itemsID, itemName, itemPrice));
        LOGGER.info("Item has been updated");
        return items;
    }
	

	@Override
	public int delete() {
	   //Output list of items for better experience
		readAll();
		LOGGER.info("");
		//
		LOGGER.info("Please enter the id of the item you would like to delete");
        Long itemsID = javaUtilities.getLong();
        LOGGER.info("Item has been deleted");
        return itemsDao.delete(itemsID);
	}

}
