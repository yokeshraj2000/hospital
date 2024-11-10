import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiEndpoint } from '../common/endpoint';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) { }

  getAllPatientDetails() {
    return this.http.get(environment.serviceUrl + ApiEndpoint.PATIENT.GET_ALL_PATIENT_DETAILS);
  }

  updateAppointmentStatus(patientId: any) {
    return this.http.put(environment.serviceUrl + ApiEndpoint.PATIENT.UPDATE_APPOINTMENT_STATUS.replace("{patientId}", patientId), {});
}

}
