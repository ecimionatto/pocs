package com.codenuance.kodeego.to;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CodeTo {

	@NotNull
	@Size(min = 1, max = 60000)
	private String code;
	@NotNull
	private String comments;
	@NotNull
	private String type;

}
