package org.spieckermann.skateboarding.hardware;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
	
	@Query("select h from Hardware h where h.head = :head")
	List<Hardware> findByHead(Head head);
	
}
