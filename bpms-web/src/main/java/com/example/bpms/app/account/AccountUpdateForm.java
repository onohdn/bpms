package com.example.bpms.app.account;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String name;

}
