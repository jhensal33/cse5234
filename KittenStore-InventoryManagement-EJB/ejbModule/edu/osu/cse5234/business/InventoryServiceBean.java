package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.models.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
	@PersistenceContext
    EntityManager entityManager;
	
	private final String MY_QUERY = "Select i from Item i";

	@Override
	public Inventory getAvailableInventory() {
		
		Inventory inventory = new Inventory();
		inventory.setItems(entityManager.createQuery(MY_QUERY, Item.class).getResultList());
		
		return inventory;
	}

	@Override
	public boolean validateQuantity(List<Item> items) {
		
		/*
		List<Item> inv = getAvailableInventory().getItems();
		
		int itemId = 0;
		for(Item item : items) {
	
			itemId = item.getId();
			
			for(int i=0; i<inv.size(); i++) {				
				if(inv.get(i).getId() == itemId) {
					if(inv.get(i).getAvailableQuantity() < item.getAvailableQuantity()) {
						return false;
					}
					else {
						break;
					}
				}
			}			
		}
		*/
		
		return true;
	}

	@Override
	public boolean updateInventory(List<Item> items) {
		// TODO Auto-generated method stub
		return true;
	}
	
    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
