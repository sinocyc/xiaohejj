package com.xhtutor.tutor.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhtutor.tutor.utils.HttpRequestor;

public class TestIpUtil {
	public static void main(String[] args) throws Exception {
		HttpRequestor httpRequestor = new HttpRequestor();
		String result = httpRequestor.doGet("http://ip.taobao.com/service/getIpInfo.php?ip=36.149.224.129");
		System.out.println(result);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readValue(result, JsonNode.class);
		System.out.println(node);
		JsonNode data = node.get("data");
		JsonNode city_id = data.get("city_id");
		System.out.println(city_id.asText());
		System.out.println("success");
	}
}
