import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { DoctorRoutingModule } from './doctor-routing.module';
import { ViewAppoinmentComponent } from './view-appoinment/view-appoinment.component';


@NgModule({
  declarations: [
    ViewAppoinmentComponent
  ],
  imports: [
    CommonModule,
    DoctorRoutingModule,
    HttpClientModule
  ]
})
export class DoctorModule { }
