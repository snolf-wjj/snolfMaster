package com.snolf.common.model;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;

/**
 * 系统信息
 * User：wangjunjie
 * Date: 2017/11/1
 * Time: 15:11
 */
public class SystemInfo {
	/**系统名*/
	private String os_name;
	/**系统IP*/
	private String os_ip ;
	/**系统架构*/
	private String os_arch ;
	/**系统版本号*/
	private String os_version ;
	/**系统MAC地址*/
	private String os_mac;
	/**系统时间*/
	private Date os_date;
	/**系统CPU个数*/
	private Integer os_cpus ;
	/**系统用户名*/
	private String os_user_name;

	/**Java的运行环境版本*/
	private String java_version ;

	/**java 平台*/
	private String sun_desktop ;
	/**服务器名*/
	private String server_name;
	/**服务器端口*/
	private Integer server_port;
	/**服务器地址*/
	private String server_addr;
	/**获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址*/
	private String server_host;
	/**服务协议*/
	private String server_protocol;

	public static SystemInfo SYSTEMINFO;

	public static SystemInfo getInstance(){
		if(SYSTEMINFO == null){
			SYSTEMINFO = new SystemInfo();
		}
		return SYSTEMINFO;
	}


	public static SystemInfo getInstance(HttpServletRequest request){
		if(SYSTEMINFO == null){
			SYSTEMINFO = new SystemInfo(request);
		}
		else {
			SYSTEMINFO.ServerInfo(request);
		}
		return SYSTEMINFO;
	}

	private SystemInfo() {
		super();
		init();
	}

	private SystemInfo(HttpServletRequest request) {
		super();
		init();
		/**
		 * 额外信息
		 */
		ServerInfo(request);
	}

	/**
	 * 输出信息
	 */
	public void PrintInfo(){

		System.out.println("操作系统的名称："+this.os_name);
		System.out.println("操作系统的IP地址："+this.os_ip);
		System.out.println("操作系统的构架："+this.os_arch);
		System.out.println("操作系统的版本："+this.os_version);
		System.out.println("操作系统的时间："+this.os_date);
		System.out.println("服务器的cpu数量："+this.os_cpus);

		System.out.println("服务器的名称："+this.os_user_name);

		System.out.println("Java的版本号："+this.java_version);
		System.out.println("Java的运行环境："+this.sun_desktop);

		System.out.println("服务器的名称："+this.server_name);
		System.out.println("服务器的端口号："+this.server_port);
		System.out.println("服务器的地址："+this.server_addr);
		System.out.println("客户端的IP："+this.server_host);
		System.out.println("服务协议："+this.server_protocol);
	}

	/**
	 * 初始化基本属性
	 */
	private void init(){
		Properties props=System.getProperties();

		this.java_version = props.getProperty("java.version");
		this.os_name = props.getProperty("os.name");
		this.os_arch = props.getProperty("os.arch");
		this.os_version = props.getProperty("os.version");
		this.os_user_name = props.getProperty("user.name");

		this.sun_desktop = props.getProperty("sun.desktop");

		this.os_date = new Date();
		this.os_cpus = Runtime.getRuntime().availableProcessors();

		try {
			ipMac();
		} catch (Exception e) {
			this.os_ip = "";
			this.os_mac = "";
		}
	}

	/**
	 * 获取ip和mac地址
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private void ipMac() throws Exception{
		InetAddress address = InetAddress.getLocalHost();
		NetworkInterface ni = NetworkInterface.getByInetAddress(address);
		ni.getInetAddresses().nextElement().getAddress();
		byte[] mac = ni.getHardwareAddress();
		String sIP = address.getHostAddress();
		String sMAC = "";
		Formatter formatter = new Formatter();
		for (int i = 0; i < mac.length; i++) {
			sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
					(i < mac.length - 1) ? "-" : "").toString();

		}
		SystemInfo.this.os_ip = sIP;
		SystemInfo.this.os_mac = sMAC;
	}

	/**
	 * 获取服           务器信息
	 * @param request
	 */
	public void ServerInfo(HttpServletRequest request){
		this.server_name = request.getServerName();
		this.server_port = request.getServerPort();
		this.server_addr = request.getRemoteAddr();
		this.server_host = request.getRemoteHost();
		this.server_protocol = request.getProtocol();
	}


	public String getOs_name() {
		return os_name;
	}

	public String getOs_arch() {
		return os_arch;
	}

	public String getOs_version() {
		return os_version;
	}

	public String getOs_ip() {
		return os_ip;
	}

	public String getOs_mac() {
		return os_mac;
	}

	public Date getOs_date() {
		return os_date;
	}

	public Integer getOs_cpus() {
		return os_cpus;
	}

	public String getOs_user_name() {
		return os_user_name;
	}

	public String getJava_version() {
		return java_version;
	}

	public String getSun_desktop() {
		return sun_desktop;
	}

	public String getServer_name() {
		return server_name;
	}

	public Integer getServer_port() {
		return server_port;
	}

	public String getServer_addr() {
		return server_addr;
	}

	public String getServer_host() {
		return server_host;
	}

	public String getServer_protocol() {
		return server_protocol;
	}

}
