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
	
	
	private int id;
	private String customerName;
	private String emailAddress;
	private List<LineItem> lineItems;
	//private List<Item> items; Item represents inventory now

    /**
     * Default constructor. 
     */
    public Order() {
        this.lineItems = new ArrayList<LineItem>();
    }

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
