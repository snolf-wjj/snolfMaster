package com.snolf.manager.test;

/**
 * <p>Title: Test </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author Wjj
 * @CreateDate 2018/3/20 10:40
 */
class A {
	public void abs() {
		System.out.println("A");
	}
}
class B extends A {
	public void abs() {
//		super.abs();
		System.out.println("B");
	}
}
class C extends B {
	public void abs(){
//		super.abs();
		System.out.println("C");
	}
}
public class Test {
	public static void main(String[] args) {
		A a = new C();
		a.abs();
	}
}
