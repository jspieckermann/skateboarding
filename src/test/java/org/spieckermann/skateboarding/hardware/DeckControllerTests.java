package org.spieckermann.skateboarding.hardware;

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
@WebMvcTest(HardwareController.class)
public class DeckControllerTests {

	@MockBean
	HardwareService hardwareService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	Hardware hw1 = new Hardware(Company.ANTIX, "Standards", Head.ALLEN, 1.0, 42);
	Hardware hw2 = new Hardware(Company.ANTIX, "Standards", Head.PHILIPS, 1.0, 42);

	@Test
	public void testGetHardwareList() throws Exception {

		List<Hardware> hardware = Arrays.asList(hw1, hw2);
		List<Hardware> hardwareByHead = Arrays.asList(hw1);

		Mockito.when(hardwareService.getHardware()).thenReturn(hardware);
		Mockito.when(hardwareService.getHardware(Head.ALLEN)).thenReturn(hardwareByHead);

		mockMvc.perform(get("/hardware")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].head", Matchers.is(Head.ALLEN.toString()))).andExpect(jsonPath("$[1].head", Matchers.is(Head.PHILIPS.toString())));
		
		mockMvc.perform(get("/hardware?head=ALLEN")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));

		Mockito.when(hardwareService.getHardware()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/hardware")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(0)));

		verify(hardwareService, times(2)).getHardware();
		verify(hardwareService, times(1)).getHardware(Head.ALLEN);
	}

	@Test
	public void testGetHardware() throws Exception {

		Mockito.when(hardwareService.getHardware(1L)).thenReturn(hw1);
		Mockito.when(hardwareService.getHardware(2L)).thenReturn(hw2);
		Mockito.when(hardwareService.getHardware(3L)).thenThrow(new HardwareNotFoundException(3L));

		mockMvc.perform(get("/hardware/1")).andExpect(status().isOk()).andExpect(jsonPath("$.head", Matchers.is(Head.ALLEN.toString())));
		verify(hardwareService, times(1)).getHardware(1L);

		mockMvc.perform(get("/hardware/2")).andExpect(status().isOk()).andExpect(jsonPath("$.head", Matchers.is(Head.PHILIPS.toString())));
		verify(hardwareService, times(1)).getHardware(2L);

		mockMvc.perform(get("/hardware/3")).andExpect(status().isNotFound());
		verify(hardwareService, times(1)).getHardware(3L);
	}

	@Test
	public void testCreateHardware() throws Exception {

		Mockito.when(hardwareService.createHardware(hw1)).thenReturn(hw1);

		mockMvc.perform(
				post("/hardware").contentType("application/json").content(objectMapper.writeValueAsString(hw1)))
				.andExpect(status().isOk());

		verify(hardwareService, times(1)).createHardware(Mockito.any(Hardware.class));

		mockMvc.perform(post("/hardware").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteHardware() throws Exception {
		doThrow(new HardwareNotFoundException(2L)).when(hardwareService).deleteHardware(2L);

		mockMvc.perform(delete("/hardware/1")).andExpect(status().isOk());
		verify(hardwareService, times(1)).deleteHardware(1L);

		mockMvc.perform(delete("/hardware/2")).andExpect(status().isNotFound());
		mockMvc.perform(delete("/hardware/null")).andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateDeck() throws Exception {

		Mockito.when(hardwareService.updateHardware(Mockito.eq(2L), Mockito.any(Hardware.class)))
				.thenThrow(new HardwareNotFoundException(1L));

		mockMvc.perform(
				put("/hardware/1").contentType("application/json").content(objectMapper.writeValueAsString(hw1)))
				.andExpect(status().isOk());

		verify(hardwareService, times(1)).updateHardware(Mockito.eq(1L), Mockito.any(Hardware.class));

		mockMvc.perform(
				put("/hardware/2").contentType("application/json").content(objectMapper.writeValueAsString(hw1)))
				.andExpect(status().isNotFound());

		mockMvc.perform(put("/hardware/2").contentType("application/json")).andExpect(status().isBadRequest());
	}

}
