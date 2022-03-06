package org.spieckermann.skateboarding.trucks;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TruckController.class)
public class TruckControllerTests {

	@MockBean
	TruckService truckService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	Truck truck1 = new Truck(Company.THUNDER, "Titanium Lights", 147, 8.0, 49, 288, 129);
	Truck truck2 = new Truck(Company.THUNDER, "Titanium Lights", 148, 8.25, 52, 308, 129);

	@Test
	public void testGetTrucks() throws Exception {

		List<Truck> trucks = Arrays.asList(truck1, truck2);
		List<Truck> trucksByWidth = Arrays.asList(truck1);

		Mockito.when(truckService.getTrucks()).thenReturn(trucks);
		Mockito.when(truckService.getTrucks(8.0)).thenReturn(trucksByWidth);

		mockMvc.perform(get("/trucks")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].size", Matchers.is(147))).andExpect(jsonPath("$[1].size", Matchers.is(148)));
		
		mockMvc.perform(get("/trucks?width=8.0")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));

		Mockito.when(truckService.getTrucks()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/trucks")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(0)));

		verify(truckService, times(2)).getTrucks();
		verify(truckService, times(1)).getTrucks(8.0);
	}

	@Test
	public void testGetTruck() throws Exception {

		Mockito.when(truckService.getTruck(1L)).thenReturn(truck1);
		Mockito.when(truckService.getTruck(2L)).thenReturn(truck2);
		Mockito.when(truckService.getTruck(3L)).thenThrow(new TruckNotFoundException(3L));

		mockMvc.perform(get("/trucks/1")).andExpect(status().isOk()).andExpect(jsonPath("$.size", Matchers.is(147)));
		verify(truckService, times(1)).getTruck(1L);

		mockMvc.perform(get("/trucks/2")).andExpect(status().isOk()).andExpect(jsonPath("$.size", Matchers.is(148)));
		verify(truckService, times(1)).getTruck(2L);

		mockMvc.perform(get("/trucks/3")).andExpect(status().isNotFound());
		verify(truckService, times(1)).getTruck(3L);
	}

	@Test
	public void testCreateTruck() throws Exception {

		Mockito.when(truckService.createTruck(truck1)).thenReturn(truck1);

		mockMvc.perform(
				post("/trucks").contentType("application/json").content(objectMapper.writeValueAsString(truck1)))
				.andExpect(status().isOk());

		verify(truckService, times(1)).createTruck(Mockito.any(Truck.class));

		mockMvc.perform(post("/trucks").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteTruck() throws Exception {
		doThrow(new TruckNotFoundException(2L)).when(truckService).deleteTruck(2L);

		mockMvc.perform(delete("/trucks/1")).andExpect(status().isOk());
		verify(truckService, times(1)).deleteTruck(1L);

		mockMvc.perform(delete("/trucks/2")).andExpect(status().isNotFound());
		mockMvc.perform(delete("/trucks/null")).andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateTruck() throws Exception {

		Mockito.when(truckService.updateTruck(Mockito.eq(2L), Mockito.any(Truck.class)))
				.thenThrow(new TruckNotFoundException(1L));

		mockMvc.perform(
				put("/trucks/1").contentType("application/json").content(objectMapper.writeValueAsString(truck1)))
				.andExpect(status().isOk());

		verify(truckService, times(1)).updateTruck(Mockito.eq(1L), Mockito.any(Truck.class));

		mockMvc.perform(
				put("/trucks/2").contentType("application/json").content(objectMapper.writeValueAsString(truck1)))
				.andExpect(status().isNotFound());

		mockMvc.perform(put("/trucks/2").contentType("application/json")).andExpect(status().isBadRequest());
	}

}
