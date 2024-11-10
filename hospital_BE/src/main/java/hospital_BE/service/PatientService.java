package hospital_BE.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital_BE.dto.ResultResponse;
import hospital_BE.enums.IsCompleted;
import hospital_BE.enums.ResponseStatus;
import hospital_BE.model.BookedSlotsEntity;
import hospital_BE.model.PatientDetailsEntity;
import hospital_BE.repositories.BookedSlotsRepository;
import hospital_BE.repositories.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	BookedSlotsRepository bookedSlotsRepository;

	private static final Logger logs = LoggerFactory.getLogger(PatientService.class);

	public ResultResponse getAllPatientDetails() {
		ResultResponse finalresult = new ResultResponse();
		try {
			List<PatientDetailsEntity> patientDetailsEntity = patientRepository.findAll();
			if (!patientDetailsEntity.isEmpty()) {
				finalresult.setData(patientDetailsEntity);
				finalresult.setMessage("Patient details are fetched");
				finalresult.setStatus(ResponseStatus.SUCCESS);
				finalresult.setCode(200);
			} else {
				finalresult.setData(null);
				finalresult.setMessage("Patient details are empty");
				finalresult.setStatus(ResponseStatus.SUCCESS);
				finalresult.setCode(404);

			}
		} catch (Exception e) {
			logs.error("PatientService :: getAllPatientDetails() :: " + e.getMessage());
			finalresult.setStatus(ResponseStatus.FAILURE);
			finalresult.setMessage(e.getMessage());
			finalresult.setCode(500);
		}
		return finalresult;
	}

	public ResultResponse addPatientDetails(PatientDetailsEntity patientDetails) {
		ResultResponse finalResult = new ResultResponse();
		try {
			LocalDate dob = patientDetails.getDob();
			if (dob != null) {
				LocalDate now = LocalDate.now();
				int age = Period.between(dob, now).getYears();
				patientDetails.setAge(age);
			}
			PatientDetailsEntity savedPatientDetails = patientRepository.save(patientDetails);
			finalResult.setData(savedPatientDetails);
			finalResult.setMessage("Patient details are added successfully");
			finalResult.setStatus(ResponseStatus.SUCCESS);
			finalResult.setCode(201);
		} catch (Exception e) {
			logs.error("PatientService :: addPatientDetails() :: " + e.getMessage());
			finalResult.setStatus(ResponseStatus.FAILURE);
			finalResult.setMessage(e.getMessage());
			finalResult.setCode(500);
		}
		return finalResult;
	}

	public ResultResponse getAllBookedSlots() {
		ResultResponse finalresult = new ResultResponse();
		try {
			List<BookedSlotsEntity> bookedSlots = bookedSlotsRepository.findAll();
			if (!bookedSlots.isEmpty()) {
				finalresult.setData(bookedSlots);
				finalresult.setMessage("slots details are fetched");
				finalresult.setStatus(ResponseStatus.SUCCESS);
				finalresult.setCode(200);
			} else {
				finalresult.setData(null);
				finalresult.setMessage("slots details are empty");
				finalresult.setStatus(ResponseStatus.SUCCESS);
				finalresult.setCode(404);

			}
		} catch (Exception e) {
			logs.error("PatientService :: getAllBookedSlots() :: " + e.getMessage());
			finalresult.setStatus(ResponseStatus.FAILURE);
			finalresult.setMessage(e.getMessage());
			finalresult.setCode(500);
		}
		return finalresult;
	}

	public ResultResponse updateAppointmentStatus(Long patientId) {
		ResultResponse finalResult = new ResultResponse();
		try {
			Optional<PatientDetailsEntity> optionalPatient = patientRepository.findById(patientId);
			if (optionalPatient.isPresent()) {
				PatientDetailsEntity patientDetails = optionalPatient.get();
				IsCompleted currentStatus = patientDetails.getIsCompleted();
				IsCompleted newStatus = (currentStatus == IsCompleted.N) ? IsCompleted.Y : IsCompleted.N;
				patientDetails.setIsCompleted(newStatus);
				patientRepository.save(patientDetails);
				finalResult.setData(patientDetails);
				finalResult.setMessage("Patient status updated successfully");
				finalResult.setStatus(ResponseStatus.SUCCESS);
				finalResult.setCode(200);
			} else {
				finalResult.setData(null);
				finalResult.setMessage("Patient not found");
				finalResult.setStatus(ResponseStatus.FAILURE);
				finalResult.setCode(404);
			}
		} catch (Exception e) {
			logs.error("PatientService :: updateAppointmentStatus() :: " + e.getMessage());
			finalResult.setStatus(ResponseStatus.FAILURE);
			finalResult.setMessage(e.getMessage());
			finalResult.setCode(500);
		}
		return finalResult;
	}

	public ResultResponse getDetailsByMobileAndEmail(PatientDetailsEntity patientDetails) {
		ResultResponse finalresult = new ResultResponse();
		try {
			String email = patientDetails.getEmail();
			String mobile = patientDetails.getMobile();
			List<PatientDetailsEntity> patientDetailsEntity = patientRepository.findByMobileAndEmail(mobile, email);
			if (!patientDetailsEntity.isEmpty()) {
				finalresult.setData(patientDetailsEntity);
				finalresult.setMessage("Patient details are fetched");
				finalresult.setStatus(ResponseStatus.SUCCESS);
				finalresult.setCode(200);
			} else {
				finalresult.setData(null);
				finalresult.setMessage("Patient details are empty");
				finalresult.setStatus(ResponseStatus.SUCCESS);
				finalresult.setCode(404);
			}
		} catch (Exception e) {
			logs.error("PatientService :: getAllPatientDetails() :: " + e.getMessage());
			finalresult.setStatus(ResponseStatus.FAILURE);
			finalresult.setMessage(e.getMessage());
			finalresult.setCode(500);
		}
		return finalresult;
	}

}
