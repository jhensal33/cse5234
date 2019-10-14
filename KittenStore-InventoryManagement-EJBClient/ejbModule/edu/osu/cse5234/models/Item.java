package edu.osu.cse5234.models;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Item
 */
@Stateless
@LocalBean
public class Item  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 866179739582712365L;
	private String name;
	private String price;
	private String quantity;
	
	/**
     * Default constructor. 
     */
    public Item() {
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
