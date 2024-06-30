package Polyaeva.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationInitializer implements WebApplicationInitializer {
    private static final String BASE_PACKAGE = "ru.netology";
    private static final String SERVLET_NAME = "app";
    private static final String DELIMITER = "/";
    @Override
    public void onStartup(ServletContext servletContext) {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan(BASE_PACKAGE);
        context.refresh();

        final var servlet = new DispatcherServlet(context);
        final var registration = servletContext.addServlet(SERVLET_NAME, servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping(DELIMITER);
    }
}
