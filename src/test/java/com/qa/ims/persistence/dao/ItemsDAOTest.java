package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DatabaseUtilities;

public class ItemsDAOTest {

	private final ItemsDao DAO = new ItemsDao();

    @Before
    public void setup() {
        DatabaseUtilities.connect();
        DatabaseUtilities.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
    }

    @Test
    public void testCreate() {
        final Items created = new Items(2L, "Final Fantasy X Remake", 70.99);
        assertEquals(created, DAO.create(created));
    }

    @Test
    public void testReadAll() {
        List<Items> expected = new ArrayList<>();
        expected.add(new Items(1L, "Zelda", 70.99));
        assertEquals(expected, DAO.readAll());
    }

    @Test
    public void testReadLatest() {
        assertEquals(new Items(1L, "Zelda", 70.99), DAO.readLatest());
    }

    @Test
    public void testRead() {
        final long itemsID = 1L;
        assertEquals(new Items(itemsID, "Zelda", 70.99), DAO.read(itemsID));
    }

    @Test
    public void testUpdate() {
        final Items updated = new Items(1L, "Zelda", 40.00);
        assertEquals(updated, DAO.update(updated));

    }

    @Test
    public void testDelete() {
        assertEquals(1, DAO.delete(1));
    }

}
