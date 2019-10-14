package edu.osu.cse5234.business.view;

import java.util.ArrayList;

import edu.osu.cse5234.models.Item;

public class Inventory implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4880922429803008945L;
	
	private ArrayList<Item> items;

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
}
