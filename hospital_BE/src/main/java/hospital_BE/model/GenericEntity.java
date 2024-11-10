package hospital_BE.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import hospital_BE.enums.IsCompleted;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class GenericEntity {

	@Column(name = "createdAt")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	@Column(length = 32, columnDefinition = "enum('Y','N') default 'N'")
	@Enumerated(value = EnumType.STRING)
	private IsCompleted isCompleted = IsCompleted.N;

}
