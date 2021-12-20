import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, take, first, delay } from 'rxjs/operators';
import { Course } from '../models/course';

@Injectable({
  providedIn: 'root'  //a instancia dessa classe vai ser fornecida na raiz do projeto, ou seja, o courses.service fica disponível de maneira global no aplicativo, ou seja, nao precisa exportar esse serviço para poder utilizar em putras partes do codigo
})
export class CoursesService {

  private readonly API = 'api/courses'  //o back-end disponibiliza, nesta url/neste end-point, os dados que estão no banco de dados, no caso, a lista de cursos, eu preciso, então fazer uma requisição do tipo GET para esse end-point para recuperar esses dados

  constructor(private httpClient: HttpClient) { }

  list(){
    return this.httpClient.get<Course[]>(this.API)
    .pipe( //o pipe() permite manipular a resposta do coursesService.list() antes de atribuir ela para a variável courses$
      take(1),
      //first(), //first é o mesmo que o take(1), ele só recebe a primeira resposta do servidor e ja se desinscreve do observable 
      //delay(2000),  //esse delay é para testar o spinner
      //tap(courses => console.log(courses))
    )
  }

}
