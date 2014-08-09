/**
 * 
 */
package com.lahs.examples.purchaseorder.models;

/**
 * @author andre
 * 
 */
public class PurchaseOrder {
	private Long id;
	private Long valueInCents;

	/**
	 * @param id
	 * @param valueInCents
	 */
	public PurchaseOrder(Long id, Long valueInCents) {
		this.id = id;
		this.valueInCents = valueInCents;
	}
	
	public PurchaseOrder() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValueInCents() {
		return valueInCents;
	}

	public void setValueInCents(Long valueInCents) {
		this.valueInCents = valueInCents;
	}
}
