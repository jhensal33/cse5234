package edu.osu.cse5234.models;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class Order
 */
@Stateless
@LocalBean
public class Order {
	
	private List<Item> items;

    /**
     * Default constructor. 
     */
    public Order() {
        this.items = new ArrayList<Item>();
    }

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
