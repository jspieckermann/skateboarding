package org.spieckermann.skateboarding.wheels;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WheelRepository extends JpaRepository<Wheel, Long> {
	
	@Query("select w from Wheel w where w.size = :size")
	List<Wheel> findBySize(int size);
	
}
