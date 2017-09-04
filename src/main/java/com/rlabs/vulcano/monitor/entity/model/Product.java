package com.rlabs.vulcano.monitor.entity.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.rlabs.vulcano.monitor.commons.Environment;

/**
 * Product class.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Entity
@Table(name = "vl_product")
public class Product implements Serializable {

	private static final long serialVersionUID = -4127444152817993771L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "pg-uuid")
	private UUID internal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "active")
	private boolean active = true;

	@NotBlank(message = "{product.name.blank}")
	@Size(min = 1, max = 200)
	@Column(name = "name")
	private String name;

	@NotBlank(message = "{product.description.blank}")
	@Size(min = 10, max = 500)
	@Column(name = "description")
	private String description;

	@NotBlank(message = "{product.artifactid.blank}")
	@Size(min = 5, max = 100)
	@Column(name = "artifact_id")
	private String artifactId;

	@Enumerated(EnumType.STRING)
	@Column(name = "environment")
	private Environment environment;

	@NotBlank(message = "{product.version.blank}")
	@Column(name = "version")
	private String version;

	@NotBlank(message = "{product.hostname.blank}")
	@Column(name = "hostname")
	private String hostname;

	@Column(name = "endpoint_health")
	private String endpointHealth;

	@Column(name = "endpoint_property")
	private String endpointProperty;

	@Column(name = "endpoint_environment")
	private String endpointEnvironment;

	@NotBlank(message = "{product.project.repository.blank}")
	@Column(name = "project_repository")
	private String projectRepository;

	@Column(name = "project_page")
	private String projectPage;

	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
	private List<Dependency> dependencies;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_status_timestamp")
	private Date lastStatusTimestamp;

	@Transient
	private String statusColor = "bg-green";

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
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

	public String getEndpointHealth() {
		return endpointHealth;
	}

	public void setEndpointHealth(String endpointHealth) {
		this.endpointHealth = endpointHealth;
	}

	public String getEndpointProperty() {
		return endpointProperty;
	}

	public void setEndpointProperty(String endpointProperty) {
		this.endpointProperty = endpointProperty;
	}

	public String getEndpointEnvironment() {
		return endpointEnvironment;
	}

	public void setEndpointEnvironment(String endpointEnvironment) {
		this.endpointEnvironment = endpointEnvironment;
	}

	public String getProjectRepository() {
		return projectRepository;
	}

	public void setProjectRepository(String projectRepository) {
		this.projectRepository = projectRepository;
	}

	public String getProjectPage() {
		return projectPage;
	}

	public void setProjectPage(String projectPage) {
		this.projectPage = projectPage;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	public Date getLastStatusTimestamp() {
		return lastStatusTimestamp;
	}

	public void setLastStatusTimestamp(Date lastStatusTimestamp) {
		this.lastStatusTimestamp = lastStatusTimestamp;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	@PrePersist
	protected void onInsert() {
		this.internal = UUID.randomUUID();
		this.created = new Date();
	}

	@Override
	public String toString() {
		return name;
	}

}
