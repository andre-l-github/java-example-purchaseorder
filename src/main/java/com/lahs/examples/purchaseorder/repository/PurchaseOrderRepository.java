package com.lahs.examples.purchaseorder.repository;

import java.util.List;

import com.lahs.examples.purchaseorder.models.PurchaseOrder;

public interface PurchaseOrderRepository {
	PurchaseOrder create(PurchaseOrder purchaseOrder);

	PurchaseOrder find(Long id) throws EntityNotFoundException;

	List<PurchaseOrder> all();
}
