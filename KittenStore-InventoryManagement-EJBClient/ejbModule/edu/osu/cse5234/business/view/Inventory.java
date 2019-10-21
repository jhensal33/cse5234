package edu.osu.cse5234.business.view;

import java.util.ArrayList;
import java.util.List;

import edu.osu.cse5234.models.Item;

public class Inventory implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4880922429803008945L;
	
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
