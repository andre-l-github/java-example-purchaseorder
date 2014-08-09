/**
 * 
 */
package com.lahs.examples.purchaseorder.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;
import java.util.UUID;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lahs.examples.purchaseorder.Application;
import com.lahs.examples.purchaseorder.models.PurchaseOrder;
import com.lahs.examples.purchaseorder.processes.PurchaseOrderProcess;
import com.lahs.examples.purchaseorder.repository.PurchaseOrderMemoryRepository;
import com.lahs.examples.purchaseorder.repository.PurchaseOrderRepository;

/**
 * @author andre
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Application.class })
public class PurchaseOrdersControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	PurchaseOrdersController controller;

	@Autowired
	PurchaseOrderRepository repository;

	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	WebApplicationContext webApplicationContext;

	UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.webAppContextSetup(
				this.webApplicationContext).build();
		
		PurchaseOrderMemoryRepository.reset();
	}

	@Test
	public void thatExistingPurchaseOrderCanBeShown() throws Exception {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setValueInCents(10000l);

		repository.create(purchaseOrder);

		MockHttpServletRequestBuilder query = get("/purchase_orders/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(query).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.valueInCents").value(10000));
	}

	@Test
	public void thatMissingPurchaseOrderCannotBeFound() throws Exception {
		MockHttpServletRequestBuilder query = get("/purchase_orders/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(query).andExpect(status().isNotFound());
	}

	@Test
	@Deployment
	public void thatPurchaseOrderCanBeCreated() throws Exception {
		Long valueInCents = new Random().nextLong();

		RequestBuilder query = post("/purchase_orders")
				.content(standardOrderJSON(valueInCents))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(query).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.valueInCents").value(valueInCents));
	
		// TODO: This is way too much here, but just to get started with camunda...
		String businessKey = PurchaseOrderProcess.getBusinessKeyFromOrderId(1l);
		assertEquals(1, runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).count());
	}

	private String standardOrderJSON(Long valueInCents) {
		return "{ \"valueInCents\": " + valueInCents + " }";
	}
}
