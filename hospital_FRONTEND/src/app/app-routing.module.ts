import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'doctor',
    loadChildren: () => import('./doctor/doctor.module').then((m) => m.DoctorModule)
  },
  {
    path: 'patient',
    loadChildren: () => import('./patient/patient.module').then((m) => m.PatientModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
