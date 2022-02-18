package org.spieckermann.skateboarding.decks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
	
	@Query("select d from Deck d where d.width = :width")
	List<Deck> findByWidth(double width);
	
}
