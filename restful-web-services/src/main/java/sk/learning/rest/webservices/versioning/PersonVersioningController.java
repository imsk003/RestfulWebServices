package sk.learning.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("SK");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("SK", "T"));
	}
	
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("SK");
	}
	
	@GetMapping(value="person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("SK", "T"));
	}
	
	@GetMapping(value="person/header", headers="Api-Version=1")
	public PersonV1 headerV1() {
		return new PersonV1("SK");
	}
	
	@GetMapping(value="person/header", headers="Api-Version=2")
	public PersonV2 header() {
		return new PersonV2(new Name("SK", "T"));
	}
}
