package br.com.rlabs.entity.model;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import br.com.rlabs.commons.Environment;

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

	@Enumerated(EnumType.STRING)
	@Column(name = "environment")
	private Environment environment;

	@NotBlank(message = "{product.version.blank}")
	@Column(name = "version")
	private String version;

	@NotBlank(message = "{product.hostname.blank}")
	@Column(name = "hostname")
	private String hostname;

	@NotBlank(message = "{product.project.repository.blank}")
	@Column(name = "project_repository")
	private String projectRepository;

	@Column(name = "project_page")
	private String projectPage;

	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	private List<Dependency> dependencies;

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
