package org.spieckermann.skateboarding.trucks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
	
	@Query("select t from Truck t where t.width = :width")
	List<Truck> findByWidth(double width);
	
}
