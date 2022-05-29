package com.lab11_requests.requests;

import com.lab11_requests.entities.Friend;
import com.lab11_requests.entities.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

public class FriendRequests implements Requests {
    private final RestTemplate restTemplate;
    private static final String URL = "http://localhost:8090/friends";

    public FriendRequests(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute() {
        System.out.println("~~~~~GET all the friends:~~~~~");
        Friend[] friends = getAllFriends();
        Arrays.stream(friends).forEach(System.out::println);
        System.out.println();
        System.out.println();

        System.out.println("~~~~~find if 2 persons are friends:~~~~~");
        System.out.println("~~~~~person with id 2 and person with id 5:~~~~~");
        System.out.println("MESSAGE: " + findIfFriends(2, 5));
        System.out.println();
        System.out.println("~~~~~person with id 3 and person with id 0:~~~~~");
        System.out.println("MESSAGE: " + findIfFriends(3, 0));
        System.out.println();
        System.out.println();

        System.out.println("~~~~~GET the friend relationship with id 11:~~~~~");
        System.out.println(getFriend(11));
        System.out.println();
        System.out.println("~~~~~GET the friend relationship with id 0:~~~~~");
        System.out.println(getFriend(0));
        System.out.println();
        System.out.println();

        System.out.println("~~~~~POST a friend relationship between person with id 3 and 5:~~~~~");
        Friend friend = new Friend();
        friend.setPerson1(new Person(3, ""));
        friend.setPerson2(new Person(5, ""));
        System.out.println(createFriend(friend));
        System.out.println();
        System.out.println("~~~~~POST a friend relationship between person with id 5 and 3:~~~~~");
        friend = new Friend();
        friend.setPerson1(new Person(5, ""));
        friend.setPerson2(new Person(3, ""));
        System.out.println(createFriend(friend));
        System.out.println();
        System.out.println();

        System.out.println("~~~~~GET friends for the person with the id 2:~~~~~");
        System.out.println(Arrays.toString(getFriendsFor(2)));
        System.out.println();
        System.out.println("~~~~~GET friends for the person with the id 0:~~~~~");
        System.out.println(Arrays.toString(getFriendsFor(0)));
        System.out.println();
        System.out.println();
    }

    public Friend[] getAllFriends()
    {
        HttpEntity<String> entity = new HttpEntity<>(null);
        ResponseEntity<Friend[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Friend[].class);

        return response.getBody();
    }

    public String findIfFriends(int person1, int person2)
    {
        HttpEntity<String> entity = new HttpEntity<>(null);
        String url = URL + "/relation" ;
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("person1", person1)
                .queryParam("person2", person2)
                .encode()
                .toUriString();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    public Friend getFriend(int id)
    {
        HttpEntity<Friend> entity = new HttpEntity<>(null);
        String url = URL + "/" + id;
        ResponseEntity<Friend> response = restTemplate.exchange(url, HttpMethod.GET, entity, Friend.class);

        return response.getBody();
    }

    public Person[] getFriendsFor(int id)
    {
        HttpEntity<String> entity = new HttpEntity<>(null);
        String url = URL + "/for" ;
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("id", id)
                .encode()
                .toUriString();
        ResponseEntity<Person[]> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Person[].class);

        return response.getBody();
    }

    public String createFriend(Friend friend)
    {
        HttpEntity<Friend> entity = new HttpEntity<>(friend);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}
