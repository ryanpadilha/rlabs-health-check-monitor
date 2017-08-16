package br.com.rlabs.entity.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Status Details class.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Entity
@Table(name = "vl_status_details")
public class StatusDetails implements Serializable {

	private static final long serialVersionUID = 7674660259785293340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "pg-uuid")
	private UUID internal;

	@Column(name = "description")
	private String description;

	@Column(name = "result")
	private String result;

	@Column(name = "details")
	private String details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getInternal() {
		return internal;
	}

	public void setInternal(UUID internal) {
		this.internal = internal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
