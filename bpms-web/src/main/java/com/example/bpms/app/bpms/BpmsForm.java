package com.example.bpms.app.bpms;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BpmsForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// id
	private String id;
	
	// 打席
	@NotNull
	@Min(0)
	private int plate_appearance;
	
	// 打数
	private int at_bat;
	
	// 単打
	@NotNull
	@Min(0)
	private int hit;
	
	// 二塁打
	@NotNull
	@Min(0)
	private int two_base_hit;
	
	// 三塁打
	@NotNull
	@Min(0)
	private int three_base_hit;
	
	// 本塁打
	@NotNull
	@Min(0)
	private int home_run;
	
	// 四球
	@NotNull
	@Min(0)
	private int walks;
	
	// 死球
	@NotNull
	@Min(0)
	private int hit_by_pitch;
	
	// 犠打
	@NotNull
	@Min(0)
	private int sacrifice_bunt;
	
	// 犠飛
	@NotNull
	@Min(0)
	private int sacrifice_fly;
	
	// 失策
	@NotNull
	@Min(0)
	private int faux_pas;
	
	// 打率
	private double batting_average;
	
	// 出塁率
	private double on_base_percentage;
	
	// 長打率
	private double slugging_percentage;
	
	// OPS
	private double on_base_plus_slugging;
	
	// アップロードファイル
	private MultipartFile file;
	
}
