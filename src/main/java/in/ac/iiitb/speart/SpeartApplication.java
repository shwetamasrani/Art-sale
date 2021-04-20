package in.ac.iiitb.speart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SpeartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeartApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.
                basePackage("in.ac.iiitb.speart.controller")).build();
    }
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Art Sale APIs",
                "Spring Boot REST API for Selling and Bidding Art.",
                "1.0",
                "Terms of service",
                "Kanchan And Shweta", "https://art_sale", "kanchanshweta@art_Sale");
        return apiInfo;
    }
}
