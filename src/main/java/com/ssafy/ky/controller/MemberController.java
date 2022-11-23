package com.ssafy.ky.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ky.dto.MemberDto;
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

	final static int EXPIRE_MINUTES = 10;
	final static String SECRET_KEY = "ssafy";

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
<<<<<<< HEAD

=======
		log.debug("member: {}",member);
>>>>>>> 876431a8114cc098c5fbd5f8d1a8f1628a77a8cf
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
