package hospital_BE;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hospital_BE.dto.ResultResponse;
import hospital_BE.enums.IsCompleted;
import hospital_BE.enums.ResponseStatus;
import hospital_BE.model.BookedSlotsEntity;
import hospital_BE.model.PatientDetailsEntity;
import hospital_BE.repositories.BookedSlotsRepository;
import hospital_BE.repositories.PatientRepository;
import hospital_BE.service.PatientService;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private BookedSlotsRepository bookedSlotsRepository;

    @InjectMocks
    private PatientService patientService;

    private static final Logger logs = LoggerFactory.getLogger(PatientService.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPatientDetails_Success() {
        PatientDetailsEntity patient = new PatientDetailsEntity();
        when(patientRepository.findAll()).thenReturn(List.of(patient));

        ResultResponse result = patientService.getAllPatientDetails();

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("Patient details are fetched", result.getMessage());
        assertEquals(200, result.getCode());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPatientDetails_Empty() {
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        ResultResponse result = patientService.getAllPatientDetails();

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("Patient details are empty", result.getMessage());
        assertEquals(404, result.getCode());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testAddPatientDetails_Success() {
        PatientDetailsEntity patient = new PatientDetailsEntity();
        patient.setDob(LocalDate.of(1990, 1, 1));
        when(patientRepository.save(any(PatientDetailsEntity.class))).thenReturn(patient);

        ResultResponse result = patientService.addPatientDetails(patient);

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("Patient details are added successfully", result.getMessage());
        assertEquals(201, result.getCode());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testGetAllBookedSlots_Success() {
        BookedSlotsEntity slot = new BookedSlotsEntity();
        when(bookedSlotsRepository.findAll()).thenReturn(List.of(slot));

        ResultResponse result = patientService.getAllBookedSlots();

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("slots details are fetched", result.getMessage());
        assertEquals(200, result.getCode());
        verify(bookedSlotsRepository, times(1)).findAll();
    }

    @Test
    void testUpdateAppointmentStatus_PatientFound() {
        PatientDetailsEntity patient = new PatientDetailsEntity();
        patient.setIsCompleted(IsCompleted.N);
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(PatientDetailsEntity.class))).thenReturn(patient);

        ResultResponse result = patientService.updateAppointmentStatus(1L);

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("Patient status updated successfully", result.getMessage());
        assertEquals(200, result.getCode());
        verify(patientRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testUpdateAppointmentStatus_PatientNotFound() {
        when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResultResponse result = patientService.updateAppointmentStatus(1L);

        assertEquals(ResponseStatus.FAILURE, result.getStatus());
        assertEquals("Patient not found", result.getMessage());
        assertEquals(404, result.getCode());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDetailsByMobileAndEmail_Success() {
        PatientDetailsEntity patient = new PatientDetailsEntity();
        when(patientRepository.findByMobileAndEmail(anyString(), anyString())).thenReturn(List.of(patient));

        PatientDetailsEntity queryPatient = new PatientDetailsEntity();
        queryPatient.setEmail("test@example.com");
        queryPatient.setMobile("1234567890");

        ResultResponse result = patientService.getDetailsByMobileAndEmail(queryPatient);

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("Patient details are fetched", result.getMessage());
        assertEquals(200, result.getCode());
        verify(patientRepository, times(1)).findByMobileAndEmail("1234567890", "test@example.com");
    }

    @Test
    void testGetDetailsByMobileAndEmail_Empty() {
        when(patientRepository.findByMobileAndEmail(anyString(), anyString())).thenReturn(Collections.emptyList());

        PatientDetailsEntity queryPatient = new PatientDetailsEntity();
        queryPatient.setEmail("test@example.com");
        queryPatient.setMobile("1234567890");

        ResultResponse result = patientService.getDetailsByMobileAndEmail(queryPatient);

        assertEquals(ResponseStatus.SUCCESS, result.getStatus());
        assertEquals("Patient details are empty", result.getMessage());
        assertEquals(404, result.getCode());
        verify(patientRepository, times(1)).findByMobileAndEmail("1234567890", "test@example.com");
    }
}
