package pl.sda.bootcamp.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/kurs/lista");
        registry.addViewController("/panel-klienta").setViewName("panels/studentpanel");
        registry.addViewController("/panel-trenera").setViewName("panels/teacherpanel");
        registry.addViewController("/login").setViewName("panels/loginpage");
    }
}
