package com.rlabs.vulcano.monitor.schedule;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.rlabs.vulcano.monitor.commons.Constants;

/**
 * The Application Scheduler.<br>
 * This is an implementation of Spring-Quartz.
 *
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.0.1
 *
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class ApplicationScheduler {

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
		schedulerFactory.setConfigLocation(new ClassPathResource(Constants.QUARTZ_PROPERTIES));
		schedulerFactory.setJobFactory(springBeanJobFactory());
		schedulerFactory.setJobDetails(jobDetail);
		schedulerFactory.setTriggers(trigger);
		return schedulerFactory;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(ApplicationHealthJob.class);
		jobDetailFactoryBean.setName(Constants.JOB_NAME);
		jobDetailFactoryBean.setDescription(Constants.JOB_DESCRIPTION);
		jobDetailFactoryBean.setDurability(Constants.JOB_DURABILITY);
		return jobDetailFactoryBean;
	}

	@Bean
	public SimpleTriggerFactoryBean trigger(JobDetail jobDetail) {
		SimpleTriggerFactoryBean triggerFactoryBean = new SimpleTriggerFactoryBean();
		triggerFactoryBean.setJobDetail(jobDetail);
		triggerFactoryBean.setRepeatInterval(Constants.JOB_FREQUENCY_SECS * 1000);
		triggerFactoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		triggerFactoryBean.setName(Constants.TRIGGER_NAME);
		return triggerFactoryBean;
	}
}
