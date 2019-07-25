package com.b2w.starwars.model.planet;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.b2w.starwars.model.GenericModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Guilherme Vital
 *
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "nome", "clima", "terreno", "qtdFilmes" })
public class Planet implements Serializable, GenericModel {

	private static final long serialVersionUID = 1L;
	
	@Transient
    public static final String SEQUENCE_NAME = "planets_sequence";

	@Id
	private Long id;
	
	@JsonProperty(required = true)
	@NotBlank(message = "Nome is mandatory")
	private String nome;
	
	@JsonProperty(required = true)
	@NotBlank(message = "Clima is mandatory")
	private String clima;
	
	@JsonProperty(required = true)
	@NotBlank(message = "Terreno is mandatory")
	private String terreno;
	
	@Transient
	private int qtdFilmes;

	/**
	 * @param name
	 * @param climate
	 * @param terrain
	 */
	public Planet(@NotBlank String name, @NotBlank String climate, @NotBlank String terrain) {
		super();
		this.nome = name;
		this.clima = climate;
		this.terreno = terrain;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param climate
	 * @param terrain
	 */
	public Planet(@NotBlank Long id, @NotBlank String name, @NotBlank String climate, @NotBlank String terrain) {
		super();
		this.id = id;
		this.nome = name;
		this.clima = climate;
		this.terreno = terrain;
	}

}
