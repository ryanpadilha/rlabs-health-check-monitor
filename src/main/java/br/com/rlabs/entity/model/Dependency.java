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

	@Column(name = "name")
	private String name;

}
