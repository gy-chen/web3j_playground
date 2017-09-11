package tw.edu.nutc.s13023047.web3j_playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@EnableAutoConfiguration
@ComponentScan
public class App {

	@Bean
	public Web3j getWeb3j() {
		// default http://localhost:8545
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8540"));
		return web3;
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
