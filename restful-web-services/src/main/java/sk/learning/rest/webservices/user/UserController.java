package sk.learning.rest.webservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	private UserDaoService daoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = daoService.findOne(id);
		
		if(user == null)
			throw new UserNotFoundException("id - " + id);	
		
		Resource<User> resource = new Resource<User>(user);
		
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = daoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public User DeleteUser(@PathVariable int id) {
		User user = daoService.deleteById(id);
		
		if(user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}
}
