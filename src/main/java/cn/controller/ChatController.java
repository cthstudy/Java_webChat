package cn.controller;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@RequestMapping("/welcome")
	public ModelAndView welcome(ModelAndView md) {
		md.setViewName("webChat"); 
		return md;
	}
	
	/**
	 * 客户端的消息内容
	 * @param md
	 * @param info
	 * @return
	 */
	@RequestMapping("/chatInfo")
	public ModelAndView chatInfo(ModelAndView md,
			@RequestParam(name="info",defaultValue="") String info) {
		System.out.println(info);
		md.setViewName("chat");
		return md;
	}
	
}
