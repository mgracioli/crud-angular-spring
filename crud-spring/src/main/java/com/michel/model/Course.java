package com.michel.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data //anotação do Lombok - cria os getters e setters das variáveis, o construtor, toString, entre outros
@Entity //anotação da JPA (Java Persistency Aplication) - mapeia essa classe como uma entidade/tabela (lembrar do modelo entidade-relacionamento) do banco de dados
//@Table(name = "cursos") //anotação para caso queira mudar o nome da tabela no banco de dados, se não tiver essa opção, nesse caso, a tabela lá vai se chamar 'Course'. Também serve para quando as tabelas já existem no banco de dados e você não vai precisar criar ela, só a fazer a referência para ela
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")    //transforma o id em _id, sem isso, o id nao aparece lá no Angular pq ele vai tentar pegar id e, não _id. O _id é como foi definido o nome desse campo lá no Angular (no arquivo models -> course.ts)
    private Long id;

    //@Column(name = "nome") //mesma ideia do @Table, para mudar o nome da coluna ou parar refernciar uma coluna já existente no banco
    @Column(length = 200, nullable = false)  //nullable é o mesmo que 'not null'
    private String name;

    @Column(length = 10, nullable = false)
    private String category;
}
