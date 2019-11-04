package edu.osu.cse5234.business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.chase.payment.*;

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
 	    private PaymentProcessor service;
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
    	
    	paymentInfo.setConfirmationNumber(result);
    	return result;
    } 
    
    public boolean validateItemAvailability(Order order) {
    	//Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
    	return ServiceLocator.getInventoryService().validateQuantity(util.lineItemListToItemList(order.getLineItems()));
    }

}
