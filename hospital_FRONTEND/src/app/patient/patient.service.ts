import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { ApiEndpoint } from '../common/endpoint';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) { }

  getAllBookedSlots() {
    return this.http.get(environment.serviceUrl + ApiEndpoint.PATIENT.GET_BOOKED_SLOTS);
  }

  addPatientDetails(body: any) {
    return this.http.post(environment.serviceUrl + ApiEndpoint.PATIENT.ADD_PATIENT_DETAILS, body);
  }
  getDetailsByMobileAndEmail(requestBody: any) {
    return this.http.post(environment.serviceUrl + ApiEndpoint.PATIENT.GET_DETAILS_BY_MOBILE_AND_EMAIL, requestBody);
  }

}
