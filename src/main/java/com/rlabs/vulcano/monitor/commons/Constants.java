package com.rlabs.vulcano.monitor.commons;

public class Constants {

	// TODO refactoring messages on right-place
	public static final String INVALID_FIELDS = "Campos inválidos. Por favor verifique.";
	public static final String PERSIST = "Registro salvo com sucesso.";
	public static final String DELETE = "Registro deletado com sucesso.";
	public static final String RECORD_ACTIVE = "Cliente com acesso (não) liberado ao Vulcano Monitor.";

	/**
	 * Scheduler properties
	 */
	public static final String QUARTZ_PROPERTIES = "quartz.properties";
	public static final String JOB_NAME = "health.check.api";
	public static final String JOB_DESCRIPTION = "Invoke health check api service";
	public static final boolean JOB_DURABILITY = true;
	public static final int JOB_FREQUENCY_SECS = 60;
	public static final String TRIGGER_NAME = "trigger.health.check";
	public static final int REQUEST_TIMEOUT = 6000;

	/**
	 * Status health
	 */
	public static final int STATUS_ALERT_DOWN = 4;
	public static final int STATUS_ALERT_OUT = 2;

}
