package com.example.bpms.domain.service.battingresults;

import com.example.bpms.domain.model.Battingresults;

public interface BattingresultsService {

	/**
	 * 打撃成績の新規作成
	 * @param battingresults
	 * @return 作成されたbattingresults
	 */
	Battingresults create(Battingresults battingresults);
	
}
