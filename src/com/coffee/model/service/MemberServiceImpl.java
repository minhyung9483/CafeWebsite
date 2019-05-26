package com.coffee.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.coffee.common.exception.DeleteFailException;
import com.coffee.common.exception.EditFailException;
import com.coffee.common.exception.RegistFailException;
import com.coffee.model.domain.Member;
import com.coffee.model.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
   @Autowired
   //@Qualifier("mybatisMemberDAO")
   private MemberDAO memberDAO;
   
   @Override
   public void insert(Member member) throws RegistFailException{
      int result=memberDAO.insert(member);
      if(result==0) {
         throw new RegistFailException("��ϵ��� �ʾҽ��ϴ�");
      }
   }

   @Override
   public List selectAll() {
      return memberDAO.selectAll();
   }

   @Override
   public Member select(int member_id) {
      return memberDAO.select(member_id);
   }

   @Override
   public void update(Member member) throws EditFailException{
      int result=memberDAO.update(member);
      if(result==0) {
         throw new EditFailException("�������� �ʾҽ��ϴ�");
      }
   }

   @Override
   public void updatePoint(Member member) throws EditFailException{
      // TODO Auto-generated method stub
      int result = memberDAO.updatePoint(member);
      if(result==0) {
    	  throw new EditFailException("����Ʈ ���� ����");
      }
   }

   @Override
   public void delete(int member_id) throws DeleteFailException{
      int result=memberDAO.delete(member_id);
      if(result==0) {
         throw new DeleteFailException("�������� �ʾҽ��ϴ�");
      }
   }

   @Override
   public Member logIn(Member member) {
      return memberDAO.logIn(member);
   }
   @Override
	public Member findId(Member member) {
		// TODO Auto-generated method stub
		return memberDAO.findId(member);
	}

	@Override
	public Member findPass(Member member) {
		// TODO Auto-generated method stub
		return memberDAO.findPass(member);
	}

	@Override
	public Member checkInfo(Member member) {
		return memberDAO.checkInfo(member);
	}

	@Override
	public void changePass(Member member) throws EditFailException{
		// TODO Auto-generated method stub
		System.out.println("service������...");
		int result =  memberDAO.changePass(member);
		if(result == 0) {
			throw new EditFailException("��й�ȣ ���� ����...");
		}
	}
	@Override
	public Member checkphone(Member member) {
		return memberDAO.checkPhone(member);
	}

	@Override
	public List search(Member member) {
		return memberDAO.search(member);
	}
 
}