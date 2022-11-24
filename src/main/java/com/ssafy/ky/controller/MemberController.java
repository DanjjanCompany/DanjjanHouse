package com.ssafy.ky.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ky.dto.Mail;
import com.ssafy.ky.dto.MemberDto;
import com.ssafy.ky.model.service.MailService;
import com.ssafy.ky.model.service.MemberService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@Slf4j
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	final static int EXPIRE_MINUTES = 10;
	final static String SECRET_KEY = "ssafy";
	 
	 
	 // 메일 보내기
	 @PostMapping("/mail")
	  public ResponseEntity<?> mailSending(@RequestBody MemberDto member) {
	   
		log.debug("mail 메서드 실행");
		log.debug("member: {}",member);
		String userId = member.getUserId();
		String toEmail = member.getEmailId()+"@"+member.getEmailDomain();
		log.debug("toEmail: {}",toEmail); // 상태 코드만으로 구분
		
		/*
		 * String setfrom = "jiyeoyou0416@naver.com"; String tomail =
		 * "jiyeoyou0416@naver.com"; // 받는 사람 이메일 String title =
		 * "jiyeoyou0416@naver.com"; // 보내는 사람 이메일 String content =
		 * "jiyeoyou0416@naver.com"; // 보내는 사람 이메일 String filename = "img/img1.jpg"; //
		 * 파일 경로.
		 * 
		 * try { MimeMessage message = mailSender.createMimeMessage(); MimeMessageHelper
		 * messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		 * 
		 * messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
		 * messageHelper.setTo(tomail); // 받는사람 이메일 messageHelper.setSubject(title);
		 * //메일제목은 생략이 가능하다 messageHelper.setText(content); // 메일 내용
		 * 
		 * // 파일첨부 FileSystemResource fsr = new FileSystemResource(filename);
		 * messageHelper.addAttachment("img/img1.jpg",fsr);
		 * 
		 * mailSender.send(message); log.debug("mail 보내짐"); } catch(Exception e){
		 * System.out.println(e); }
		 */
		 
		
		  String str = memberService.getRandomCode(); //log.debug("{}",str);
		  
		  
		  
		  //String userId = "ssafy";
		  
		  Mail mail = new Mail(); 
		  mail.setMailFrom("jiyeonyou0416@naver.com");
		  mail.setMailTo(toEmail); 
		  mail.setMailSubject("임시 비밀번호 전송");
		  mail.setMailContent(userId+"님의 임시 비밀번호는 "+str+"입니다. 추후 비밀번호 변경을 진행해주세요.");
		  
		  //AbstractApplicationContext context = new
		  //AnnotationConfigApplicationContext(MailConfig.class);
		  mailService.sendEmail(mail); 
		  int result = memberService.setTempPwd(userId,str);
		  
		  if (result == 1) return new
		  ResponseEntity<Void>(HttpStatus.OK); else return new
		  ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		 
	  } 


	// 로그인 요청 처리 - POST /users/login
	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody Map<String, String> map) throws Exception {
		// 유저 정보 조회
		MemberDto member = new MemberDto();
		member.setUserId(map.get("id"));
		member.setUserPwd(map.get("pass"));
		System.out.println("memberDto member: " + member.toString());
		MemberDto memberInfo = memberService.loginMember(member);
		System.out.println("memberInfo : " + memberInfo);
		// 로그인 성공
		if (memberInfo != null) {
			// jwt 토큰 생성
			String token = Jwts.builder()
					// header
					.setHeaderParam("algo", "HS256").setHeaderParam("type", "JWT")
					// payload
					.claim("id", memberInfo.getUserId()).claim("name", memberInfo.getUserName())
					// 만료기간 설정
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
					// signature
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8")).compact();

			log.debug("발급된 토큰 : {}", token);

			// 한번 감싸서 보냄 -> 받을때 가독성
			Map<String, String> result = new HashMap<>();
			result.put("token", token);

			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		}
		// 로그인 실패
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 1. Create 등록
	// 데이터 등록 요청
	@PostMapping
	@ApiOperation(value = "멤버 정보 등록. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
	public ResponseEntity<?> doRegist(@RequestBody MemberDto member) throws Exception {
		int result = memberService.joinMember(member);
		log.debug("{}",member);
		// 상태 코드만으로 구분
		if (result == 1)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 2. Read 조회
	// 전체 목록 조회
	@GetMapping
	@ApiOperation(value = "모든 유저의 정보를 반환", response = List.class)
	public ResponseEntity<?> showList() throws Exception {

		List<MemberDto> members = memberService.listMember();
		return new ResponseEntity<List<MemberDto>>(members, HttpStatus.OK);
	}

	// 상세 조회
	@GetMapping("/{userId}")
	@ApiOperation(value = "userId에 해당하는 유저의 정보를 반환한다.", response = MemberDto.class)
	public ResponseEntity<?> showDetail(@PathVariable String userId) throws Exception {
		MemberDto member = memberService.getMember(userId);
		return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
	}

	// 3. Update 수정
	// 데이터 수정 요청
	@PutMapping("/{userId}")
	@ApiOperation(value = "userId에 해당하는 유저의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
	public ResponseEntity<?> modifyUser(@RequestBody MemberDto member) throws Exception {
		int result = memberService.updateMember(member);

		// 상태 코드만으로 구분
		if (result == 1)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 4. Delete 삭제
	@DeleteMapping("/{userId}")
	@ApiOperation(value = "userId에 해당하는 유저의 정보 삭제. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열 반환", response = String.class)
		public ResponseEntity<?> doDelete(@PathVariable String userId) throws Exception {
			int result = memberService.deleteMember(userId);
			
			//상태 코드만으로 구분
			if(result==1) return new ResponseEntity<Void>(HttpStatus.OK);
			else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
