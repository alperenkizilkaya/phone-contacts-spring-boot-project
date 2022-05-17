package com.contacts.repository;

import com.contacts.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts,Long> {

    Optional<Contacts> findByNameAndAndLastName(@NotNull String name,String lastName);

    List<Contacts> findByName(String name);

}
