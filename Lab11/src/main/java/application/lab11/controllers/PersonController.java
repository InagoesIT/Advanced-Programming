package application.lab11.controllers;

import application.lab11.entities.Person;
import application.lab11.repositories.PersonRepository;
import application.lab11.services.PopularPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/persons")
public class PersonController
{
	@Autowired
	PersonRepository personRepository;

	@Autowired
	PopularPersonService popularPersonService;

	@GetMapping
	public ResponseEntity<List<Person>> getPersons()
	{
		return ResponseEntity.ok(personRepository.findAll());
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> createPerson(@RequestBody Person person)
	{
		if (personRepository.findByName(person.getName()).isPresent())
			return new ResponseEntity<>(
					"Person not created", HttpStatus.CONFLICT);

		personRepository.saveAndFlush(person);
		return new ResponseEntity<>(
				"Person created successfully", HttpStatus.CREATED);
	}

	@GetMapping(path = "/popular", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Person>> getPopularPersons(@RequestParam Integer nr)
	{
		return ResponseEntity.ok(popularPersonService.getPopularPersons(nr));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<String> updatePerson(@PathVariable Integer id, @RequestParam String name)
	{
		if (personRepository.findByName(name).isPresent())
			return new ResponseEntity<>(
					"Name already taken", HttpStatus.CONFLICT);

		Optional<Person> person = personRepository.findById(id);
		if (person.isEmpty()) //create new Person
			return createPerson(new Person(id, name));

		person.get().setName(name);
		personRepository.saveAndFlush(person.get());
		return new ResponseEntity<>(
				"Person's name updated", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable int id)
	{
		Optional<Person> person = personRepository.findById(id);
		if (person.isEmpty())
			return new ResponseEntity<>(
					"Person not found", HttpStatus.NOT_FOUND);

		personRepository.delete(person.get());
		return new ResponseEntity<>("Person removed", HttpStatus.OK);
	}
}