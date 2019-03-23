package sk.learning.rest.webservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "/hello-world")
	public String SayHelloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean SayHelloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/hello-world-bean/{name}")
	public HelloWorldBean SayHelloBeanVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello " + name);
	}
	
	@GetMapping(path = "/hello-world-Internationalization")
	public HelloWorldBean SayHelloWorldInternationalization() {
		return new HelloWorldBean(messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale()));
	}
}
