package hospital_BE.dto;

import hospital_BE.enums.ResponseStatus;
import lombok.Data;

@Data
public class ResultResponse {
	private int code;
	private ResponseStatus status;
	private String message;
	private Object data;
}
