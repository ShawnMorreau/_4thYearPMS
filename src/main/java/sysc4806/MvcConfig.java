package sysc4806;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/events").setViewName("event");
        registry.addViewController("/deadlines").setViewName("deadline");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/project").setViewName("index");
        registry.addViewController("/").setViewName("home");

    }
}
