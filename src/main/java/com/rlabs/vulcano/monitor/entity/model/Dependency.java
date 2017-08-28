package com.rlabs.vulcano.monitor.entity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.rlabs.vulcano.monitor.commons.DependencyType;

/**
 * Dependency class.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Entity
@Table(name = "vl_dependency")
public class Dependency implements Serializable {

	private static final long serialVersionUID = 2451840393099386141L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "pg-uuid")
	private UUID internal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@NotBlank(message = "{dependency.name.blank}")
	@Size(min = 1, max = 200)
	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private DependencyType type;

	@NotBlank(message = "{dependency.version.blank}")
	@Column(name = "version")
	private String version;

	@NotBlank(message = "{dependency.hostname.blank}")
	@Column(name = "hostname")
	private String hostname;

	@Column(name = "complement")
	private String complement;

	@ManyToMany
	@JoinTable(name = "vl_product_dependency", joinColumns = {
			@JoinColumn(name = "dependency_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private List<Product> products;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DependencyType getType() {
		return type;
	}

	public void setType(DependencyType type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@PrePersist
	protected void onInsert() {
		this.internal = UUID.randomUUID();
		this.created = new Date();
	}

}
