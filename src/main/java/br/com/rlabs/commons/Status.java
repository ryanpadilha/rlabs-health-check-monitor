package br.com.rlabs.commons;

/**
 * Status of the service.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
public enum Status {

	UNKNOWN("UNKNOWN"), UP("UP"), DOWN("DOWN"), OUT_OF_SERVICE("OUT_OF_SERVICE");

	private String description;

	Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
