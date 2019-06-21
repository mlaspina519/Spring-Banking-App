package bank.springmvc.model;

import bank.springmvc.controller.BankingApplication;

import java.util.Random;

public abstract class User {
  private String firstName;
  private String lastName;
  private String userLogin;
  private String password;
  private String userType;
  private int userID;

  // Constructor with all data values provided
  User(String first, String last, String login, String password, String userType, int userID) {
    this.firstName = first;
    this.lastName = last;
    this.userLogin = login;
    this.password = password;
    this.userType = userType;
    this.userID = userID;
  }

  // Constructor to create a randomly generated User ID
  User(String first, String last, String login, String password, String userType) {
    this.firstName = first;
    this.lastName = last;
    this.userLogin = login;
    this.password = password;
    this.userType = userType;

    Random rand = new Random();
    this.userID = rand.nextInt(Integer.MAX_VALUE);
  }

  // When a user is removed from DB, call delete() to remove all
  // existing accounts associated with user
  public void delete() {
    // Delete all transactions associated with user
    for(int i = 0; i < BankingApplication.TRANSACTION_MANAGER.numberOfTransactions(this.userID); i++) {
      BankingApplication.TRANSACTION_MANAGER.findTransactionsByUserID(this.userID).get(i).deleteTransaction();
    }
    // Delete all accounts associated with user
    for(int i = 0; i < BankingApplication.ACCOUNT_MANAGER.numberOfAccounts(this.userID); i++) {
      BankingApplication.ACCOUNT_MANAGER.findAccountByUserID(this.userID).get(i).deleteAccount();
    }

    // Remove user from DB
    BankingApplication.USER_MANAGER.removeUser(this.userLogin);
  }

  // Getters
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public String getPassword() {
    return password;
  }

  public String getUserType() {
    return userType;
  }

  public int getUserID() {
    return userID;
  }

  // Prints out representation of a User
  @Override
  public String toString() {
    return this.firstName + " " + this.lastName + "\n" +
            this.userLogin + " : " + this.password + "\n" +
            this.userType + " " + this.userID;
  }
}