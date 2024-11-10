export const ApiEndpoint = {
    PATIENT : {
        ADD_PATIENT_DETAILS :'/patient/addPatientDetails',
        GET_BOOKED_SLOTS : '/patient/getAllBookedSlots',
        GET_ALL_PATIENT_DETAILS : '/patient/getAllPatientDetails',
        UPDATE_APPOINTMENT_STATUS :'/patient/updateAppointmentStatus?patientId={patientId}',
        GET_DETAILS_BY_MOBILE_AND_EMAIL : '/patient/getDetailsByMobileAndEmail'
    }
}