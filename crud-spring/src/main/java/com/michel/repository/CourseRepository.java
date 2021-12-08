package com.michel.repository;
import com.michel.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{  //Course é a Entidade e Long é a chave primária dessa entidade
    //essa interfaace não tem métodos q precisam ser definidos, todos os métodos que precisamos usar são os da API do Java Persistency - JpaRepository - (findAll(), findAllById(), delete(), save(), getById()...)
}
