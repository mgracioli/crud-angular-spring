package com.michel.controller;
import java.util.List;
import com.michel.model.Course;
import com.michel.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*O CONTROLLER É QUEM FAZ A INTERAÇÃO DO FRONT-END COM O BANCO DE DADOS */

@RestController //RestController sinaliza para o Spring que essa classe possui um endpoint (url) que vai ser usado para acessar a API (usando, por exemplo, o método httpClient.get('/api/courses') ) - essa classe é um Java Servlet. Informa que essa classe fornece serviços REST e retorna a resposta desses serviços no formato JSON
@RequestMapping("/api/courses") //o /api é para diferenciar as rotas aqui do back-end das rotas do Angular, isso é importante por conta do proxy, para ele identificar quais rotas serão o target dele (o proxy é necessário para não dar erro de CORS (Cross Origin) entre o front-end e o back-end). 
//a url 'localhost:8080/api/courses ou localhost:4200/courses (por conta do proxy)' é o end-point para onde os dados do banco de dados (a lista de cursos) estarão disponíveis para serem acessados por uma chamada http GET, por exemplo, lá do front-end
public class CursosController {  
    //@Autowired  //injeção de dependência da interface CourseRepository (não se usa muito pq é mais interessante fazer a injeção via construtor, mas pode usar tambem)
    private final CourseRepository courseRepository;
  
    public CursosController(CourseRepository courseRepository) {    //como eu declarei que CourseRepository é um @Repository (Lá no arquivo CourseRepository.java), eu posso fazer a injeção de dependência dela aqui, e é o Spring que fica responsável por criar, manter e destruir a instância dessa interface automaticamente
        this.courseRepository = courseRepository;
    }

    //@RequestMapping(method = RequestMethod.GET) //faz o mesmo que o @GetMapping
    @GetMapping //faz o mapeamento da url atual (nesse caso: /api/courses (na verdade a url é só /courses por conta do proxy que foi gerado p evitar problema de cross-origin)) e linka ela com o método GET, ou seja, ele sabe que sempre que carregar essa URL tem que fazer uma chamada GET para o banco de dados. Com isso, eu disponibilizo para a URL api/courses a lista de cursos do banco de dados, assim, o Angular vai conseguir fazer uma chamada http GET para essa url e disponibilizar os dados na view
    public List<Course> list(){
        return courseRepository.findAll();  //faz o SELECT * FROM tabela e disponibiliza esses dados na url 'localhos:8080/api/courses'
    }

    @Bean   //o Bean diz para o Spring cuidar do ciclo de vida desse componente (esse aqui será executado sempre que essa classe for executada, é tipo um onInit()). A anotação @Bean é necessária pq essa classe não é um @Repository, um @Component ou um @Service (que são os Beans originais do Spring). Por isso, o Spring não vai gerenciar ela (nao será possivel fazer injeção de dependencia dela, por exemplo, o Spring nao vai criar as instancias, gerenciar e destruir elas automaticamente). Para que o Sprin identifique esse método como um Bean, é preciso usar a amotação, daí o Spring passa a gerenciar ela como faz com os Beans originais.
    CommandLineRunner initDataBase(CourseRepository courseRepository){
        return args -> {
            Course c = new Course();
            c.setName("Angular");   //setName e setCategory só existem aqui pq eu anotei o model->Course.java com o @Data, com isso, o Lombok cria os metodos getters e setters automaticamente para cada atributo da tabela
            c.setCategory("front-end");

            courseRepository.deleteAll();
            courseRepository.save(c);
        };
    }
}
