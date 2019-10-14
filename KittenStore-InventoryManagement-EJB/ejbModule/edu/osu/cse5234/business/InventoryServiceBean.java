package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.models.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
@Remote(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Inventory getAvailableInventory() {
		
		Inventory inventory = new Inventory();
		
		Item cat1 = new Item();
		Item cat2 = new Item();
		Item cat3 = new Item();
		Item cat4 = new Item();
		Item cat5 = new Item();
		
		cat1.setName("ragdoll");
		cat2.setName("munchkin");
		cat3.setName("scottish fold");
		cat4.setName("toyger");
		cat5.setName("bengal");
		
		cat1.setPrice("12");
		cat2.setPrice("200");
		cat3.setPrice("35");
		cat4.setPrice("78");
		cat5.setPrice("5000");
		
		cat1.setQuantity("11");
		cat2.setQuantity("1");
		cat3.setQuantity("111");
		cat4.setQuantity("11");
		cat5.setQuantity("1");
		
		ArrayList<Item> lst = new ArrayList<>();
		lst.add(cat1);
		lst.add(cat2);
		lst.add(cat3);
		lst.add(cat4);
		lst.add(cat5);
		
		inventory.setItems(lst);
		
		return inventory;
	}

	@Override
	public boolean validateQuantity(List<Item> items) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateInventory(List<Item> items) {
		// TODO Auto-generated method stub
		return true;
	}

}
