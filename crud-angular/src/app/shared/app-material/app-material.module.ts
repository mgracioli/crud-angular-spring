import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';


/*Esse módulo contém todos os imports dos módulos do
angular material que são usados na aplicação, foi criado apenas
para deixar o código mais legível e reutilizável. Não é necessário
fazer isso
*/

@NgModule({
    exports: [
      MatTableModule,
      MatCardModule,
      MatToolbarModule,
      MatProgressSpinnerModule,
      MatDialogModule,
      MatButtonModule,
      MatIconModule
    ]
})
export class AppMaterialModule { }
