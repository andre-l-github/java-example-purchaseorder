/**
 * 
 */
package com.lahs.examples.purchaseorder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lahs.examples.purchaseorder.models.PurchaseOrder;

/**
 * @author andre
 *
 */
@Controller
@RequestMapping("/purchase_orders")
public class PurchaseOrdersController {
	@RequestMapping("/{id}")
	public @ResponseBody PurchaseOrder show(@PathVariable Integer id) {
		
		return new PurchaseOrder(id, (long) 10000);
	}
}
