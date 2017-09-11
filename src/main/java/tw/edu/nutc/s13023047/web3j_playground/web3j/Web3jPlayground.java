package tw.edu.nutc.s13023047.web3j_playground.web3j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

public class Web3jPlayground {

	@Autowired
	protected Web3j mWeb3;

	protected String mAccount;
	protected String mCredentialFilePath;
	protected String mPassword;

	public void setAccount(String account) {
		mAccount = account;
	}

	public String getAccount() {
		return mAccount;
	}

	public void setCredentialFilePath(String credentialFilePath) {
		mCredentialFilePath = credentialFilePath;
	}

	public String getCredentialFilePath() {
		return mCredentialFilePath;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	public String getPassword() {
		return mPassword;
	}

	public Credentials getCredentials() throws IOException, CipherException {
		return WalletUtils.loadCredentials(mPassword, mCredentialFilePath);
	}

	public String getClientVersion() throws IOException {
		return mWeb3.web3ClientVersion().send().getWeb3ClientVersion();
	}

	public BigInteger getBalance() throws IOException {
		return mWeb3.ethGetBalance(mAccount, DefaultBlockParameter.valueOf("latest")).send().getBalance();
	}

	public TransactionReceipt sendFunds(String account, BigDecimal decimal)
			throws IOException, CipherException, InterruptedException, TransactionTimeoutException {
		Credentials credentials = getCredentials();
		TransactionReceipt result = Transfer.sendFunds(mWeb3, credentials, account, decimal, Convert.Unit.ETHER);
		return result;
	}
}
