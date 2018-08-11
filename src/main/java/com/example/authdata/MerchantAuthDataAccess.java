/**
 * 
 */
package com.example.authdata;;

/**
 * @author rajeshsaw
 *
 */
public class MerchantAuthDataAccess {
	private boolean chargeback;
	private boolean transactions;
	private boolean admin;
	private boolean manageAccount;
	/**
	 * @return the chargeback
	 */
	public boolean isChargeback() {
		return chargeback;
	}
	/**
	 * @param chargeback the chargeback to set
	 */
	public void setChargeback(boolean chargeback) {
		this.chargeback = chargeback;
	}
	/**
	 * @return the transactions
	 */
	public boolean isTransactions() {
		return transactions;
	}
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(boolean transactions) {
		this.transactions = transactions;
	}
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/**
	 * @return the manageAccount
	 */
	public boolean isManageAccount() {
		return manageAccount;
	}
	/**
	 * @param manageAccount the manageAccount to set
	 */
	public void setManageAccount(boolean manageAccount) {
		this.manageAccount = manageAccount;
	}
	@Override
	public String toString() {
		return "MerchantAuthDataAccess [chargeback=" + chargeback + ", transactions=" + transactions + ", admin="
				+ admin + ", manageAccount=" + manageAccount + "]";
	}
	
}
