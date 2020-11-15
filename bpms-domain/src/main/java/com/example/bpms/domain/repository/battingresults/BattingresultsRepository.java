package com.example.bpms.domain.repository.battingresults;

import java.util.Collection;
import java.util.Optional;

import com.example.bpms.domain.model.Battingresults;

public interface BattingresultsRepository {

	Optional<Battingresults> findById(String id);
	
	Collection<Battingresults> findAll();
	
	void create(Battingresults battingresults);
	
	boolean updateById(Battingresults battingresults);
	
	boolean updateIndexValueById(Battingresults battingresults);
	
	void deleteById(Battingresults battingresults);
	
}
