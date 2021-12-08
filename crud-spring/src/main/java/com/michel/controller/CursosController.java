package com.michel.controller;
import java.util.List;
import com.michel.model.Course;
import com.michel.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //RestController sinaliza para o Spring que essa classe possui um endpoint (url) que vai ser usado para acessar a API (usando, por exemplo, o método httpClient.get('/api/courses') ) - essa classe é um Java Servlet
@RequestMapping("/api/courses") //o /api é para diferenciar as rotas aqui do back-end das rotas do Angular
public class CursosController {  
    //@Autowired  //injeção de dependência da interface CourseRepository (não se usa muito pq é mais interessante fazer a injeção via construtor, mas pode usar tambem)
    private final CourseRepository courseRepository;
  
    public CursosController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //@RequestMapping(method = RequestMethod.GET) //faz o mesmo que o @GetMapping
    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();  //faz o SELECT * FROM tabela
    }

    @Bean   //o Bean diz para o Spring cuidar do ciclo de vida desse componente (esse aqui será executado sempre que essa classe for executas, é tipo um onInit())
    CommandLineRunner initDataBase(CourseRepository courseRepository){
        return args -> {
            Course c = new Course();
            c.setName("Angular");
            c.setCategory("front-end");

            courseRepository.deleteAll();
            courseRepository.save(c);
        };
    }
}
