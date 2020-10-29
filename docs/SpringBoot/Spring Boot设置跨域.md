https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
https://spring.io/guides/gs/rest-service-cors/
https://blog.csdn.net/qq_30770095/article/details/80237027
https://blog.csdn.net/w_linux/article/details/81142413
https://www.jianshu.com/p/55643abe7a18
https://blog.csdn.net/qq_30770095/article/details/80237027

@RestController
@RequestMapping("/account")
public class AccountController {

	@CrossOrigin
	@GetMapping("/{id}")
	public Account retrieve(@PathVariable Long id) {
		// ...
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		// ...
	}
}

@CrossOrigin(origins = "http://domain2.com", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

	@GetMapping("/{id}")
	public Account retrieve(@PathVariable Long id) {
		// ...
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		// ...
	}
}

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

	@CrossOrigin(origins = "http://domain2.com")
	@GetMapping("/{id}")
	public Account retrieve(@PathVariable Long id) {
		// ...
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		// ...
	}
}

If you are using Spring Security, make sure to enable CORS at Spring Security level as well to allow it to leverage the configuration defined at Spring MVC level.

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()...
	}
}



@Configuration
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
}

@Configuration  
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{  
  
    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
           registry.addMapping("/api/**")  
           .allowedOrigins("http://192.168.1.97")  
           .allowedMethods("GET", "POST")  
           .allowCredentials(false).maxAge(3600);  
    }





