package edu.osu.cse5234.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

import com.chase.payment.*;
import com.ups.shipping.client.ShippingInitiationClient;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.models.Order;
import edu.osu.cse5234.models.PaymentInfo;
import edu.osu.cse5234.util.ServiceLocator;
import edu.osu.cse5234.util.Utility;
import javax.jws.WebService;

/**
 * Session Bean implementation class OrderProcessingServiceBean
 */
@Stateless
@LocalBean
public class OrderProcessingServiceBean {
	
	private static String shippingResourceURI = "http://localhost:9080/UPS/jaxrs";
	
    /**
     * Default constructor. 
     */
    public OrderProcessingServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
    Utility util = new Utility();
    
    @PersistenceContext
    EntityManager entityManager;
    
    @WebServiceRef(wsdlLocation = 
 	       "http://localhost:9080/ChaseBankApplication/PaymentProcessorService?wsdl")
 	    private PaymentProcessorService service;
    
    public String processOrder(Order order) {
    	Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
    	
    	if(ServiceLocator.getInventoryService().validateQuantity(util.lineItemListToItemList(order.getLineItems()))) {
    		System.out.println("Order being processed");
    		entityManager.persist(order);
    		entityManager.flush();
    		ServiceLocator.getInventoryService().updateInventory(inventory.getItems());
    	}
    	
    	CreditCardPayment creditCardPayment = new CreditCardPayment();
    	PaymentInfo paymentInfo = order.getPayment();
    	creditCardPayment.setCardName(paymentInfo.getCardName());
    	creditCardPayment.setCcNumber(paymentInfo.getCcNumber());
    	creditCardPayment.setCvvCode(paymentInfo.getCvvCode());
    	creditCardPayment.setEmailAddress(paymentInfo.getEmailAddress());
    	creditCardPayment.setExpiration(paymentInfo.getExpiration());
    	
    	String result = service.getPaymentProcessorPort().processPayment(creditCardPayment); 
    	if(Integer.parseInt(result) < 0) {
    		return "Invalid Payment Info";
    	}
    	   	
    	ShippingInitiationClient SIC = new ShippingInitiationClient(shippingResourceURI);
    	
    	JsonObject j = Json.createObjectBuilder().add("Organization", "KittenStore")
    			.add("OrderRefId", order.getId())
    			.add("Zip", order.getShipping().getZip())
    			.add("ItemsNumber", order.getLineItems().size())
    			.build();

    	JsonObject responseJson = SIC.invokeInitiateShipping(j);
    	System.out.println("UPS accepted request? " + responseJson.getBoolean("Accepted"));
    	System.out.println("Shipping Reference Number: " +  responseJson.getInt("ShippingReferenceNumber"));
    	
    	
    	
    	return result;
    } 
    
    public boolean validateItemAvailability(Order order) {
    	//Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
    	return ServiceLocator.getInventoryService().validateQuantity(util.lineItemListToItemList(order.getLineItems()));
    }

}
