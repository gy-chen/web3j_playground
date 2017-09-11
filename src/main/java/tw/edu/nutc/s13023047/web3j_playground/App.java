package tw.edu.nutc.s13023047.web3j_playground;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import tw.edu.nutc.s13023047.contracts.Contract_sol_MyToken;
import tw.edu.nutc.s13023047.web3j_playground.web3j.Web3jPlayground;

@EnableAutoConfiguration
@ComponentScan
public class App {

	@Bean
	public Web3j getWeb3j() {
		// default http://localhost:8545
		Web3j web3 = Web3j.build(new HttpService("http://localhost:244"));
		return web3;
	}

	@Bean
	@Primary
	public Web3jPlayground getWeb3jPlayground() {
		Web3jPlayground web3jPlayground = new Web3jPlayground();
		web3jPlayground.setAccount("0x72A56ceaEe7fe6c6CB24eA7fD79Bd1da08fA178d");
		web3jPlayground.setCredentialFilePath(
				"D:/playground/geth/p1/tmp/p1/keystore/UTC--2017-08-25T05-29-08.190367200Z--72a56ceaee7fe6c6cb24ea7fd79bd1da08fa178d");
		web3jPlayground.setPassword("private_chain_password!");
		return web3jPlayground;
	}

	@Autowired
	@Bean
	public Contract_sol_MyToken getMyToken(Web3j web3j, Web3jPlayground web3jPlayground)
			throws IOException, CipherException {
		// https://www.reddit.com/r/ethereum/comments/5g8ia6/attention_miners_we_recommend_raising_gas_limit/
		Contract_sol_MyToken myToken = Contract_sol_MyToken.load("0x6f13756075a2Dd3ACFABB1435C3e7F887460e190", web3j,
				web3jPlayground.getCredentials(), BigInteger.valueOf(20_000_000_000L), BigInteger.valueOf(4_300_000));
		return myToken;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
