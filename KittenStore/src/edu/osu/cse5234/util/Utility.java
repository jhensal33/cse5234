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
	
}
