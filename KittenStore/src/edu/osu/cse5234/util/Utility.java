package edu.osu.cse5234.util;


import java.util.ArrayList;
import java.util.List;

import edu.osu.cse5234.models.Item;
import edu.osu.cse5234.models.LineItem;

public class Utility {
	
	public Utility() {
		
	}
	
	public LineItem itemToLineItem(Item item) {
		LineItem line = new LineItem();
		
		line.setId(item.getId());
		line.setItemName(item.getName());
		line.setItemNumber(item.getItemNumber());
		line.setPrice(item.getUnitPrice());
		line.setQuantity(item.getAvailableQuantity());
		
		return line;
	}
	
	public List<LineItem> itemListToLineItemList(List<Item> itemList) {
		List<LineItem> lineList = new ArrayList<>();
		
		for(Item it : itemList) {
			lineList.add(itemToLineItem(it));
		}
		
		return lineList;
	}
	
	static public Item lineItemToItem(LineItem line) {
		Item item = new Item();
		
		item.setId(line.getId());
		item.setName(line.getItemName());
		item.setItemNumber(line.getItemNumber());
		item.setPrice("" + line.getPrice());
		item.setAvailableQuantity(line.getQuantity());
		
		return item;
	}
	
	
	static public List<Item> lineItemListToItemList(List<LineItem> lineItemList) {
		List<Item> itemList = new ArrayList<>();
		
		for(LineItem li : lineItemList) {
			itemList.add(lineItemToItem(li));
		}
		
		return itemList;
	}
	
}
