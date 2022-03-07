package org.spieckermann.skateboarding.griptape;

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
@WebMvcTest(GriptapeController.class)
public class GriptapeControllerTests {

	@MockBean
	GriptapeService griptapeService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	Griptape grip1 = new Griptape(Company.GRIZZLY, "Santiago Signature Stamp", 33, 9, 34, 11);
	Griptape grip2 = new Griptape(Company.GRIZZLY, "Pudwill Signature Stamp", 33, 9, 34, 11);

	@Test
	public void testGetGriptapeList() throws Exception {

		List<Griptape> griptape = Arrays.asList(grip1, grip2);

		Mockito.when(griptapeService.getGriptape()).thenReturn(griptape);

		mockMvc.perform(get("/griptape")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].name", Matchers.is("Santiago Signature Stamp"))).andExpect(jsonPath("$[1].name", Matchers.is("Pudwill Signature Stamp")));
		

		Mockito.when(griptapeService.getGriptape()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/griptape")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(0)));

		verify(griptapeService, times(2)).getGriptape();
	}

	@Test
	public void testGetGriptape() throws Exception {

		Mockito.when(griptapeService.getGriptape(1L)).thenReturn(grip1);
		Mockito.when(griptapeService.getGriptape(2L)).thenReturn(grip2);
		Mockito.when(griptapeService.getGriptape(3L)).thenThrow(new GriptapeNotFoundException(3L));

		mockMvc.perform(get("/griptape/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name", Matchers.is("Santiago Signature Stamp")));
		verify(griptapeService, times(1)).getGriptape(1L);

		mockMvc.perform(get("/griptape/2")).andExpect(status().isOk()).andExpect(jsonPath("$.name", Matchers.is("Pudwill Signature Stamp")));
		verify(griptapeService, times(1)).getGriptape(2L);

		mockMvc.perform(get("/griptape/3")).andExpect(status().isNotFound());
		verify(griptapeService, times(1)).getGriptape(3L);
	}

	@Test
	public void testCreateGriptape() throws Exception {

		Mockito.when(griptapeService.createGriptape(grip1)).thenReturn(grip1);

		mockMvc.perform(
				post("/griptape").contentType("application/json").content(objectMapper.writeValueAsString(grip1)))
				.andExpect(status().isOk());

		verify(griptapeService, times(1)).createGriptape(Mockito.any(Griptape.class));

		mockMvc.perform(post("/griptape").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteGriptape() throws Exception {
		doThrow(new GriptapeNotFoundException(2L)).when(griptapeService).deleteGriptape(2L);

		mockMvc.perform(delete("/griptape/1")).andExpect(status().isOk());
		verify(griptapeService, times(1)).deleteGriptape(1L);

		mockMvc.perform(delete("/griptape/2")).andExpect(status().isNotFound());
		mockMvc.perform(delete("/griptape/null")).andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateGriptape() throws Exception {

		Mockito.when(griptapeService.updateGriptape(Mockito.eq(2L), Mockito.any(Griptape.class)))
				.thenThrow(new GriptapeNotFoundException(2L));

		mockMvc.perform(
				put("/griptape/1").contentType("application/json").content(objectMapper.writeValueAsString(grip1)))
				.andExpect(status().isOk());

		verify(griptapeService, times(1)).updateGriptape(Mockito.eq(1L), Mockito.any(Griptape.class));

		mockMvc.perform(
				put("/griptape/2").contentType("application/json").content(objectMapper.writeValueAsString(grip1)))
				.andExpect(status().isNotFound());

		mockMvc.perform(put("/griptape/2").contentType("application/json")).andExpect(status().isBadRequest());
	}

}
