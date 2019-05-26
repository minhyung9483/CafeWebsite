package com.coffee.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.ModelAndView;


@Aspect
public class AnnotationClientSessionAspect {
   // xml�� ���� ������ ��ġ(pointCut), ��(advice)�� �ڹ� �ڵ忡�� �����ؾ� �Ѵ�.

   @Pointcut("execution(public * com.coffee.controller.client.ClientCouponController.*(..))")
   public void asyncCheck() {}
   @Pointcut("execution(public * com.coffee.controller.client.ClientFranchiseeController.*(..))")
   public void memberCheck() {}
   @Pointcut("execution(public * com.coffee.controller.client.ClientBoardController.*(..))")
   public void boardCheck() {}
   @Pointcut("execution(public * com.coffee.controller.client.ClientController.getItemPage(..))")
   public void couponCheck() {}
   
   //�Ʒ��� �迭�� ����ִ� ��û URL�� ���ؼ��� ���� üũ�� ���ذ���!!
   String[] exceptList= {
		"/admin/login",   
		"/admin/logout",
		"/client/franchisee/list",
		"/ctest/member/goRegist"
   };
   
   @Around("asyncCheck()")
   public String asyncLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
      HttpServletRequest request = null;
      String requestURL=null;
      int count=0; //��ܿ� ������ ��� ������ų ����!!
      String result=null;
      Object[] objArray = joinPoint.getArgs(); // �޼��� ȣ�� �� ���޵� �Ű������� ��ȯ
      for (Object obj : objArray) {// ��� �Ű����� ����
         if (obj instanceof HttpServletRequest) {
            request = (HttpServletRequest) obj;
            requestURL=request.getRequestURL().toString();
            for(int i=0;i<exceptList.length;i++) {
            	if(requestURL.endsWith(exceptList[i])) {
            		count++; //���� ��� �߰�
            	}
            }           
         }
      }
      // �α����� �ʿ��� �޼��� ȣ��ø� ���� üũ!
      if (request != null && count<1) {
         if (request.getSession().getAttribute("client")==null) {
            result="{\"resultCode\":404, \"msg\":\"/client/login/error\"}";
         } else {
            result = (String)joinPoint.proceed();
         }
      } else {
         result = (String) joinPoint.proceed();
      }
      return result;
   }
   
   @Around("memberCheck()")
   public ModelAndView franchiseeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
      ModelAndView mav;
      HttpServletRequest request = null;
      String requestURL=null;
      int count=0; //��ܿ� ������ ��� ������ų ����!!  
      Object[] objArray = joinPoint.getArgs(); // �޼��� ȣ�� �� ���޵� �Ű������� ��ȯ
      for (Object obj : objArray) {// ��� �Ű����� ����
         if (obj instanceof HttpServletRequest) {
            request = (HttpServletRequest) obj;
            requestURL=request.getRequestURL().toString();
            for(int i=0;i<exceptList.length;i++) {
            	if(requestURL.endsWith(exceptList[i])) {
            		count++; //���� ��� �߰�
            	}
            }
         }
      }
      // �α����� �ʿ��� �޼��� ȣ��ø� ���� üũ!
      if (request != null && count<1) {
         if (request.getSession().getAttribute("client")==null) {
            mav=new ModelAndView("/client/login/error");
         } else {
            mav = (ModelAndView)joinPoint.proceed();
            String methodName = joinPoint.getSignature().getName();
         }
      } else {
    	  mav = (ModelAndView)joinPoint.proceed();
         String methodName = joinPoint.getSignature().getName();
      }
      return mav;
   }
   
   @Around("boardCheck()")
   public ModelAndView memberCheck(ProceedingJoinPoint joinPoint) throws Throwable {
	   ModelAndView mav;
	   HttpServletRequest request = null;
	   String requestURL=null;
	   int count=0; //��ܿ� ������ ��� ������ų ����!!  
	   Object[] objArray = joinPoint.getArgs(); // �޼��� ȣ�� �� ���޵� �Ű������� ��ȯ
	   for (Object obj : objArray) {// ��� �Ű����� ����
		   if (obj instanceof HttpServletRequest) {
			   request = (HttpServletRequest) obj;
			   requestURL=request.getRequestURL().toString();
			   for(int i=0;i<exceptList.length;i++) {
				   if(requestURL.endsWith(exceptList[i])) {
					   count++; //���� ��� �߰�
				   }
			   }
		   }
	   }
	   // �α����� �ʿ��� �޼��� ȣ��ø� ���� üũ!
	   if (request != null && count<1) {
		   if (request.getSession().getAttribute("client")==null) {
			   mav=new ModelAndView("/client/login/error");
		   } else {
			   mav = (ModelAndView)joinPoint.proceed();
			   String methodName = joinPoint.getSignature().getName();
		   }
	   } else {
		   mav = (ModelAndView)joinPoint.proceed();
		   String methodName = joinPoint.getSignature().getName();
	   }
	   return mav;
   }
   @Around("couponCheck()")
   public ModelAndView couponCheck(ProceedingJoinPoint joinPoint) throws Throwable {
	   ModelAndView mav;
	   HttpServletRequest request = null;
	   String requestURL=null;
	   int count=0; //��ܿ� ������ ��� ������ų ����!!  
	   Object[] objArray = joinPoint.getArgs(); // �޼��� ȣ�� �� ���޵� �Ű������� ��ȯ
	   for (Object obj : objArray) {// ��� �Ű����� ����
		   if (obj instanceof HttpServletRequest) {
			   request = (HttpServletRequest) obj;
			   requestURL=request.getRequestURL().toString();
			   for(int i=0;i<exceptList.length;i++) {
				   if(requestURL.endsWith(exceptList[i])) {
					   count++; //���� ��� �߰�
				   }
			   }
		   }
	   }
	   // �α����� �ʿ��� �޼��� ȣ��ø� ���� üũ!
	   if (request != null && count<1) {
		   if (request.getSession().getAttribute("client")==null) {
			   mav=new ModelAndView("/client/login/error");
		   } else {
			   mav = (ModelAndView)joinPoint.proceed();
			   String methodName = joinPoint.getSignature().getName();
		   }
	   } else {
		   mav = (ModelAndView)joinPoint.proceed();
		   String methodName = joinPoint.getSignature().getName();
	   }
	   return mav;
   }
}