package co.prod.mapper;

import java.util.List;

import com.prod.vo.MemberVO;


public interface MemberMapper {
	// 매퍼(MemberMapper.xml) 에서 실행할 메소드 정의
	public List<MemberVO> getMembers();
	
	// 로그인 용도.
	public MemberVO login(MemberVO vo);
	
	// 회원삭제
	public int deleteMember(String id);
}
