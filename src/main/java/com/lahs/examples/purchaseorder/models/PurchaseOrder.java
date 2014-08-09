/**
 * 
 */
package com.lahs.examples.purchaseorder.models;

/**
 * @author andre
 * 
 */
public class PurchaseOrder {
	private Integer id;
	private Long valueInCents;

	/**
	 * @param id
	 * @param valueInCents
	 */
	public PurchaseOrder(Integer id, Long valueInCents) {
		this.id = id;
		this.valueInCents = valueInCents;
	}
	
	public PurchaseOrder() {}

	public Integer getId() {
		return id;
	}

	public Long getValueInCents() {
		return valueInCents;
	}
}
