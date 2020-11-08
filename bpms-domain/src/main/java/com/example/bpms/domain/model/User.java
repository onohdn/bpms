package com.example.bpms.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private String password;
}
