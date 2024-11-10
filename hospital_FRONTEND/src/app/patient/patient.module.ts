import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BookAppoinmentComponent } from './book-appoinment/book-appoinment.component';
import { PatientRoutingModule } from './patient-routing.module';




@NgModule({
  declarations: [
    BookAppoinmentComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    PatientRoutingModule,
    HttpClientModule
  ]
})
export class PatientModule { }
