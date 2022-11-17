package com.ssafy.ky.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MemberDto (회원정보)", description = "아이디, 비번, 이름.을 가진   Domain Class")
public class MemberDto {
	private String userId;
	private String userName;
	private String userPwd;
	private String emailId;
	private String emailDomain;
	private String joinDate;
}