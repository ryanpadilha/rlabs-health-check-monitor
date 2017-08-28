package com.rlabs.vulcano.monitor.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rlabs.vulcano.monitor.service.ApplicationHealthService;

/**
 * The Application Health Job.<br>
 * Performe internal service-tasks.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Component
public class ApplicationHealthJob implements Job {

	@Autowired
	private ApplicationHealthService service;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		service.executeHealthCheck();
	}

}
