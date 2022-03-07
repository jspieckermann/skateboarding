package org.spieckermann.skateboarding.completes;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spieckermann.skateboarding.company.Company;
import org.spieckermann.skateboarding.decks.Concave;
import org.spieckermann.skateboarding.decks.Deck;
import org.spieckermann.skateboarding.griptape.Griptape;
import org.spieckermann.skateboarding.hardware.Hardware;
import org.spieckermann.skateboarding.hardware.Head;
import org.spieckermann.skateboarding.trucks.Truck;
import org.spieckermann.skateboarding.wheels.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CompleteController.class)
public class CompleteControllerTests {

	@MockBean
	CompleteService completeService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	Deck deck1 = new Deck(Company.BAKER, "Zach Goon Wall", 8.25, 31.875, 14.25, Concave.MELLOW, 7, 6.5, 1200, 89);
	Deck deck2 = new Deck(Company.BAKER, "Team Brand Logo", 8.475, 31.9, 14.25, Concave.MEDIUM, 7, 6.5, 1300, 85);
	Wheel wheel1 = new Wheel(Company.SPITFIRE, "F4 Conical Full", 53, 33.5, 21.5, "99", 225, 59);
	Wheel wheel2 = new Wheel(Company.SPITFIRE, "F4 Conical Full", 52, 32.5, 21, "101", 220, 59);
	Truck truck1 = new Truck(Company.THUNDER, "Titanium Lights", 147, 8.0, 49, 288, 129);
	Truck truck2 = new Truck(Company.THUNDER, "Titanium Lights", 148, 8.25, 52, 308, 129);
	Griptape grip1 = new Griptape(Company.GRIZZLY, "Santiago Signature Stamp", 33, 9, 34, 11);
	Griptape grip2 = new Griptape(Company.GRIZZLY, "Pudwill Signature Stamp", 33, 9, 34, 11);
	Hardware hw1 = new Hardware(Company.ANTIX, "Standards", Head.ALLEN, 1.0, 42, 7.5);
	Hardware hw2 = new Hardware(Company.ANTIX, "Standards", Head.PHILIPS, 1.0, 42, 7.5);
	Complete complete1 = new Complete(deck1, truck1, wheel1, grip1, hw1);
	Complete complete2 = new Complete(deck2, truck2, wheel2, grip2, hw2);

	@Test
	public void testGetCompletes() throws Exception {

		List<Complete> completes = Arrays.asList(complete1, complete2);

		Mockito.when(completeService.getCompletes()).thenReturn(completes);


		mockMvc.perform(get("/complete")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].deck.width", Matchers.is(8.25))).andExpect(jsonPath("$[1].deck.width", Matchers.is(8.475)));
		
		Mockito.when(completeService.getCompletes()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/complete")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(0)));

		verify(completeService, times(2)).getCompletes();
	}

	@Test
	public void testGetComplete() throws Exception {

		Mockito.when(completeService.getComplete(1L)).thenReturn(complete1);
		Mockito.when(completeService.getComplete(2L)).thenReturn(complete2);
		Mockito.when(completeService.getComplete(3L)).thenThrow(new CompleteNotFoundException(3L));

		mockMvc.perform(get("/complete/1")).andExpect(status().isOk()).andExpect(jsonPath("$.deck.width", Matchers.is(8.25)));
		verify(completeService, times(1)).getComplete(1L);

		mockMvc.perform(get("/complete/2")).andExpect(status().isOk()).andExpect(jsonPath("$.deck.width", Matchers.is(8.475)));
		verify(completeService, times(1)).getComplete(2L);

		mockMvc.perform(get("/complete/3")).andExpect(status().isNotFound());
		verify(completeService, times(1)).getComplete(3L);
	}

	@Test
	public void testCreateComplete() throws Exception {

		Mockito.when(completeService.createComplete(complete1)).thenReturn(complete1);

		mockMvc.perform(
				post("/complete").contentType("application/json").content(objectMapper.writeValueAsString(complete1)))
				.andExpect(status().isOk());

		verify(completeService, times(1)).createComplete(Mockito.any(Complete.class));

		mockMvc.perform(post("/complete").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteComplete() throws Exception {
		doThrow(new CompleteNotFoundException(2L)).when(completeService).deleteComplete(2L);

		mockMvc.perform(delete("/complete/1")).andExpect(status().isOk());
		verify(completeService, times(1)).deleteComplete(1L);

		mockMvc.perform(delete("/complete/2")).andExpect(status().isNotFound());
		mockMvc.perform(delete("/complete/null")).andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateComplete() throws Exception {

		Mockito.when(completeService.updateComplete(Mockito.eq(2L), Mockito.any(Complete.class)))
				.thenThrow(new CompleteNotFoundException(2L));

		mockMvc.perform(
				put("/complete/1").contentType("application/json").content(objectMapper.writeValueAsString(complete1)))
				.andExpect(status().isOk());

		verify(completeService, times(1)).updateComplete(Mockito.eq(1L), Mockito.any(Complete.class));

		mockMvc.perform(
				put("/complete/2").contentType("application/json").content(objectMapper.writeValueAsString(complete2)))
				.andExpect(status().isNotFound());

		mockMvc.perform(put("/complete/2").contentType("application/json")).andExpect(status().isBadRequest());
	}

}
