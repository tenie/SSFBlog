package net.tenie.myblog.AppBoot;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

/**
 * web 启动和结算分别调用的方法
 * @author tenie
 *
 */
@Service
public class WebStartAndEnd {


/**

* 在web启动时执行

*/

@PostConstruct

public void applicationStart(){

	System.out.println("application start");

}

 

/**

* 在web结束时执行

*/

@PreDestroy

public void applicationEnd(){

	System.out.println("InskyScheduleCenter application end");

 

}


	
}
