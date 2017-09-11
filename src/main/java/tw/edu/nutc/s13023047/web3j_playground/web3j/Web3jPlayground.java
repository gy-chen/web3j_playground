package tw.edu.nutc.s13023047.web3j_playground.web3j;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

@Component
public class Web3jPlayground {

	@Autowired
	protected Web3j mWeb3;

	public String getClientVersion() throws IOException {
		return mWeb3.web3ClientVersion().send().getWeb3ClientVersion();
	}
}
