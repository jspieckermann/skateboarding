package org.spieckermann.skateboarding.decks;

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
@WebMvcTest(DeckController.class)
public class DeckControllerTests {

	@MockBean
	DeckService deckService;

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	Deck deck1 = new Deck(Company.BAKER, "Zach Goon Wall", 8.25, 31.875, 14.25, Concave.MELLOW, 7, 6.5, 1200);
	Deck deck2 = new Deck(Company.BAKER, "Team Brand Logo", 8.475, 31.9, 14.25, Concave.MEDIUM, 7, 6.5, 1300);

	@Test
	public void testGetDecks() throws Exception {

		List<Deck> decks = Arrays.asList(deck1, deck2);
		List<Deck> decksByWidth = Arrays.asList(deck1);

		Mockito.when(deckService.getDecks()).thenReturn(decks);
		Mockito.when(deckService.getDecks(8.25)).thenReturn(decksByWidth);

		mockMvc.perform(get("/decks")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$[0].width", Matchers.is(8.25))).andExpect(jsonPath("$[1].width", Matchers.is(8.475)));
		
		mockMvc.perform(get("/decks?width=8.25")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));

		Mockito.when(deckService.getDecks()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/decks")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(0)));

		verify(deckService, times(2)).getDecks();
		verify(deckService, times(1)).getDecks(8.25);
	}

	@Test
	public void testGetDeck() throws Exception {

		Mockito.when(deckService.getDeck(1L)).thenReturn(deck1);
		Mockito.when(deckService.getDeck(2L)).thenReturn(deck2);
		Mockito.when(deckService.getDeck(3L)).thenThrow(new DeckNotFoundException(3L));

		mockMvc.perform(get("/decks/1")).andExpect(status().isOk()).andExpect(jsonPath("$.width", Matchers.is(8.25)));
		verify(deckService, times(1)).getDeck(1L);

		mockMvc.perform(get("/decks/2")).andExpect(status().isOk()).andExpect(jsonPath("$.width", Matchers.is(8.475)));
		verify(deckService, times(1)).getDeck(2L);

		mockMvc.perform(get("/decks/3")).andExpect(status().isNotFound());
		verify(deckService, times(1)).getDeck(3L);
	}

	@Test
	public void testCreateDeck() throws Exception {

		Mockito.when(deckService.createDeck(deck1)).thenReturn(deck1);

		mockMvc.perform(
				post("/decks").contentType("application/json").content(objectMapper.writeValueAsString(deck1)))
				.andExpect(status().isOk());

		verify(deckService, times(1)).createDeck(Mockito.any(Deck.class));

		mockMvc.perform(post("/decks").contentType("application/json")).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteDeck() throws Exception {
		doThrow(new DeckNotFoundException(2L)).when(deckService).deleteDeck(2L);

		mockMvc.perform(delete("/decks/1")).andExpect(status().isOk());
		verify(deckService, times(1)).deleteDeck(1L);

		mockMvc.perform(delete("/decks/2")).andExpect(status().isNotFound());
		mockMvc.perform(delete("/decks/null")).andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdateDeck() throws Exception {

		Mockito.when(deckService.updateDeck(Mockito.eq(2L), Mockito.any(Deck.class)))
				.thenThrow(new DeckNotFoundException(1L));

		mockMvc.perform(
				put("/decks/1").contentType("application/json").content(objectMapper.writeValueAsString(deck1)))
				.andExpect(status().isOk());

		verify(deckService, times(1)).updateDeck(Mockito.eq(1L), Mockito.any(Deck.class));

		mockMvc.perform(
				put("/decks/2").contentType("application/json").content(objectMapper.writeValueAsString(deck1)))
				.andExpect(status().isNotFound());

		mockMvc.perform(put("/decks/2").contentType("application/json")).andExpect(status().isBadRequest());
	}

}
