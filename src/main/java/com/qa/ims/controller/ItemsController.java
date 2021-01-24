package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDao;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
