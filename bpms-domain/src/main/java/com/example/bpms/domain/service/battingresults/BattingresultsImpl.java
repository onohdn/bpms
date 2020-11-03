package com.example.bpms.domain.service.battingresults;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bpms.domain.model.Battingresults;
import com.example.bpms.domain.repository.battingresults.BattingresultsRepository;

@Service
@Transactional
public class BattingresultsImpl implements BattingresultsService {

	@Inject
	BattingresultsRepository battingresultsRepository;
	
	@Override
	public Battingresults create(Battingresults battingresults) {
		// 入力された打撃成績で、データ生成
		battingresultsRepository.create(battingresults);
		
		// 入力された打撃成績を用いて、各種指標を計算する
		Battingresults calculatedBattingresults = indexCalc(battingresults);
		
		// 各種指標の計算結果を登録する
		battingresultsRepository.updateIndexValueById(calculatedBattingresults);
		
		return battingresults;
	}

	/**
	 * 各種指標を計算するメソッド
	 * @param battingresults
	 * @return battingresults
	 */
	private Battingresults indexCalc(Battingresults battingresults) {
		
		// 打数を計算
		int atBat = battingresults.getPlate_appearance() - (battingresults.getWalks() + battingresults.getHit_by_pitch() + battingresults.getSacrifice_bunt() + battingresults.getSacrifice_fly()); 
		
		// 打率 batting_averageを計算
		int hitsCount = battingresults.getHit() + battingresults.getTwo_base_hit() + battingresults.getThree_base_hit() + battingresults.getHome_run();
		double battingAverage = (double)hitsCount / (double)atBat;
		
		// 出塁率 on_base_percentageを計算
		double onBasePercentage = (double)(hitsCount + battingresults.getWalks() + battingresults.getHit_by_pitch()) / (double)(atBat + battingresults.getWalks() + battingresults.getHit_by_pitch() + battingresults.getSacrifice_fly());
		
		// 長打率 slugging_percentageを計算
		int totalBases = (battingresults.getHit() * 1) + (battingresults.getTwo_base_hit() * 2) + (battingresults.getThree_base_hit() * 3) + (battingresults.getHome_run() * 4);
		double sluggingPercentage = (double)totalBases / (double)atBat;
		
		// OPS on_base_plus_sluggingを計算
		double onBasePlusSlugging = onBasePercentage + sluggingPercentage;
		
		// 計算結果を詰め込む
		battingresults.setAt_bat(atBat);
		battingresults.setBatting_average(battingAverage);
		battingresults.setOn_base_percentage(onBasePercentage);
		battingresults.setSlugging_percentage(sluggingPercentage);
		battingresults.setOn_base_plus_slugging(onBasePlusSlugging);
		
		return battingresults;
	}

}
