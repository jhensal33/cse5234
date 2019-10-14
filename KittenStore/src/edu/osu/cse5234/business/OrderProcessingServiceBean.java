package edu.osu.cse5234.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.models.Order;
import edu.osu.cse5234.util.ServiceLocator;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {

    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    public String processOrder(Order order) {
    	Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
    	if(ServiceLocator.getInventoryService().validateQuantity(inventory.getItems())) {
    		ServiceLocator.getInventoryService().updateInventory(inventory.getItems());
    	}
    	return "XXX-ORDER-NUMBER-XXX";
    } 
    
    public boolean validateItemAvailability(Order order) {
    	Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
    	return ServiceLocator.getInventoryService().validateQuantity(inventory.getItems());
    }

}
