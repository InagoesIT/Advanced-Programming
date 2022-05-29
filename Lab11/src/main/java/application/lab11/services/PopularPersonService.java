package application.lab11.services;

import application.lab11.entities.Person;
import application.lab11.repositories.FriendRepository;
import application.lab11.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PopularPersonService
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FriendRepository friendRepository;

    private static Map<Person, Integer> sortByValue(Map<Person, Integer> map)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Person, Integer>> list = new LinkedList<>(map.entrySet());

        // Sort the list
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        // put data from sorted list to hashmap
        HashMap<Person, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Person, Integer> sortedMap : list)
            result.put(sortedMap.getKey(), sortedMap.getValue());

        return result;
    }

    public List<Person> getPopularPersons(Integer nr)
    {
        if (nr <= 0)
            return Collections.emptyList();

        List<Person> persons = friendRepository.getPersons1();
        persons.addAll(friendRepository.getPersons2());
        Map<Person, Integer> frequencies = new HashMap<>();

        for (Person person : persons)
            frequencies.put(person, Collections.frequency(persons, person));

        return sortByValue(frequencies).keySet().stream().limit(nr).toList();
    }
}