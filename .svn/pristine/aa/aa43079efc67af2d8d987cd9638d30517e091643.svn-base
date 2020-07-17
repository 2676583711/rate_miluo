package com.rate.system.rate_system.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.rate.system.rate_system.entity.Response;

@Component
public class WelcomeJob implements Job{

	@Autowired
	SimpMessagingTemplate template;
	
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
//    	System.out.println("定时器执行了.........................................");
    	template.convertAndSend("/topic/getResponse", new Response("株洲站出现故障，请运维处理" ));
    }

}