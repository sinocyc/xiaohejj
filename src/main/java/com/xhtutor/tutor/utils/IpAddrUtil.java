package com.xhtutor.tutor.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 获取原始客户端ip
 * @author Ethan
 *
 */
public class IpAddrUtil {

	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	private static String getCityByIp(String ip) throws Exception {
		String requestUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
		HttpRequestor httpRequestor = new HttpRequestor();
		String result = httpRequestor.doGet(requestUrl);
		return result;
	}
	
	public static String getCurrCityCode(HttpServletRequest request) {
		String city_id = null;
		try {
			String ipAddr = getIpAddr(request);
			String jsonStr = getCityByIp(ipAddr);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode node = objectMapper.readValue(jsonStr, JsonNode.class);
			JsonNode cityIdNode = node.get("data").get("city_id");
			city_id = null == cityIdNode ? null : cityIdNode.asText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return city_id;
	}
	
}
