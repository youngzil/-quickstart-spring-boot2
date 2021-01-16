package com.quickstart.springboot.swagger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/12/14 23:04
 */
@Component
class CustomAnnotationDemo implements ApplicationListener<ContextRefreshedEvent> {

  public static List<Invoker> invokerList = new ArrayList<>();

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    // 根容器为Spring容器
    if(event.getApplicationContext().getParent()==null){
      Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(OnsConsumer.class);
      for(Object bean : beans.values()){
        OnsConsumer ca = bean.getClass().getAnnotation(OnsConsumer.class);
        System.out.println(bean.getClass().getName()+"==="+ca.value());
        Method[] methods = bean.getClass().getMethods();
        for (Method declaredMethod : methods) {
          System.out.println(declaredMethod.getName());
          ConsumerMapping ma = AnnotationUtils.findAnnotation(declaredMethod, ConsumerMapping.class);
          if(ma != null){
            invokerList.add(new Invoker(declaredMethod,ma.value(),bean));
            System.out.println(bean.getClass().getName()+"==="+ca.value()+"==="+ma.value());
          }
        }
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
          System.out.println(declaredMethod.getName());
          ConsumerMapping ma = declaredMethod.getAnnotation(ConsumerMapping.class);
          if(ma != null){
            System.out.println(bean.getClass().getName()+"==="+ca.value()+"==="+ma.value());
          }
        }
      }
      System.err.println("=====onApplicationEvent====="+event.getSource().getClass().getName());
    }
    invoke();
  }

  private void invoke() {
    for (Invoker invoker : invokerList) {
      try {
        invoker.getMethod().invoke(invoker.getBean(),invoker.getTag());
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }

  @Data
  class Invoker{
    private Method method;
    private String tag;
    private Object bean;

    public Invoker(Method method, String tag, Object bean) {
      this.method = method;
      this.tag = tag;
      this.bean = bean;
    }
  }

}
