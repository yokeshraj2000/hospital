# Hospital Management System

## Project Overview

The **Hospital Management System** is a full-stack application built with a **Java Spring Boot** backend and an **Angular** frontend. The application provides functionalities for managing patient and doctor records and scheduling appointments within a hospital setting. The backend handles RESTful API services, while the Angular frontend provides an intuitive user interface for patients, doctors, and administrators.

---

## Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [API Endpoints](#api-endpoints)
- [Frontend Routes](#frontend-routes)
- [Running Tests](#running-tests)

---

## Technologies Used

- **Backend**: Java, Spring Boot, JPA, MySQL
- **Frontend**: Angular 16, Angular Material, Bootstrap
- **Testing**: JUnit, Mockito (backend), Jasmine/Karma (frontend)
- **Build Tools**: Maven for backend, Angular CLI for frontend

---

## Project Structure

hospital/
├── hospital_BE/         # Backend directory (Spring Boot)
├── hospital_FE/         # Frontend directory (Angular)
├── .git/                # Git repository files
├── README.md            # Project documentation (this file)
```

### Backend Directory: `hospital_BE`

The backend is a **Spring Boot** application that provides the main RESTful API services for managing patient and doctor information.

### Frontend Directory: `hospital_FE`

The frontend is an **Angular 16** application that serves as the user interface, including separate modules for managing doctor and patient-related functionalities.

---

## Setup Instructions

### Backend Setup

1. **Navigate to Backend Directory**

   cd hospital/hospital_BE

2. **Configure Database**

   - Create a MySQL database named `hospital` (or adjust the name in the configuration).
   - Configure your database connection in `src/main/resources/application.properties`:

     spring.datasource.url=jdbc:mysql://localhost:3306/hospital
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password

3. **Build and Run the Application**

   Use Maven to build and run the backend:

   mvn clean install

   The backend server will run on `http://localhost:8080`.

4. ""Swagger**

    http://localhost:8080/swagger-ui/index.html#/

### Frontend Setup

1. **Navigate to Frontend Directory**

   cd hospital/hospital_FE

2. **Install Dependencies**

   Use `npm` or `yarn` to install the Angular dependencies:

   npm install

3. **Run the Application**

   Start the Angular development server:

   ng serve

   The frontend application will run on `http://localhost:4200`.

---

## API Endpoints (Backend)

Below are the key API endpoints provided by the backend:

1. **Get All Patient Details**

   - **URL**: `/api/patients`
   - **Method**: `GET`
   - **Description**: Retrieves all patient records.
   
2. **Add Patient Details**

   - **URL**: `/api/patients`
   - **Method**: `POST`
   - **Description**: Adds new patient details and calculates their age based on date of birth.
   
3. **Get All Booked Slots**

   - **URL**: `/api/booked-slots`
   - **Method**: `GET`
   - **Description**: Retrieves all booked slots.
   
4. **Update Appointment Status**

   - **URL**: `/api/patients/{patientId}/status`
   - **Method**: `PUT`
   - **Description**: Toggles the appointment completion status of a patient.
   
5. **Get Patient by Mobile and Email**

   - **URL**: `/api/patients/details`
   - **Method**: `POST`
   - **Description**: Retrieves patient details based on their mobile number and email address.

---

## Frontend Routes (Angular)

The application has the following Angular routes, organized with lazy-loaded modules:

const routes: Routes = [
  {
    path: 'doctor',
    loadChildren: () => import('./doctor/doctor.module').then((m) => m.DoctorModule)
  },
  {
    path: 'patient',
    loadChildren: () => import('./patient/patient.module').then((m) => m.PatientModule)
  }
];

### Frontend Dependencies

The frontend uses the following dependencies:

"dependencies": {
  "@angular/animations": "^16.1.0",
  "@angular/cdk": "^16.2.14",
  "@angular/common": "^16.1.0",
  "@angular/compiler": "^16.1.0",
  "@angular/core": "^16.1.0",
  "@angular/forms": "^16.1.0",
  "@angular/material": "^16.2.14",
  "@angular/platform-browser": "^16.1.0",
  "@angular/platform-browser-dynamic": "^16.1.0",
  "@angular/router": "^16.1.0",
  "bootstrap": "^5.3.3",
  "bootstrap-icons": "^1.11.3",
  "moment": "^2.30.1",
  "rxjs": "~7.8.0",
  "simple-datatables": "^4.0.8",
  "sweetalert2": "^11.12.1",
  "tslib": "^2.3.0",
  "zone.js": "~0.13.0"
}

---

## Running Tests

### Backend Tests

Backend tests are implemented with **JUnit** and **Mockito** and cover core services in the patient and slot management modules.

Run the tests with the following command:

mvn test

### Frontend Tests

Frontend tests are written using **Jasmine** and **Karma** for unit and end-to-end testing.

To run the frontend tests:

ng test

---