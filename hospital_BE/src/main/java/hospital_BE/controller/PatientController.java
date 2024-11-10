package hospital_BE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hospital_BE.dto.ResultResponse;
import hospital_BE.enums.ResponseStatus;
import hospital_BE.model.PatientDetailsEntity;
import hospital_BE.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RequestMapping(path = "/patient")
@RestController
public class PatientController {

	@Autowired
	PatientService patientService;

	@Operation(summary = "Get all patient details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved all patient details"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getAllPatientDetails")
	public ResponseEntity<?> getAllPatientDetails() throws Exception {
		try {
			ResultResponse output = patientService.getAllPatientDetails();
			return ResponseEntity.status(HttpStatus.OK).body(output);
		} catch (Exception e) {
			ResultResponse output = new ResultResponse();
			output.setStatus(ResponseStatus.FAILURE);
			output.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "Add patient details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Patient details successfully added"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/addPatientDetails")
	public ResponseEntity<?> addPatientDetails(@RequestBody PatientDetailsEntity patientDetails) throws Exception {
		try {
			ResultResponse output = patientService.addPatientDetails(patientDetails);
			return ResponseEntity.status(HttpStatus.OK).body(output);
		} catch (Exception e) {
			ResultResponse output = new ResultResponse();
			output.setStatus(ResponseStatus.FAILURE);
			output.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
		}
	}

	@Operation(summary = "Get all booked slots")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved all booked slots"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/getAllBookedSlots")
	public ResponseEntity<?> getAllBookedSlots() throws Exception {
		try {
			ResultResponse output = patientService.getAllBookedSlots();
			return ResponseEntity.status(HttpStatus.OK).body(output);
		} catch (Exception e) {
			ResultResponse output = new ResultResponse();
			output.setStatus(ResponseStatus.FAILURE);
			output.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Operation(summary = "Update appointment status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Appointment status successfully updated"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/updateAppointmentStatus")
	public ResponseEntity<?> updateAppointmentStatus(@RequestParam("patientId") Long patientId) {
		try {
			ResultResponse output = patientService.updateAppointmentStatus(patientId);
			return ResponseEntity.status(HttpStatus.OK).body(output);
		} catch (Exception e) {
			ResultResponse output = new ResultResponse();
			output.setStatus(ResponseStatus.FAILURE);
			output.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
		}
	}

	@Operation(summary = "Get patient details by mobile and email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully retrieved patient details"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/getDetailsByMobileAndEmail")
	public ResponseEntity<?> getDetailsByMobileAndEmail(@RequestBody PatientDetailsEntity patientDetails)
			throws Exception {
		try {
			ResultResponse output = patientService.getDetailsByMobileAndEmail(patientDetails);
			return ResponseEntity.status(HttpStatus.OK).body(output);
		} catch (Exception e) {
			ResultResponse output = new ResultResponse();
			output.setStatus(ResponseStatus.FAILURE);
			output.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
