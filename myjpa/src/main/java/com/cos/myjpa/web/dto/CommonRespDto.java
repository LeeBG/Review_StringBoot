package com.cos.myjpa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonRespDto<T> {
	private int statusCode; // 상태코드 1:정상 -1:실패
	private String msg;
	private T data;	
}
