package bank.springmvc.controller;

import bank.springmvc.dao.AccountDao;
import bank.springmvc.dao.AlertDao;
import bank.springmvc.dao.TransactionDao;
import bank.springmvc.dao.UserDao;
import bank.springmvc.daoimpl.*;
import bank.springmvc.model.User;

public class BankingApplication {
    public static boolean initialized = false;
    public static UserDao USER_MANAGER;                 // Bank Object
    public static User CURRENT_USER;                    // User currently logged in to bank
    public static OracleDBConnection CONNECTION;        // Connection to DB
    public static AccountDao ACCOUNT_MANAGER;           // Manages all user accounts
    public static TransactionDao TRANSACTION_MANAGER;   // Manages all transactions
    public static AlertDao ALERT_MANAGER;               // Manages all alerts

    // Initializes important program-wide variables when logging in to web app
    public static void startup() {
        BankingApplication.USER_MANAGER = new UserDaoImpl();
        BankingApplication.CONNECTION = new OracleDBConnection();
        BankingApplication.ACCOUNT_MANAGER = new AccountDaoImpl();
        BankingApplication.TRANSACTION_MANAGER = new TransactionDaoImpl();
        BankingApplication.ALERT_MANAGER = new AlertDaoImpl();
        BankingApplication.CURRENT_USER = null;
        initialized = true;
    }
}