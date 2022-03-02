package org.spieckermann.skateboarding.wheels;

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
@WebMvcTest(WheelController.class)
public class WheelControllerTests {

	@MockBean
	WheelService wheelService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	Wheel wheel1 = new Wheel(Company.SPITFIRE, "F4 Conical Full", 53, 33.5, 21.5, "99", 225, 59);
	Wheel wheel2 = new Wheel(Company.SPITFIRE, "F4 Conical Full", 52, 32.5, 21, "101", 220, 59);

	@Test
	public void testGetWheels() throws Exception {

		List<Wheel> wheels = Arrays.asList(wheel1, wheel2);
		List<Wheel> wheelsBySize = Arrays.asList(wheel1);

		Mockito.when(wheelService.getWheels()).thenReturn(wheels);
		Mockito.when(wheelService.getWheels(53)).thenReturn(wheelsBySize);

		mockMvc.perform(get("/wheels")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].size", Matchers.is(53))).andExpect(jsonPath("$[1].size", Matchers.is(52)));
		
		mockMvc.perform(get("/wheels?size=53")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));

		Mockito.when(wheelService.getWheels()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/wheels")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(0)));

		verify(wheelService, times(2)).getWheels();
		verify(wheelService, times(1)).getWheels(53);
	}

	@Test
	public void testGetWheel() throws Exception {

		Mockito.when(wheelService.getWheel(1L)).thenReturn(wheel1);
		Mockito.when(wheelService.getWheel(2L)).thenReturn(wheel2);
		Mockito.when(wheelService.getWheel(3L)).thenThrow(new WheelNotFoundException(3L));

		mockMvc.perform(get("/wheels/1")).andExpect(status().isOk()).andExpect(jsonPath("$.size", Matchers.is(53)));
		verify(wheelService, times(1)).getWheel(1L);

		mockMvc.perform(get("/wheels/2")).andExpect(status().isOk()).andExpect(jsonPath("$.size", Matchers.is(52)));
		verify(wheelService, times(1)).getWheel(2L);

		mockMvc.perform(get("/wheels/3")).andExpect(status().isNotFound());
		verify(wheelService, times(1)).getWheel(3L);
	}

	@Test
	public void testCreateWheel() throws Exception {

		Mockito.when(wheelService.createWheel(wheel1)).thenReturn(wheel1);

		mockMvc.perform(
				post("/wheels").contentType("application/json").content(objectMapper.writeValueAsString(wheel1)))
				.andExpect(status().isOk());

		verify(wheelService, times(1)).createWheel(Mockito.any(Wheel.class));

		mockMvc.perform(post("/wheels").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteWheel() throws Exception {
		doThrow(new WheelNotFoundException(2L)).when(wheelService).deleteWheel(2L);

		mockMvc.perform(delete("/wheels/1")).andExpect(status().isOk());
		verify(wheelService, times(1)).deleteWheel(1L);

		mockMvc.perform(delete("/wheels/2")).andExpect(status().isNotFound());
		mockMvc.perform(delete("/wheels/null")).andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateWheel() throws Exception {

		Mockito.when(wheelService.updateWheel(Mockito.eq(2L), Mockito.any(Wheel.class)))
				.thenThrow(new WheelNotFoundException(1L));

		mockMvc.perform(
				put("/wheels/1").contentType("application/json").content(objectMapper.writeValueAsString(wheel1)))
				.andExpect(status().isOk());

		verify(wheelService, times(1)).updateWheel(Mockito.eq(1L), Mockito.any(Wheel.class));

		mockMvc.perform(
				put("/wheels/2").contentType("application/json").content(objectMapper.writeValueAsString(wheel1)))
				.andExpect(status().isNotFound());

		mockMvc.perform(put("/wheels/2").contentType("application/json")).andExpect(status().isBadRequest());
	}

}
