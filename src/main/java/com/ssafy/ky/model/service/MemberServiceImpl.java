package com.ssafy.ky.model.service;

import java.util.List;
import java.util.Map;

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

}
