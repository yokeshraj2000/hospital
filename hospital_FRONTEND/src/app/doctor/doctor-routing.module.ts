import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewAppoinmentComponent } from './view-appoinment/view-appoinment.component';

const routes: Routes = [
  {
    path:'',
    component:ViewAppoinmentComponent
  },
  {
    path:'viewAppoinment',
    component:ViewAppoinmentComponent
  },
  {
    path:'**',
    component:ViewAppoinmentComponent
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DoctorRoutingModule { }
