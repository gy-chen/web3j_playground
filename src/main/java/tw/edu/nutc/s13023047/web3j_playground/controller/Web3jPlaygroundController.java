package tw.edu.nutc.s13023047.web3j_playground.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;

import tw.edu.nutc.s13023047.contracts.Contract_sol_MyToken;
import tw.edu.nutc.s13023047.web3j_playground.web3j.Web3jPlayground;

@RestController
public class Web3jPlaygroundController {

	@Autowired
	protected Web3jPlayground mWeb3Playground;
	@Autowired
	protected Contract_sol_MyToken mMyToken;

	@RequestMapping("/")
	public String home() {
		return "Hello World! " + mWeb3Playground.getAccount();
	}

	@RequestMapping("/web3/clientversion")
	public String version() {
		try {
			return mWeb3Playground.getClientVersion();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/web3/balance")
	public String balance() {
		try {
			return String.format("%s has %s wei", mWeb3Playground.getAccount(), mWeb3Playground.getBalance());
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	@RequestMapping("/web3/sendFunds")
	public String sendFunds() {
		String account = "0x42d37Cb430f4A0fFe06DeDeD30Ab688ef3D2d180";
		BigDecimal amount = BigDecimal.valueOf(10);
		TransactionReceipt result = null;

		try {
			result = mWeb3Playground.sendFunds(account, amount);
		} catch (IOException | CipherException | InterruptedException | TransactionTimeoutException e) {
			return e.getMessage();
		}

		return String.format("Funds transfered: \nBlock Number: %s\nTransaction Hash:%s", result.getBlockNumber(),
				result.getTransactionHash());
	}

	@RequestMapping("/web3/sendToken")
	public String sendToken() {
		String account = "0x42d37Cb430f4A0fFe06DeDeD30Ab688ef3D2d180";
		BigInteger amount = BigInteger.valueOf(10);

		TransactionReceipt result = null;
		try {
			result = mMyToken.transfer(new Address(account), new Uint256(amount)).get();
		} catch (InterruptedException | ExecutionException e) {
			return e.getMessage();
		}

		return String.format("Token transfered: \nBlock Number: %s\nTransaction Hash:%s", result.getBlockNumber(),
				result.getTransactionHash());
	}
}
