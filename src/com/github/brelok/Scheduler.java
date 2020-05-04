package com.github.brelok;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Scheduler {

    public static void main(String[] args) {


        try {
            org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail job = JobBuilder.newJob(Job.class)
                    .withIdentity("job", "group1")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("job", "group1")
                    .startNow()
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInMinutes(5)
                                    .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);


        } catch (
                SchedulerException e) {
            e.printStackTrace();
        }
    }

}
