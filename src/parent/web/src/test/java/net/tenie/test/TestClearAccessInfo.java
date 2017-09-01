package net.tenie.test;
 

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import net.tenie.web.entity.AccessInfo;
import net.tenie.web.service.CecheResult;

public class TestClearAccessInfo {
	
	@Test
	public void test() throws InterruptedException {
		AccessInfo info = new AccessInfo();
		info.setDate(new Date());
		
		Calendar calendar = Calendar.getInstance();
		Thread.sleep(3000);
		AccessInfo info2 = new AccessInfo();
		info2.setDate(new Date());
		
		CecheResult.addSession("f1", info);
		CecheResult.addSession("f2", info2);
		CecheResult.rmSeeion(calendar);
		
		System.out.println(CecheResult.getSessionMap());
		
	}

}
