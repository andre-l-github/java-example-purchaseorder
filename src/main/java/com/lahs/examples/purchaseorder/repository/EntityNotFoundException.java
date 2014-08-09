/**
 * 
 */
package com.lahs.examples.purchaseorder.repository;


/**
 * @author andre
 *
 */
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = -1006948818323383711L;

	public EntityNotFoundException(Class<?> class1, Long id) {
		super(class1.getName() + " with id " + id + " cannot be found.");
	}

}
