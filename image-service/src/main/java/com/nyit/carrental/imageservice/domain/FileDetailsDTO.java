package com.nyit.carrental.imageservice.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class FileDetailsDTO {
	
	private String fileId;
	private String relationId;
	private String fileName;

}
