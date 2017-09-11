package tw.edu.nutc.s13023047.web3j_playground.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.edu.nutc.s13023047.web3j_playground.web3j.Web3jPlayground;

@RestController
public class Web3jPlaygroundController {

	@Autowired
	protected Web3jPlayground mWeb3Playground;

	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}

	@RequestMapping("/web3/clientversion")
	public String version() {
		try {
			return mWeb3Playground.getClientVersion();
		} catch (IOException e) {
			return e.getMessage();
		}
	}
}
