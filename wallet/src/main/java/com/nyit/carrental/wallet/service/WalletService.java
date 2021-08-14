package com.nyit.carrental.wallet.service;

import com.nyit.carrental.wallet.exception.WalletException;

public interface WalletService<S,T> {
	
	public T executeWalletService(S req) throws WalletException;

}
