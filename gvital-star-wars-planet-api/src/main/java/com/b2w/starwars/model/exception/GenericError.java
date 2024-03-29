package com.b2w.starwars.model.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GenericError implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime date;
	private int status;
	private String errorMessage;
	private String path;
	private Map<String, String> errors;
	private String exceptionMessage;
}
