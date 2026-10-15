package car.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationBeanContext.xml");


    MyBean myBean = (MyBean) context.getBean("myBean");
    System.out.println(myBean);
    }
}
