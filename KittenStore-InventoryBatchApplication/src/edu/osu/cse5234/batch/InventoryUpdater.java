package edu.osu.cse5234.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InventoryUpdater {
	
	public static void main(String[] args){

		System.out.println("Starting Inventory Update ...");
		try {
			Connection conn = createConnection();
			Collection<Integer> newOrderIds = getNewOrders(conn);
			Map<Integer, Integer> orderedItems = getOrderedLineItems(newOrderIds, conn);
			udpateInventory(orderedItems, conn);
			udpateOrderStatus(newOrderIds, conn);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Inventory Update Complete");
	}

	private static Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:C:/workspaceCSE5234/h2db/FelineBeelineDB;AUTO_SERVER=TRUE", "sa", "password");
		return conn;
	}

	private static Collection<Integer> getNewOrders(Connection conn) throws SQLException {
		Collection<Integer> orderIds = new ArrayList<Integer>();
		ResultSet rset = conn.createStatement().executeQuery(
                     "select ID from CUSTOMER_ORDER where STATUS = 'New'");
		while (rset.next()) {
			orderIds.add(new Integer(rset.getInt("ID")));
		}
		return orderIds;
	}

	/**
	 * @return This method returns a map of two integers. The first Integer is item ID, and the second is cumulative requested quantity across all new orders
	 * @throws SQLException
	 */
	private static Map<Integer, Integer> getOrderedLineItems(Collection<Integer> newOrderIds,
                Connection conn)  throws SQLException {
		
		Map<Integer, Integer> orderedLineItems = new HashMap<Integer, Integer>();
		Iterator<Integer> iterator = newOrderIds.iterator();	
        int currentItem;
        int orderedQuantity;
        
		while (iterator.hasNext()) {
			int currentOrderId = iterator.next();
	        ResultSet rset = conn.createStatement().executeQuery(
	                "select ITEM_NUMBER, QUANTITY from CUSTOMER_ORDER_LINE_ITEM where CUSTOMER_ORDER_ID_FK = '" + currentOrderId + "'");
	        
	        while (rset.next()) {
	        	currentItem = rset.getInt("ITEM_NUMBER");
	        	if(orderedLineItems.containsKey(currentItem)) {
	        		orderedQuantity = orderedLineItems.get(currentItem);
	        		orderedLineItems.put(currentItem, orderedQuantity + rset.getInt("QUANTITY"));
	        	}
	        	else {
		        	orderedLineItems.put(currentItem, rset.getInt("QUANTITY"));	        		
	        	}
	        }
        }
		
		return orderedLineItems;
	}
	
	
	private static void udpateInventory(Map<Integer, Integer> orderedItems, 
                Connection conn) throws SQLException {
				 
		for (Map.Entry<Integer,Integer> item : orderedItems.entrySet())   {
	        conn.createStatement().executeUpdate("update ITEM set AVAILABLE_QUANTITY = AVAILABLE_QUANTITY - " + item.getValue() + " where ITEM_NUMBER = '" + item.getKey() + "'");
		}
	}

	private static void udpateOrderStatus(Collection<Integer> newOrderIds, 
                Connection conn) throws SQLException {
		
		Iterator<Integer> iterator = newOrderIds.iterator();		 
        
		int currentOrderId;
		while (iterator.hasNext()) {
			currentOrderId = iterator.next();
	        conn.createStatement().executeUpdate("update CUSTOMER_ORDER set STATUS = 'Processed' where ID = '" + currentOrderId + "'");	      
		}
	}
	
}
