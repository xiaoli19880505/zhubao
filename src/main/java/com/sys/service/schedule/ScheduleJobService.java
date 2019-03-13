package com.sys.service.schedule;

import com.sys.service.MessageService;
import com.sys.service.apply.ApproveService;
import com.sys.service.common.MessageAndFormService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


//import com.sms.factory.QuartzJobFactory;
//import com.sms.model.ScheduleJobDomain;
//import com.sms.service.CustomerService;
//import com.sms.service.JkpzService;
//import com.sms.service.ScheduleJobService;
//import com.sms.service.SenddxService;
//import com.sms.util.MIMSJob;

@Service
public class ScheduleJobService
{

	@Autowired
	private Scheduler scheduler;

	/*@Autowired private SenddxService senddxService;
	@Autowired private JkpzService jkpzService;;*/
	@Autowired
	private MessageAndFormService messageAndFormService;

	@Autowired
	private ApproveService approveService;

	@Autowired
	private MessageService messageService;


	public List<ScheduleJobDomain> getPlanJobs() throws SchedulerException
	{
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJobDomain> jobList = new ArrayList<ScheduleJobDomain>();
		for (JobKey jobKey : jobKeys)
		{
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers)
			{
				ScheduleJobDomain job = (ScheduleJobDomain) trigger.getJobDataMap().get("scheduleJob");
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if(trigger instanceof CronTrigger)
				{
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setQuartz(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	public List<ScheduleJobDomain> getRunningJobs() throws SchedulerException
	{
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJobDomain> jobList = new ArrayList<ScheduleJobDomain>();
		for (JobExecutionContext executingJob : executingJobs)
		{
			Trigger trigger = executingJob.getTrigger();
			ScheduleJobDomain job = (ScheduleJobDomain) trigger.getJobDataMap().get("scheduleJob");
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if(trigger instanceof CronTrigger)
			{
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setQuartz(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}


	public void pauseJob(ScheduleJobDomain scheduleJob) throws SchedulerException
	{
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}


	public void resumeJob(ScheduleJobDomain scheduleJob) throws SchedulerException
	{
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}


	public void deleteJob(ScheduleJobDomain scheduleJob) throws SchedulerException
	{
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}

	public void runOnce(ScheduleJobDomain scheduleJob) throws SchedulerException
	{
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	public void updateExpression(ScheduleJobDomain scheduleJob, String expression) throws SchedulerException
	{
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	public void addJob(ScheduleJobDomain scheduleJob, Map<String, Object> conditionMap) throws SchedulerException
	{
		TriggerKey key = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		Trigger trigger = scheduler.getTrigger(key);
		if(trigger == null)
		{
			//在创建任务时如果不存在新建一个
			JobDetail jobDetail = JobBuilder.newJob(MIMSJob.class)
					.withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
			jobDetail.getJobDataMap().put("conditionMap", conditionMap);
			jobDetail.getJobDataMap().put("messageAndFormService", messageAndFormService);
			jobDetail.getJobDataMap().put("approveService", approveService);
			jobDetail.getJobDataMap().put("messageService", messageService);
			//jobDetail.getJobDataMap().put("jkpzService", jkpzService);
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getQuartz());
			//按新的cronExpression表达式构建一个新的trigger
			trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
					.withSchedule(scheduleBuilder).build();
			trigger.getJobDataMap().put("scheduleJob", scheduleJob);
			scheduler.scheduleJob(jobDetail, trigger);
		}
		else
		{
			// Trigger已存在，那么更新相应的定时设置
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getQuartz());
			trigger = TriggerBuilder.newTrigger().withIdentity(key).withSchedule(scheduleBuilder).build();
			trigger.getJobDataMap().put("scheduleJob", scheduleJob);
			//重新执行
			scheduler.rescheduleJob(key, trigger);
		}
	}
}