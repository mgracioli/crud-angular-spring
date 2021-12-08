import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(value: string): string { 
    //code e computer são os nomes dos ícones (do angular material) que serão retornados
    switch(value){
      case 'front-end': return 'code'
      case 'back-end': return 'computer'
    }
    return 'code';
  }

}
