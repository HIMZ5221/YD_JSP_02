package co.prod.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.prod.vo.MemberVO;

import co.prod.common.DataSource;
import co.prod.mapper.MemberMapper;


public class MemberServiceMybatis implements MemberService {

	// jdbc : MemberDAO dao;
	// mybatis: DataSource: SqlSessionFactory -> SqlSession;
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	// Mapper.xml 파일의 메소드를 호출.
	private MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

	@Override
	public List<MemberVO> getMembers() {
		return mapper.getMembers();
	}

	@Override
	public boolean addMember(MemberVO vo) {
		return false;
	}

	@Override
	public MemberVO getMember(String id) {
		return null;
	}

	@Override
	public boolean modifyMember(MemberVO vo) {
		return false;
	}

	@Override
	public boolean removeMember(String id) {
		System.out.println("remove 될 ID 명 입니다 : "+id);
		return mapper.deleteMember(id) ==1;
	}

	@Override
	public MemberVO login(MemberVO vo) {
		return mapper.login(vo);
	}

}
