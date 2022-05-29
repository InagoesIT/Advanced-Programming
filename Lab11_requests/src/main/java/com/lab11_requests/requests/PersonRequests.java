package com.lab11_requests.requests;

import com.lab11_requests.entities.Person;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

public class PersonRequests implements Requests {
    private final RestTemplate restTemplate;
    private final static String URL = "http://localhost:8090/persons";

    public PersonRequests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute() {
        System.out.println("~~~~~GET all the persons:~~~~~");
        Person[] persons = getAllPersons();
        Arrays.stream(persons).forEach(System.out::println);
        System.out.println();
        System.out.println();

        System.out.println("~~~~~POST a person with name \"maxy\":~~~~~");
        System.out.println("MESSAGE: " + createPerson(new Person(0, "maxy")));
        System.out.println();
        System.out.println("~~~~~POST a person with name \"inette\":~~~~~");
        System.out.println("MESSAGE: " + createPerson(new Person(0, "inette")));
        System.out.println();
        System.out.println();

        System.out.println("~~~~~DELETE the person with id 9:~~~~~");
        System.out.println("MESSAGE: " + deletePerson(9));
        System.out.println();
        System.out.println("~~~~~DELETE the person with id 1:~~~~~");
        System.out.println("MESSAGE: " + deletePerson(1));
        System.out.println();
        System.out.println();

        System.out.println("~~~~~PUT the person's name to \"mimi\" for id = 10:~~~~~");
        System.out.println("MESSAGE: " + putPerson(new Person(10, "mimi")));
        System.out.println();
        System.out.println("~~~~~PUT the person's name to \"alex\" for id = 1:~~~~~");
        System.out.println("MESSAGE: " + putPerson(new Person(1, "alex")));
        System.out.println();
        System.out.println();

        System.out.println("~~~~~GET the most popular 3 persons:~~~~~");
        Arrays.stream(getPopularPersons(3)).forEach(System.out::println);
        System.out.println();
        System.out.println("~~~~~GET the most popular 0 persons:~~~~~");
        Arrays.stream(getPopularPersons(0)).forEach(System.out::println);
        System.out.println();
        System.out.println();
    }

    public Person[] getAllPersons()
    {
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<Person[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Person[].class);

        return response.getBody();
    }

    public String createPerson(Person person)
    {
        HttpEntity<Person> entity = new HttpEntity<>(person);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    public String deletePerson(int id)
    {
        HttpEntity<Person> entity = new HttpEntity<>(null);
        String url = URL + "/" + id;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

        return response.getBody();
    }

    public String putPerson(Person person)
    {
        HttpEntity<Person> entity = new HttpEntity<>(null);
        String url = URL + "/" + person.getId();
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", person.getName())
                .encode()
                .toUriString();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.PUT, entity, String.class);

        return response.getBody();
    }

    public Person[] getPopularPersons(int nr)
    {
        HttpEntity<Person> entity = new HttpEntity<>(null);
        String url = URL + "/popular";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nr", nr)
                .encode()
                .toUriString();
        ResponseEntity<Person[]> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Person[].class);

        return response.getBody();
    }
}
