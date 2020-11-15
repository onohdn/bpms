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
		
		// id検索し、未登録idの場合は新規データ生成を行う
		if (!battingresultsRepository.findById(battingresults.getId()).isPresent()) {
			// 入力された打撃成績で、データ生成
			battingresultsRepository.create(battingresults);
		} else {
			// id検索し、登録されているデータを取得する
			Battingresults registeredButtingresults = battingresultsRepository.findById(battingresults.getId()).get();
			
			// 入力された打撃成績で、データを更新する
			Battingresults addBattingresults = addBattingresults(registeredButtingresults, battingresults);
			battingresultsRepository.updateById(addBattingresults);
		}
		
		// 更新後のデータを取得する
		Battingresults updatedButtingresults = battingresultsRepository.findById(battingresults.getId()).get();
			
		// 登録された打撃成績を用いて、各種指標を計算する
		Battingresults calculatedBattingresults = indexCalc(updatedButtingresults);
			
		// 各種指標の計算結果を登録する
		battingresultsRepository.updateIndexValueById(calculatedBattingresults);
		
		return updatedButtingresults;
	}

	/**
	 * 入力された打撃成績と、登録済みの打撃成績を足しこむメソッド
	 * @param registeredButtingresults
	 * @param battingresults
	 * @return addBattingresults
	 */
	private Battingresults addBattingresults(Battingresults registeredButtingresults,
			Battingresults battingresults) {
		Battingresults addBattingresults = new Battingresults();
		addBattingresults.setId(registeredButtingresults.getId());
		addBattingresults.setPlate_appearance(registeredButtingresults.getPlate_appearance() + battingresults.getPlate_appearance());
		addBattingresults.setHit(registeredButtingresults.getHit() + battingresults.getHit());
		addBattingresults.setTwo_base_hit(registeredButtingresults.getTwo_base_hit() + battingresults.getTwo_base_hit());
		addBattingresults.setThree_base_hit(registeredButtingresults.getThree_base_hit() + battingresults.getThree_base_hit());
		addBattingresults.setHome_run(registeredButtingresults.getHome_run() + battingresults.getHome_run());
		addBattingresults.setWalks(registeredButtingresults.getWalks() + battingresults.getWalks());
		addBattingresults.setHit_by_pitch(registeredButtingresults.getHit_by_pitch() + battingresults.getHit_by_pitch());
		addBattingresults.setSacrifice_bunt(registeredButtingresults.getSacrifice_bunt() + battingresults.getSacrifice_bunt());
		addBattingresults.setSacrifice_fly(registeredButtingresults.getSacrifice_fly() + battingresults.getSacrifice_fly());
		addBattingresults.setFaux_pas(registeredButtingresults.getFaux_pas() + battingresults.getFaux_pas());
		
		return addBattingresults;
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
