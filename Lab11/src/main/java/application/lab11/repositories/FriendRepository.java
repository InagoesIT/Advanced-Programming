package application.lab11.repositories;

import application.lab11.entities.Friend;
import application.lab11.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>, JpaSpecificationExecutor<Friend>
{
    @Query("select count(friend.id) > 0 from Friend friend where (friend.person1=?1 and friend.person2=?2) or (friend.person2=?1 and friend.person1=?2)")
    Boolean areFriends(Person person1, Person person2);

    @Query("select DISTINCT friend.person1 from Friend friend")
    List<Person> getPersons1();

    @Query("select DISTINCT friend.person2 from Friend friend")
    List<Person> getPersons2();

    @Query("select friend.person2 from Friend friend where friend.person1=?1")
    List<Person> getFriends1(Person person);

    @Query("select friend.person1 from Friend friend where friend.person2=?1")
    List<Person> getFriends2(Person person);
}

