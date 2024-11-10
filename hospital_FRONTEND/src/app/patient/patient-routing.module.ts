import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookAppoinmentComponent } from './book-appoinment/book-appoinment.component';

const routes: Routes = [
  {
    path:'',
    component:BookAppoinmentComponent
  },
  {
    path:'bookAppoinment',
    component:BookAppoinmentComponent
  },
  {
    path:'**',
    component:BookAppoinmentComponent
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }