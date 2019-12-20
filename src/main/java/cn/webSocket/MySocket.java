package cn.webSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author CTH
 */
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//声明WebSocket的请求名
@ServerEndpoint("/websocket")
public class MySocket {

	// 时间
//	private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//线程安全
	// 一个线程集合存储session线程会话
	private Map<MySocket,String> map = new ConcurrentHashMap<MySocket,String>();
	private Session session;
	
	/**
	 * 建立连接
	 * websocket通讯，浏览器和服务器只会建立一次连接，因此，我们需要在建立连接握手的时候，把这个会话存储到集合里面
	 * 1、一般是用map集合，因为key是不能重复的 
	 * 2、如果是List集合的话，无法区分是自己发的信息还是别人发的信息
	 * 3、集合存在意义，就是实现循环遍历转发给连接服务器的客户端浏览器页面
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("连接已建立,sessionID:"+session.getId());
		// 把当前的session存储起来，以便后来的时候服务器转发数据
		this.session = session;
		System.out.println(+map.size());
		map.put(this,"");

	}
	
	/**
	 * 接受客户端的消息
	 * @param message
	 */ 
	@OnMessage
	public void getMessage(String str, Session session) { 
		for (MySocket s : map.keySet()) {
			System.out.println("this:"+this+"客户端的信息：" + str);
			// 服务器把信息分为是自己的还是其他人发的，再加上时间，然后群发到连接的用户session会话里面
			String time = fmt.format(LocalDateTime.now());
			String info = str + "时间：" + time;
			// 会话异步发送消息
			s.session.getAsyncRemote().sendText(info);
		}
 
	}
	
	@OnClose
	public void onClose() {
		System.out.println("连接关闭!");
	}
	
	@OnError
	public void error(Throwable t) {
		// 添加错误操作
	}

}
