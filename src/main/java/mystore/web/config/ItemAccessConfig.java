package mystore.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import mystore.web.interceptor.ItemAccessInterceptor;
/*Indicates that a class declares one or more @Bean methods andmay be processed by the Spring container to generate bean definitions
 *  andservice requests for those beans at runtime, for example: */
@Configuration
public class ItemAccessConfig  implements WebMvcConfigurer{
	
	/*@WebMvc Configurer
	 * Defines callback methods to customize the Java-based configuration forSpring MVC enabled via @EnableWebMvc. 
	 * */
	@Autowired
    private ItemAccessInterceptor Interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) Interceptor)
          .addPathPatterns("/item/**")
          ;
    }
}






