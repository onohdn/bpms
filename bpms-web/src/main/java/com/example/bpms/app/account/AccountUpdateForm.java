package com.example.bpms.app.account;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;

}