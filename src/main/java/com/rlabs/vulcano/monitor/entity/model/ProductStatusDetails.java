package com.rlabs.vulcano.monitor.entity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.rlabs.vulcano.monitor.commons.Status;

/**
 * Status Details class.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Entity
@Table(name = "vl_status_details")
public class ProductStatusDetails implements Serializable {

	private static final long serialVersionUID = 7674660259785293340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "pg-uuid")
	private UUID internal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "result")
	private Status result;

	@Column(name = "details")
	private String details;

	@ManyToOne
	@JoinColumn(name = "dependency_id")
	private Dependency dependency;

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getResult() {
		return result;
	}

	public void setResult(Status result) {
		this.result = result;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Dependency getDependency() {
		return dependency;
	}

	public void setDependency(Dependency dependency) {
		this.dependency = dependency;
	}

	@PrePersist
	protected void onInsert() {
		this.internal = UUID.randomUUID();
		this.created = new Date();
	}

}
