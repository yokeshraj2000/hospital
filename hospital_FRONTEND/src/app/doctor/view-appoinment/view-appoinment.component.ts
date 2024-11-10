import { AfterViewInit, Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { DataTable } from 'simple-datatables';
import { DoctorService } from '../doctor.service';


@Component({
  selector: 'app-view-appoinment',
  templateUrl: './view-appoinment.component.html',
  styleUrls: ['./view-appoinment.component.css']
})
export class ViewAppoinmentComponent implements OnInit,AfterViewInit {

  allAppoinments:any;
  doctors = [{
    "name": "Dr.Manohar (MBBS)",
    "mobile": "7092399569",
    "email":"manohar@gmail.com"
  }]

  capitalizeAppointmentType(type: string): string {
    return type.split(/(?=[A-Z])/).map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');
  }

  constructor(private doctorService:DoctorService){}

  ngOnInit() {
    this.getAllAppointments();
  }

  ngAfterViewInit() {
    this.dataTableEdit();
  }

  getAllAppointments() {
    this.doctorService.getAllPatientDetails().subscribe((data: any) => {
      if (data.status === 'SUCCESS') {
        this.allAppoinments = data.data;
        console.log("data", this.allAppoinments);

        const dataTable = new DataTable("#doctorAppoinmentTable", {
          data: {
            headings: [
              'S No',
              'Name',
              'Mobile',
              'Gender',
              'Age',
              'Appoinment For',
              'Appoinment Date and Time',
              'Status',
            ],
            data: this.allAppoinments.map((item: any, index: number) => [
              (index + 1).toString(),
              this.capitalizeAppointmentType(item.name),
              item.mobile,
              item.gender,
              item.age,
              this.capitalizeAppointmentType(item.typeOfAppointment),
              moment(item.bookedSlotsEntities[0].bookedDate).format('MMMM DD, YYYY') + ' - ' + item.bookedSlotsEntities[0].bookedSlot,
              `<span class="btn ${item.isCompleted === 'Y' ? 'badge bg-success' : 'badge bg-danger'} active-btn" data-index="${item.patient_id}" data-status="${item.isCompleted === 'Y' ? 1 : 0}">${item.isCompleted === 'Y' ? 'Completed' : 'Pending'}</span>`,
            ])
          }
        });
      }
    });
  }

  dataTableEdit() {
    const doctorAppoinmentTable = document.getElementById('doctorAppoinmentTable') as HTMLElement | null;
    if (doctorAppoinmentTable) {
      doctorAppoinmentTable.addEventListener('click', (event) => {
        const target = event.target as HTMLElement;
        if (target.classList.contains('active-btn')) {
          this.updateAppointmentStatus(+target.getAttribute('data-index')!);
        }
      });
    }
  }

  updateAppointmentStatus(patientId:any){
    this.doctorService.updateAppointmentStatus(patientId).subscribe((data:any) => {
      if(data.status == 'SUCCESS'){
        window.location.reload();
      }
    });
  }

}
