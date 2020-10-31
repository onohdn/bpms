package com.example.bpms.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Battingresults implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	// 打席
	private int plate_appearance;
	
	// 打数
	private int at_bat;
	
	// 単打
	private int hit;
	
	// 二塁打
	private int two_base_hit;
	
	// 三塁打
	private int three_base_hit;
	
	// 本塁打
	private int home_run;
	
	// 四球
	private int walks;
	
	// 死球
	private int hit_by_pitch;
	
	// 犠打
	private int sacrifice_bunt;
	
	// 犠飛
	private int sacrifice_fly;
	
	// 失策
	private int faux_pas;
	
	// 打率
	private double batting_average;
	
	// 出塁率
	private double on_base_percentage;
	
	// 長打率
	private double slugging_percentage;
	
	// OPS
	private double on_base_plus_slugging;
}
