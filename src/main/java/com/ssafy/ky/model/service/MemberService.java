package com.ssafy.ky.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ky.dto.Mail;
import com.ssafy.ky.dto.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;
	
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(MemberDto memberDto) throws Exception;
	/* Admin */
	List<MemberDto> listMember() throws Exception;
	MemberDto getMember(String userId) throws Exception;
	
	int updateMember(MemberDto memberDto) throws Exception;
	int deleteMember(String userid) throws Exception;

	void sendEmail(Mail mail);

	String getRandomCode();

	int setTempPwd(String userId, String str);
	
}
