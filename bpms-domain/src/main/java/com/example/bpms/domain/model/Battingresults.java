package com.example.bpms.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Battingresults implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private int plate_appearance;
	
	private int at_bat;
	
	private int hit;
	
	private int two_base_hit;
	
	private int three_base_hit;
	
	private int home_run;
	
	private int walks;
	
	private int hit_by_pitch;
	
	private int sacrifice_bunt;
	
	private int sacrifice_fly;
	
	private int faux_pas;
	
	private double batting_average;
	
	private double on_base_percentage;
	
	private double slugging_percentage;
	
	private double on_base_plus_slugging;
}
