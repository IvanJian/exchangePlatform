package cn.edu.jmu.jyf.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		SpringContextUtil.applicationContext = applicationContext;
	}
	
	  public static ApplicationContext getApplicationContext() {
          return applicationContext;
   }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
               return (T) applicationContext.getBean(name);
     }


}
