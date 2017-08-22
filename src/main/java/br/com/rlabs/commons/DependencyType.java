package br.com.rlabs.commons;

/**
 * Dependency Type.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public enum DependencyType {

	INTERNAL("internal"), EXTERNAL("external");

	private String description;

	DependencyType(String value) {
		this.description = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
