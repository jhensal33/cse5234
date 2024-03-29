package edu.osu.cse5234.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.models.Item;
import edu.osu.cse5234.models.Order;
import edu.osu.cse5234.models.PaymentInfo;
import edu.osu.cse5234.models.ShippingInfo;
import edu.osu.cse5234.util.ServiceLocator;
import edu.osu.cse5234.util.Utility;

@Controller
@RequestMapping("/purchase")
public class Purchase {
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewOrderEntryForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Order ord = new Order();
		Utility utility = new Utility();
		
		Inventory inventory = ServiceLocator.getInventoryService().getAvailableInventory();
		ord.setLineItems(utility.itemListToLineItemList(inventory.getItems()));
		
		request.setAttribute("order", ord);
		request.getSession().setAttribute("validQuantity", "");
		return "OrderEntryForm";
	}
	
	@RequestMapping(path = "/submitItems", method = RequestMethod.POST)
	public String submitItems(@ModelAttribute("order") Order order, HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("order", order);
		
		if(ServiceLocator.getOrderProcessingService().validateItemAvailability(order)) {
			return "redirect:/purchase/paymentEntry";
		} else {  
			request.getSession().setAttribute("validQuantity", "Please resubmit item quantities");
			return "redirect:/purchase";
		}
		
	}

	@RequestMapping(path = "/paymentEntry", method = RequestMethod.GET)
	public String paymentEntry(HttpServletRequest request) throws Exception {
		request.setAttribute("payment", new PaymentInfo());
		return "PaymentEntryForm";
	}
	
	@RequestMapping(path = "/submitPayment", method = RequestMethod.POST)
	public String submitPayment(@ModelAttribute("payment") PaymentInfo payment, HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("payment", payment);
		return "redirect:/purchase/shippingEntry";
	}

	@RequestMapping(path = "/shippingEntry", method = RequestMethod.GET)
	public String shippingEntry(HttpServletRequest request) throws Exception {
		request.setAttribute("shipping", new ShippingInfo());
		return "ShippingEntryForm";
	}
	
	@RequestMapping(path = "/submitShipping", method = RequestMethod.POST)
	public String submitShipping(@ModelAttribute("shipping") ShippingInfo shipping, HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("shipping", shipping);
		return "redirect:/purchase/viewOrder";
	}	

	@RequestMapping(path = "/viewOrder", method = RequestMethod.GET)
	public String viewOrder() throws Exception {
		return "ViewOrder";
	}	

	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpServletRequest request) throws Exception {
		
		Order order = (Order) request.getSession().getAttribute("order");
		
		PaymentInfo pi = (PaymentInfo) request.getSession().getAttribute("payment");
		ShippingInfo si = ((ShippingInfo) request.getSession().getAttribute("shipping"));
		
		order.setShipping(si);
		order.setCustomerName(si.getName());
		
		order.setPayment(pi);
		order.setEmailAddress(pi.getEmailAddress());
		
		request.getSession().setAttribute("confirmationCode", ServiceLocator.getOrderProcessingService().processOrder(order));
		return "redirect:/purchase/viewConfirmation";
	}	

	@RequestMapping(path = "/viewConfirmation", method = RequestMethod.GET)
	public String viewConfirmation() throws Exception {
		return "Confirmation";
	}	

}