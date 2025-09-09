package com.tap.models;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cart {
	 private Map<Integer, CartItem> items;

	public cart() {	
		this.items = new HashMap<>();
	}
	

	public void addCartItem(CartItem newItem) {
	    int itemId = newItem.getId();

	    if (items.containsKey(itemId)) {
	        
	        CartItem existingItem = items.get(itemId);
	        existingItem.setQuantity(existingItem.getQuantity() + 1);
	    } else {
	       
	        items.put(itemId, newItem);
	    }
	}
	public void updateCartItem(int itemId, int quantity) {
	    if (items.containsKey(itemId)) {
	        if (quantity > 0) {
	            
	            CartItem item = items.get(itemId);
	            item.setQuantity(quantity);
	        } else {
	           
	            items.remove(itemId);
	        }
	    }
	}

	public void removeCartItem(int itemId) {
	    items.remove(itemId);
	}
	 public List<CartItem> getCartItems() {
	        return new ArrayList<>(items.values());
	    }

	    
	    public double getTotalPrice() {
	        double total = 0;
	        for (CartItem item : items.values()) {
	            total += item.getPrice() * item.getQuantity();
	        }
	        return total;
	    }
	    
	    public Map<Integer, CartItem> getItems() {
			return items;
		}
	}

