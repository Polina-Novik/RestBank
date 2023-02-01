package by.novik.restexample.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition //на сайте шапку изм
public class SwaggerConfig {
    @Bean
    public OpenAPI getOpenAPIDefinition() {
        return new OpenAPI().info(new Info()
                .summary("my animals REST project")
                .description("This is my super animals project")
                .title("REST Animals app").version("1.0.0")
                .license(new License().name("sadfghj"))
                .contact(new Contact()
                        .name("Polya")
                        .email("aa")));
    }

    @Bean
    public GroupedOpenApi getSecondControllerApi() { //позволяет группировать эндпоинты по какому-либо признаку. делаем по вкладкам
        return GroupedOpenApi.builder()
                .group("second controller group")
                .displayName("second controller group")
                .pathsToMatch("/animals2/**") /*все что начинается с анималс два ааа попадает в группу*/
                .build();
    }
    @Bean
    public GroupedOpenApi getThirdControllerApi() {
        return GroupedOpenApi.builder()
                .group("third controller group")
                .displayName("third controller group")
                .pathsToMatch("/animals3/**","animals/**")
                .build();
    }
}
