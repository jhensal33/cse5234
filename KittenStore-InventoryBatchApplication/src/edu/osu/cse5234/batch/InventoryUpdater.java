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
	}

	private static Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "password");
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

	private static Map<Integer, Integer> getOrderedLineItems(Collection<Integer> newOrderIds,
                Connection conn)  throws SQLException {
		// TODO Auto-generated method stub
		// This method returns a map of two integers. The first Integer is item ID, and 
                 // the second is cumulative requested quantity across all new orders
		
		Map<Integer, Integer> orderedLineItems = new HashMap<Integer, Integer>();
		
		Iterator<Integer> iterator = newOrderIds.iterator();		 
        
		while (iterator.hasNext()) {
			int currentOrderId = iterator.next();
	        ResultSet rset = conn.createStatement().executeQuery(
	                "select ITEM_NUMBER, QUANTITY from CUSTOMER_ORDER_LINE_ITEM where CUSTOMER_ORDER_ID_FK = '" + currentOrderId + "'");
	        
	        while (rset.next()) {
	        	int currentItem = rset.getInt("ITEM_NUMBER");
	        	int cumulativeQuantity = orderedLineItems.get(currentItem);
	        	cumulativeQuantity += rset.getInt("QUANTITY");
	        	orderedLineItems.put(currentItem, cumulativeQuantity);
	        }
        }
		
		return orderedLineItems;
	}
	
	

	private static void udpateInventory(Map<Integer, Integer> orderedItems, 
                Connection conn) throws SQLException {
				 

		for (Map.Entry<Integer,Integer> item : orderedItems.entrySet())   {
			
	        conn.createStatement().executeQuery("update ITEM set AVAILABLE_QUANTITY = AVAILABLE_QUANTITY - " + item.getValue() + " where ITEM_NUMBER = '" + item.getKey() + "'");
		}

	}

	private static void udpateOrderStatus(Collection<Integer> newOrderIds, 
                Connection conn) throws SQLException {
		
		Iterator<Integer> iterator = newOrderIds.iterator();		 
        
		while (iterator.hasNext()) {
			int currentOrderId = iterator.next();
			
	        conn.createStatement().executeQuery("update CUSTOMER_ORDER set STATUS = 'Old' where ID = '" + currentOrderId + "'");
	        
		}
	}

	
}
