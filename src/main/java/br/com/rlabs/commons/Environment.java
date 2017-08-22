package br.com.rlabs.commons;

/**
 * Environment Enumeration.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public enum Environment {

	DEVELOPMENT("development"), STAGE("stage"), PRODUCTION("production");

	private String description;

	Environment(String value) {
		this.description = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
