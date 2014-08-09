/**
 * 
 */
package com.lahs.examples.purchaseorder.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lahs.examples.purchaseorder.models.PurchaseOrder;

/**
 * @author andre
 *
 */
@Component
public class PurchaseOrderMemoryRepository implements PurchaseOrderRepository {

	private static Map<Long, PurchaseOrder> database = new HashMap<Long, PurchaseOrder>();
	private static Long sequence = 0l;
	
	/* (non-Javadoc)
	 * @see com.lahs.examples.purchaseorder.repository.IPurchaseOrderRepository#create(com.lahs.examples.purchaseorder.models.PurchaseOrder)
	 */
	@Override
	public PurchaseOrder create(PurchaseOrder purchaseOrder) {
		sequence++;
		
		purchaseOrder.setId(sequence);
		database.put(sequence, purchaseOrder);
		
		return purchaseOrder;
	}

	/* (non-Javadoc)
	 * @see com.lahs.examples.purchaseorder.repository.IPurchaseOrderRepository#find(java.lang.Long)
	 */
	@Override
	public PurchaseOrder find(Long id) throws EntityNotFoundException {
		if (database.containsKey(id)) {
			return database.get(id);
		} else {
			throw new EntityNotFoundException(PurchaseOrder.class, id);
		}
	}

	/* (non-Javadoc)
	 * @see com.lahs.examples.purchaseorder.repository.IPurchaseOrderRepository#all()
	 */
	@Override
	public List<PurchaseOrder> all() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void reset() {
		database = new HashMap<Long, PurchaseOrder>();
		sequence = 0l;
	}

}
