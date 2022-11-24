package com.ssafy.ky.model.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ssafy.ky.dto.Mail;
import com.ssafy.ky.dto.MemberDto;
import com.ssafy.ky.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
//	@Autowired
//	private SqlSession sqlSession;
	@Autowired
    JavaMailSender mailSender;
 
    @Override
    public void sendEmail(Mail mail) {
 
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                
                helper.setFrom(mail.getMailFrom()); // recipient
                helper.setTo(mail.getMailTo()); //sender
                helper.setSubject(mail.getMailSubject()); // mail title
                helper.setText(mail.getMailContent(), true); // mail content
            }
        };
 
        mailSender.send(preparator);
    }
    
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).idCheck(userId);
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
//		sqlSession.getMapper(MemberMapper.class).joinMember(memberDto);
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(MemberDto memberDto) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).loginMember(map);
		return memberMapper.loginMember(memberDto);
	}

	@Override
	public List<MemberDto> listMember() throws Exception {
		return memberMapper.listMember();
	}

	@Override
	public MemberDto getMember(String userId) throws Exception {
		return memberMapper.getMember(userId);
	}

	@Override
	public int updateMember(MemberDto memberDto) throws Exception {
		return memberMapper.updateMember(memberDto);
	}

	@Override
	public int deleteMember(String userId) throws Exception {
		return memberMapper.deleteMember(userId);		
	}
	
	@Override
	public String getRandomCode() {
		
		/*
		 * Random rand = new Random(System.nanoTime()); StringBuffer sb = new
		 * StringBuffer();
		 * 
		 * for (int i = 0; i < 20; i++) {
		 * 
		 * if(rand.nextBoolean()) { sb.append(rand.nextInt(10)); }else {
		 * sb.append((char)rand.nextInt(26)+97); } } System.out.println(sb); return
		 * sb.toString();
		 */
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
	}

	@Override
	public int setTempPwd(String userId, String str) {
		return memberMapper.setTempPwd(userId, str);	
	}

}
