package mainApp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Spring4Hibernate4Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        IUserDao udao = ctx.getBean(IUserDao.class);
        udao.saveUser();
        System.out.println("Done");
    }
}