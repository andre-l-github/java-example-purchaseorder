/**
 * 
 */
package com.lahs.examples.purchaseorder.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * @author andre
 * 
 */
public class PurchaseOrdersControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	PurchaseOrdersController controller;

	UUID key = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).setMessageConverters(
				new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void thatPurchaseOrderCanBeShown() throws Exception {
		MockHttpServletRequestBuilder query = get("/purchase_orders/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);

		this.mockMvc.perform(query).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.valueInCents").value(10000));
	}
}
