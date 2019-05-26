package com.coffee.common.file;

import java.io.File;

import org.springframework.stereotype.Component;
@Component
public class FileManager {
	
	//���ϸ� �� Ȯ���� �����Ͽ� ��ȯ�ϴ� �޼���
	public String getExt(String path) {
		int lastIndex=path.lastIndexOf(".");
		return path.substring(lastIndex+1,path.length());
	}
	
	//������ ��ο� ������ �����ϴ� �޼���!!
	public boolean save() {
	
		return false;
	}
	
	//���ϸ� �ٲٱ� : ������ ���ο� ���ϸ��� ��¥�� �̿��Ѵ�
	public String renameByDate(File ori,String dir) {
		
		long time=System.currentTimeMillis();
		String filename=time+"."+getExt(ori.getName()); //���� �̸�
		boolean result=ori.renameTo(new File(dir+"/"+filename));
		if(result==false) {
			filename=null;
		}
		return filename;
	}
	
	/*
	public static void main(String[] args) {
		FileManager fm=new FileManager();
		System.out.println(fm.getExt("tony.leemh.png"));
	}*/
}
