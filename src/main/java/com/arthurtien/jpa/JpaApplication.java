package com.arthurtien.jpa;

import com.arthurtien.jpa.models.Author;
import com.arthurtien.jpa.models.Video;
import com.arthurtien.jpa.repositories.AuthorRepository;
import com.arthurtien.jpa.repositories.VideoRepository;
import com.arthurtien.jpa.specification.AuthorSpecification;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthorRepository authorRepository,
            VideoRepository videoRepository
    ) {
        return args -> {
            for (int i = 0; i < 50; i++) {
                Faker faker = new Faker();
                var author = Author.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .age(faker.number().numberBetween(20, 80))
                        .email(faker.name().username() + "@gmail.com")
                        .build();
                authorRepository.save(author);
            }
//            var author = Author.builder()
//                    .id(1)
//                    .firstName("Arthur")
//                    .lastName("tien")
//                    .age(24)
//                    .email("arthurtien@gmail.com")
//                    .build();
            // authorRepository.save(author);

            // update Author a set a.age = 22 where a.id = 1
//            authorRepository.updateAuthor(22, 1);
//            authorRepository.updateAllAuthorsAges(100);

//            authorRepository.findByNamedQuery(60)
//                    .forEach(System.out::println);
//            authorRepository.updateByNamedQuery(12);
            Specification<Author> spec = Specification
                    .where(AuthorSpecification.hasAge(34))
                    .or(AuthorSpecification.firstnameContains("T"))
//                    .and(AuthorSpecification.firstnameContains("T"))
                    ;
            authorRepository.findAll(spec).forEach(System.out::println);
        };

    }
}
