/**
 * 
 */
package com.lahs.examples.purchaseorder.controllers;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lahs.examples.purchaseorder.models.PurchaseOrder;
import com.lahs.examples.purchaseorder.processes.PurchaseOrderProcess;
import com.lahs.examples.purchaseorder.repository.EntityNotFoundException;
import com.lahs.examples.purchaseorder.repository.PurchaseOrderRepository;

/**
 * @author andre
 * 
 */
@Controller
@RequestMapping("/purchase_orders")
public class PurchaseOrdersController {

	@Autowired
	private PurchaseOrderRepository orderRepository;

	@Autowired
	private RuntimeService runtimeService;

	@RequestMapping("/{id}")
	public ResponseEntity<PurchaseOrder> show(@PathVariable Long id) {
		try {
			return new ResponseEntity<PurchaseOrder>(orderRepository.find(id),
					HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<PurchaseOrder>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PurchaseOrder> create(
			@RequestBody PurchaseOrder purchaseOrder) {

		PurchaseOrder newOrder = orderRepository.create(purchaseOrder);

		runtimeService
				.startProcessInstanceByKey(PurchaseOrderProcess.KEY,
						PurchaseOrderProcess.getBusinessKeyFromOrderId(newOrder
								.getId()));

		return new ResponseEntity<PurchaseOrder>(newOrder, HttpStatus.CREATED);
	}
}
