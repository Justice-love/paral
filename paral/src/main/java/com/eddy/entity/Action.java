/**
 * 
 * @creatTime 上午10:20:46
 * @author XuYi
 */
package com.eddy.entity;


/**
 * @author XuYi
 *
 */
public enum Action {

	/**
	 * 单击
	 */
	CLICK,
	/**
	 * 双击
	 */
	DOUBLECLICK,
	/**
	 * 右击
	 */
	CONTEXTCLICK,
	/**
	 * 设置值
	 */
	SETVALUE,
	/**
	 * 点击回车键
	 */
	ENTER,
	/**
	 * 断言
	 */
	ASSERT;
	
//	public static void main(String[] args) {
//		BlockingQueue<Object> queue = new LinkedBlockingQueue<Object>();
//		init(queue);
//		try {
//			queue.size();
//			Object obj = queue.poll(30, TimeUnit.SECONDS);
//			if (null != obj) {
//				throw new RuntimeException();
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	private static void init(BlockingQueue<Object> queue){
//		queue.offer(new Object());
//		queue.offer(new Object());
//		queue.offer(new Object());
//	}
}
