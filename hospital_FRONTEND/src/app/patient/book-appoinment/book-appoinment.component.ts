import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment';
import { DataTable } from 'simple-datatables';
import Swal from 'sweetalert2';
import { PatientService } from '../patient.service';



@Component({
  selector: 'app-book-appoinment',
  templateUrl: './book-appoinment.component.html',
  styleUrls: ['./book-appoinment.component.css']
})
export class BookAppoinmentComponent implements OnInit {


  name = 'Angular';
  maxDate = new Date();
  registerForm!: FormGroup;
  previousAppointmentForm!: FormGroup;
  selectedDate: any;
  slots: string[] = [];
  availableSlots: string[] = [];
  convertedDate: any;
  selectedSlots: { date: string, slot: string }[] = [];
  fullyBookedDates: string[] = [];
  minDate: any;
  showSlot: boolean = false;
  slotMarked: any;
  slotfixed: any;
  view: boolean = false;
  book: boolean = false;
  patientViewData: any;

  capitalizeAppointmentType(type: string): string {
    return type.split(/(?=[A-Z])/).map(word => word.charAt(0).toUpperCase() + word.slice(1)).join(' ');
  }

  gender = [{
    "value": "Male",
  },
  {
    "value": "Female",
  }, {
    "value": "others"
  }]

  appointmentsFor = [{
    "name": "Medical Examination",
    "value": "medicalExamination"
  }, {
    "name": "Doctor Check",
    "value": "doctorCheck"
  }, {
    "name": "Result Analysis",
    "value": "resultAnalysis"
  }, {
    "name": "Check Up",
    "value": "checkUp"
  }]

  doctors = [{
    "name": "Dr.Manohar (MBBS)",
    "mobile": "7092399569"
  }]

  constructor(private fb: FormBuilder, private patientService: PatientService) {
    this.registerForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.pattern(/^[A-Za-z]+(\s[A-Za-z]+)*$/)]],
      lastName: ['', [Validators.required, Validators.pattern(/^[A-Za-z]+(\s[A-Za-z]+)*$/)]],
      dob: ['', Validators.required, this.dateOfBirthValidator],
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobile: ['', [Validators.required, Validators.pattern('^\\d{10}$')]],
      address: ['', Validators.required],
      doctor: ['', Validators.required],
      typeOfAppointment: ['', Validators.required],
      date: ['', Validators.required],
    });

    this.previousAppointmentForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      mobile: ['', [Validators.required, Validators.pattern('^\\d{10}$')]],
    });

    this.loadSelectedSlotsFromLocalStorage();
  }

  ngOnInit() {
    this.generateSlots();
    this.updateFullyBookedDates();
    this.minDate = moment().format('YYYY-MM-DD');

  }

  submit(value: any) {
    const selectedSlot = { date: this.selectedDate, slot: this.slotfixed };
    this.selectedSlots.push(selectedSlot);
    this.saveSelectedSlotsToLocalStorage();
    this.updateFullyBookedDates();
    if (this.registerForm.valid) {
      console.log("value", value);
      this.registerFormSubmit();
    } else {
    }
  }
  registerFormSubmit() {

    const appoinmentBooked = [{
      "bookedDate": this.registerForm.value.date,
      "bookedSlot": this.slotfixed
    }]
    const data = {
      "name": this.registerForm.value.firstName + " " + this.registerForm.value.lastName,
      "address": this.registerForm.value.address,
      "mobile": this.registerForm.value.mobile,
      "beforeAttend": this.registerForm.value.beforeFacility,
      "appointmentDate": this.registerForm.value.date,
      "email": this.registerForm.value.email,
      "gender": this.registerForm.value.gender,
      "typeOfAppointment": this.registerForm.value.typeOfAppointment,
      "dob": this.registerForm.value.dob,
      "isCompleted": 'N',
      "doctor": this.registerForm.value.doctor,
      "createdAt": moment().format('YYYY-MM-DD HH:mm:ss'),
      "bookedSlotsEntities": appoinmentBooked
    }
    this.patientService.addPatientDetails(data).subscribe((data: any) => {
      if (data.status == 'SUCCESS') {
        console.log("Patient", data);
        Swal.fire({
          title: "Appointment confirmed",
          text: "Your details has been saved.",
          icon: "success"
        }).then(() => {
          window.location.reload();
        })
      }
    });

  }

  dateOfBirthValidator(control: any) {
    const selectedDate = new Date(control.value);
    const today = new Date();
    if (selectedDate > today) {
      return { futureDate: true };
    }
    return null;
  }

  generateSlots() {
    const start = new Date();
    start.setHours(9, 30, 0, 0);
    const end = new Date();
    end.setHours(17, 30, 0, 0);
    while (start < end) {
      const slot = `${start.getHours()}:${start.getMinutes().toString().padStart(2, '0')}`;
      this.slots.push(slot);
      start.setHours(start.getHours() + 1);
    }
    this.availableSlots = [...this.slots];
  }

  formatTime(time: string): string {
    return moment(time, 'HH:mm').format('HH:mm');
  }

  onDateChange(event: Event) {
    this.showSlot = true;
    const target = event.target as HTMLInputElement;
    const date = target.value;
    this.convertedDate = moment(date).format('MMMM DD, YYYY');
    this.selectedDate = date;
    const selectedDateSlots = this.selectedSlots
      .filter(s => s.date === this.selectedDate)
      .map(s => this.formatTime(s.slot));
    this.availableSlots = this.slots
      .map(s => this.formatTime(s))
      .filter(slot => !selectedDateSlots.includes(slot));
  }


  selectSlot(slot: string, index: number) {
    console.log("selectSlot", slot);
    if (this.slotMarked !== undefined) {
      this.availableSlots[this.slotMarked] = this.availableSlots[this.slotMarked];
    }
    this.slotMarked = index;
    this.slotfixed = slot;
  }

  saveSelectedSlotsToLocalStorage() {
    localStorage.setItem('selectedSlots', JSON.stringify(this.selectedSlots));
  }

  loadSelectedSlotsFromLocalStorage() {
    this.patientService.getAllBookedSlots().subscribe((data: any) => {
      if (data.status === 'SUCCESS') {
        this.selectedSlots = data.data.map((slot: any) => ({
          date: slot.bookedDate,
          slot: slot.bookedSlot
        }));
        this.updateFullyBookedDates();
      }
    });
  }

  updateFullyBookedDates(): void {
    const dateSlotsMap: { [key: string]: number } = {};
    this.selectedSlots.forEach(slot => {
      if (!dateSlotsMap[slot.date]) {
        dateSlotsMap[slot.date] = 0;
      }
      dateSlotsMap[slot.date]++;
    });
    this.fullyBookedDates = Object.keys(dateSlotsMap).filter(date => dateSlotsMap[date] >= this.slots.length);
  }

  isDateDisabled(date: string): boolean {
    const today = moment().startOf('day');
    const dateMoment = moment(date);
    const isPast = dateMoment.isBefore(today);
    const isFullyBooked = this.fullyBookedDates.includes(moment(date).format('MMMM DD, YYYY'));
    return isPast || isFullyBooked;
  }

  getDetailsByMobileAndEmail(value: any) {
    this.patientViewData = '';
    this.patientService.getDetailsByMobileAndEmail(value).subscribe((details: any) => {
      if (details.status == 'SUCCESS') {
        this.patientViewData = '';
        this.patientViewData = details.data;
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
              'Doctor',
              'Status',
            ],
            data: this.patientViewData.map((item: any, index: number) => [
              (index + 1).toString(),
              this.capitalizeAppointmentType(item.name),
              item.mobile,
              item.gender,
              item.age,
              this.capitalizeAppointmentType(item.typeOfAppointment),
              item.doctor,
              moment(item.bookedSlotsEntities[0].bookedDate).format('MMMM DD, YYYY') + ' - ' + item.bookedSlotsEntities[0].bookedSlot,
              `<span class="btn ${item.isCompleted === 'Y' ? 'badge bg-success' : 'badge bg-danger'} active-btn" data-index="${item.patient_id}" data-status="${item.isCompleted === 'Y' ? 1 : 0}">${item.isCompleted === 'Y' ? 'Completed' : 'Pending'}</span>`,
            ])
          }
        });
      }
    });
  }

  viewAppointment() {
    this.previousAppointmentForm.reset();
    this.view = true;
    this.book = false;
  }

  bookAppointment() {
    this.registerForm.reset();
    this.view = false;
    this.book = true;
  }
}
