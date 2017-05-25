package com.zs.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {

	private static Logger logger = LoggerFactory.getLogger(ComputeController.class);

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
		ServiceInstance instance = client.getLocalServiceInstance();
		Integer r = a + b;
		logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);

		testLogback();

		return r;
	}

	private void testLogback() {
		logger.trace("======trace");
		logger.debug("======debug");
		logger.info("======info");
		logger.warn("======warn");
		logger.error("======error");
		String name = "Aub";
		String message = "3Q";
		String[] fruits = new String[] { "apple", "banana" };
		// logback提供的可以使用变量的打印方式，结果为"Hello,Aub!"
		logger.info("Hello,{}!", name);
		// 可以有多个参数,结果为“Hello,Aub! 3Q!”
		logger.info("Hello,{}!   {}!", name, message);
		// 可以传入一个数组，结果为"Fruit: apple,banana"
		logger.info("Fruit:{},{}", fruits);
	}

}
