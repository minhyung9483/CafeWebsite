package com.coffee.common.board;

import javax.servlet.http.HttpServletRequest;

public class MyPager {
   private int currentPage = 1; // ���� ������ ���� �ִ� ������
   private int totalRecord; // �� ���ڵ� ��
   private int pageSize = 4; // ������ �� ������ ���ڵ� ��
   private int totalPage; // �� ������ ��
   private int blockSize = 10;
   private int firstPage;
   private int lastPage = firstPage + (blockSize - 1);
   private int curPos;
   private int num;
   
   

   public int getCurrentPage() {
      return currentPage;
   }



   public void setCurrentPage(int currentPage) {
      this.currentPage = currentPage;
   }



   public int getTotalRecord() {
      return totalRecord;
   }



   public void setTotalRecord(int totalRecord) {
      this.totalRecord = totalRecord;
   }



   public int getPageSize() {
      return pageSize;
   }



   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }



   public int getTotalPage() {
      return totalPage;
   }



   public void setTotalPage(int totalPage) {
      this.totalPage = totalPage;
   }



   public int getBlockSize() {
      return blockSize;
   }



   public void setBlockSize(int blockSize) {
      this.blockSize = blockSize;
   }



   public int getFirstPage() {
      return firstPage;
   }



   public void setFirstPage(int firstPage) {
      this.firstPage = firstPage;
   }



   public int getLastPage() {
      return lastPage;
   }



   public void setLastPage(int lastPage) {
      this.lastPage = lastPage;
   }



   public int getCurPos() {
      return curPos;
   }



   public void setCurPos(int curPos) {
      this.curPos = curPos;
   }



   public int getNum() {
      return num;
   }



   public void setNum(int num) {
      this.num = num;
   }



   // ȣ��ø���, ����¡ ó�� ���������� ����� ��
   public void init(HttpServletRequest request, int totalRecord) {
      // ����ڰ� ������ ��ȣ�� ������.. �� ���� ��ȣ�� ���� �������� ��ü�ؾ���
      if (request.getParameter("currentPage") != null) {
         currentPage = Integer.parseInt(request.getParameter("currentPage"));
      }
      this.totalRecord=totalRecord;
      totalPage = (int) Math.ceil((float) totalRecord / pageSize);
      firstPage= currentPage - (currentPage - 1) % blockSize;
      lastPage= firstPage + (blockSize - 1);
      curPos=(currentPage-1)*pageSize;
      num=totalRecord-curPos;
   }
}