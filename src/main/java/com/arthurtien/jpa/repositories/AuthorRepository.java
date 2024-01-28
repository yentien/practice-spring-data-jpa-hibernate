package com.arthurtien.jpa.repositories;

import com.arthurtien.jpa.models.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AuthorRepository extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {

    @Transactional
    List<Author> findByNamedQuery(@Param("age") int age);

    @Modifying
    @Transactional
    void updateByNamedQuery(@Param("age") int age);

    // update Author a set a.age = 22 where a.id = 1
    @Modifying
    @Transactional
    @Query("update Author a set a.age = :age where a.id = :id")
    int updateAuthor(int age, int id);

    @Modifying
    @Transactional
    @Query("update Author a set a.age = :age")
    int updateAllAuthorsAges(int age);

    // select * from Author where first_name = 'ali'
    List<Author> findAllByFirstName(String firstName);

    // select * from Author where first_name = 'al'
    List<Author> findAllByFirstNameIgnoreCase(String firstName);

    // select * from Author where first_name = '%al%'
    List<Author> findAllByFirstNameContainingIgnoreCase(String firstName);

    // select * from Author where first_name = 'al%'
    List<Author> findAllByFirstNameStartsWithIgnoreCase(String firstName);

    // select * from Author where first_name = '%al'
    List<Author> findAllByFirstNameEndsWithIgnoreCase(String firstName);

    // select * from Author where first_name in ('ali', 'bou', 'coding')
    List<Author> findAllByFirstNameInIgnoreCase(List<String> firstName);
}

