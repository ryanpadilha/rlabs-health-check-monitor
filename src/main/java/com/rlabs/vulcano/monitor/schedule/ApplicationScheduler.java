package com.rlabs.vulcano.monitor.schedule;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * The Application Scheduler.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Configuration
public class ApplicationScheduler {

	private static final String QUARTZ_PROPERTIES = "quartz.properties";
	private static final String JOB_NAME = "health.check.api";
	private static final String JOB_DESCRIPTION = "Invoke health check api service";
	private static final boolean JOB_DURABILITY = true;
	private static final int JOB_FREQUENCY_SECS = 60;
	private static final String TRIGGER_NAME = "trigger.health.check";

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail jobDetail) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource(QUARTZ_PROPERTIES));
		schedulerFactory.setJobFactory(springBeanJobFactory());
		schedulerFactory.setJobDetails(jobDetail);
		schedulerFactory.setTriggers(trigger);
		return schedulerFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(ApplicationHealthJob.class);
		jobDetailFactoryBean.setName(JOB_NAME);
		jobDetailFactoryBean.setDescription(JOB_DESCRIPTION);
		jobDetailFactoryBean.setDurability(JOB_DURABILITY);
		return jobDetailFactoryBean;
	}

	@Bean
	public SimpleTriggerFactoryBean trigger(JobDetail jobDetail) {
		SimpleTriggerFactoryBean triggerFactoryBean = new SimpleTriggerFactoryBean();
		triggerFactoryBean.setJobDetail(jobDetail);
		triggerFactoryBean.setRepeatInterval(JOB_FREQUENCY_SECS * 1000);
		triggerFactoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		triggerFactoryBean.setName(TRIGGER_NAME);
		return triggerFactoryBean;
	}
}
