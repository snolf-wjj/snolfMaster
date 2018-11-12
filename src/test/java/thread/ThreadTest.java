package thread;

/**
 * <p>Title: ThreadTest </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author Wjj
 * @CreateDate 2018/4/25 17:37
 */
public class ThreadTest {
	public int val = 0;
	public static void main(String[] args) {
		ThreadTest tes = new ThreadTest();
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(){
				@Override
				public void run() {
					tes.val++;
					System.out.println("1:" + tes.val);
				}
			};
			t.start();
			System.out.println("3:" + tes.val);
		}
		System.out.println("2:" + tes.val);
	}
}
