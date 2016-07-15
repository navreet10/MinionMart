package Util;

import java.security.NoSuchAlgorithmException;

public class PasswordGen {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String salt1=PasswordUtil.getSalt();
		String salt2=PasswordUtil.getSalt();
		String salt3=PasswordUtil.getSalt();
		String salt4=PasswordUtil.getSalt();
		String salt5=PasswordUtil.getSalt();
		String salt6=PasswordUtil.getSalt();
		
		System.out.println(salt1);
		System.out.println(salt2);
		System.out.println(salt3);
		System.out.println(salt4);
		System.out.println(salt5);
		System.out.println(salt6);
		
		String pwd1= PasswordUtil.hashPasswordPlusSalt("admin123!", salt1);

		String pwd2= PasswordUtil.hashPasswordPlusSalt("can123!", salt2);

		String pwd3= PasswordUtil.hashPasswordPlusSalt("dan123!", salt3);

		String pwd4= PasswordUtil.hashPasswordPlusSalt("van123!", salt4);

		String pwd5= PasswordUtil.hashPasswordPlusSalt("tan123!", salt5);
		
		String pwd6= PasswordUtil.hashPasswordPlusSalt("pan123!", salt6);
		
		System.out.println(pwd1);
		System.out.println(pwd2);
		System.out.println(pwd3);
		System.out.println(pwd4);
		System.out.println(pwd5);
		System.out.println(pwd6);
		
		
	}

}
