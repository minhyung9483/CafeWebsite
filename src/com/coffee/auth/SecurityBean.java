package com.coffee.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//������ �����ͺ��̽��� ��й�ȣ�� �������� �Ϲ� �ؽ�Ʈ�� ��������, ���� ������ ��ȣȭ�� �����ͷ� �־��...!!!
public class SecurityBean {
	//�Ϲ� �ؽ�Ʈ�� �ؽð����� ��ȯ�Ͽ� ��ȯ�ϴ� �޼���...
	public String textToHash(String password) {
		StringBuilder sb = new StringBuilder();
		try {
			//���� ����� �˰��� �����ϱ�  
			//���� : 224, 256, 384, 512
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			md.update(password.getBytes());
			
			//��ȣȭ �� ����Ʈ ��ȯ�ޱ�...
			byte[] data =md.digest();
			for(int i = 0; i < data.length; i++) {
				sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
