package thread;

/**
 * <p>Title: tt </p>
 * <p>Description  </p>
 * <p>Company: http://www.hnxianyi.com </p>
 *
 * @author Wjj
 * @CreateDate 2018/5/8 16:08
 */
public class ShareDataVolatile {
	//同时创建十个线程，每个线程自增100次
	//主程序等待3秒让所有线程全部运行完毕后输出最后的count值

	//使用volatile修饰计数变量count
	public volatile static int count=0;
	public static void main(String[] args){
		final ShareDataVolatile data  = new ShareDataVolatile();
		for(int i=0;i<10;i++){
			new Thread(
					new Runnable(){
						public void run(){
							try{
								Thread.sleep(1);
							}catch(InterruptedException e){
								e.printStackTrace();
							}
							for(int j=0;j<100;j++){
								data.addCount();
							}
							System.out.print(count+" ");
						}
					}
			).start();
		}
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println();
		System.out.print("count="+count);
	}
	public void addCount(){
		count++;
	}
}
