package by.novik.restbank.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI getOpenAPIDefinition() {
        return new OpenAPI().info(new Info()
                .summary("my internet-banking REST project")
                .description("This project imitates the work of a bank. It is possible to get information about the client, " +
                        "about the card, pay by card and transfer money from one card to another")
                .title("REST Bank app").version("1.0.0")
                .contact(new Contact()
                        .name("Polina")
                        .email("abc@gmail.com")));
    }


}
