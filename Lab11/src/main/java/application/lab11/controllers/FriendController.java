package application.lab11.controllers;

import application.lab11.entities.Friend;
import application.lab11.entities.Person;
import application.lab11.repositories.FriendRepository;
import application.lab11.repositories.PersonRepository;
import application.lab11.services.PopularPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/friends")
public class FriendController
{
    @Autowired
    FriendRepository friendRepository;

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Friend>> getFriends()
    {
        return ResponseEntity.ok(friendRepository.findAll());
    }

    @GetMapping(path = "/relation")
    public ResponseEntity<String> findIfFriends(@RequestParam Integer person1, @RequestParam Integer person2)
    {
        if (friendRepository.areFriends(personRepository.getById(person1), personRepository.getById(person2)))
            return new ResponseEntity<>(
                    "They are friends.", HttpStatus.FOUND);

        return new ResponseEntity<>("They are not friends.", HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/for")
    public ResponseEntity<List<Person>> getFriendsFor(@RequestParam Integer id)
    {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty())
            return new ResponseEntity<>(
                    Collections.emptyList(), HttpStatus.NOT_FOUND);

        List<Person> persons = friendRepository.getFriends1(person.get());
        persons.addAll(friendRepository.getFriends2(person.get()));
            return new ResponseEntity<>(
                    persons, HttpStatus.FOUND);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Friend> getFriendRelationship(@PathVariable("id")  Integer id)
    {
        Optional<Friend> friend = friendRepository.findById(id);
        return friend.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createFriend(@RequestBody Friend friend)
    {
        if (personRepository.findById(friend.getPerson1().getId()).isEmpty()
            || personRepository.findById(friend.getPerson2().getId()).isEmpty())
            return new ResponseEntity<>(
                    "One or more persons with this name do not exist.", HttpStatus.NOT_FOUND);

        if (friendRepository.areFriends(friend.getPerson1(), friend.getPerson2()))
            return new ResponseEntity<>(
                    "These persons are already friends.", HttpStatus.CONFLICT);

        friendRepository.saveAndFlush(friend);
        return new ResponseEntity<>(
                "Friendship relation created successfully.", HttpStatus.CREATED);
    }
}
