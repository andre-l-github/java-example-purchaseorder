/**
 * 
 */
package com.lahs.examples.purchaseorder.processes;

/**
 * @author andre
 *
 */
public class PurchaseOrderProcess {

	public static final String KEY = "process_PurchaseOrder";

	public static String getBusinessKeyFromOrderId(Long orderId) {
		return KEY + "::" + orderId;
	}
}
