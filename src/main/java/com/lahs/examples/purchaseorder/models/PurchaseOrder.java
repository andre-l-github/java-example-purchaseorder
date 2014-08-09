/**
 * 
 */
package com.lahs.examples.purchaseorder.models;

/**
 * @author andre
 * 
 */
public class PurchaseOrder {
	private final Integer id;
	private final Long valueInCents;

	/**
	 * @param id
	 * @param valueInCents
	 */
	public PurchaseOrder(Integer id, Long valueInCents) {
		super();
		this.id = id;
		this.valueInCents = valueInCents;
	}

	public Integer getId() {
		return id;
	}

	public Long getValueInCents() {
		return valueInCents;
	}
}
