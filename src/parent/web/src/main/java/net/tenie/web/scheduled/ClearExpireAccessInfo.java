package net.tenie.web.scheduled;
 
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component; 
import net.tenie.web.service.*;
@Component  
@EnableScheduling	//启用定时任务
public class ClearExpireAccessInfo {
	Logger logger = LoggerFactory.getLogger(ClearExpireAccessInfo.class); 
    /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）  
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)  
     * 注意： 30 * * * * * 表示每分钟的第30秒执行，而（*斜杠30）表示每30秒执行 
     *  
     * */  
   // @Scheduled(cron="*/30 * * * * *")  
    public void firstTask(){   
    	logger.info("clear session ...");
    	logger.info(CecheResult.sessionMapToString()); 
    	CecheResult.rmSeeion(null);  
          
    }  
    
    
    // spring 计划任务依赖这个bean , 要不然Spring 会在日志中输出error(虽然程序程序不会奔溃)
    @Bean
    public TaskScheduler scheduledExecutorService() {
     ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler(); 
     scheduler.setPoolSize(8); 
     scheduler.setThreadNamePrefix("scheduled-thread-"); 
     return scheduler; 
     }
}
