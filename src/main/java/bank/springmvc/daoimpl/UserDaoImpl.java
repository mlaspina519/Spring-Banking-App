package bank.springmvc.daoimpl;

import bank.springmvc.controller.BankingApplication;
import bank.springmvc.dao.UserDao;
import bank.springmvc.model.Customer;
import bank.springmvc.model.Employee;
import bank.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

//    @Autowired
//    DataSource datasource;
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    // Removes a user from DB by specified user ID
    public void removeUser(String userLogin) {
        try {
            BankingApplication.CONNECTION.select("delete from Bank_users" +
                    " where user_login='" + userLogin + "'");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // Adds a user to DB by specified User object
    public void addUser(User user) {
        try {
            BankingApplication.CONNECTION.select("insert into bank_users " +
                    "values('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getUserLogin() + "', '" + user.getPassword()
                    + "', '" + user.getUserType() + "', '" + user.getUserID() + "')");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Updates a user with new first, last, login, pass
    // currentUserLogin is used to find the user to update
    public boolean updateUserInfo(String first, String last, String userLogin, String pass, String currentUserLogin) {
        try {
            ResultSet rs = BankingApplication.CONNECTION.select("select user_login from bank_users where " +
                    "user_login = '" + currentUserLogin + "'");

            if(rs.next()) {
                //System.out.println(rs.getString("user_login"));
                // System.out.println(userLogin);
                if (rs.getString("user_login").equals(userLogin)) {
                    return false;
                } else {
                    BankingApplication.CONNECTION.select("update bank_users " +
                            "set first_name='" + first + "', last_name='" + last + "', user_login='" +
                            userLogin + "', user_pass='" + pass + "'" + " where user_login='" + currentUserLogin + "'");

                    return true;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    // Finds user based on login name, returns User object
    public User findUser(String userLogin) {
        try {
            ResultSet rs = BankingApplication.CONNECTION.select("select * from Bank_users where " +
                    "user_login='" + userLogin + "'");

            // If no user exists for userLogin, return null
            if(!rs.next()) {
                return null;
            } else {
                if (rs.getString("user_type").equals("Customer")) {
                    // If Customer
                    return new Customer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("user_login"), rs.getString("user_pass"),
                            rs.getInt("user_id"));
                } else if (rs.getString("user_type").equals("Employee")) {
                    // Else if Employee
                    return new Employee(rs.getString("first_name"), rs.getString("last_name"), rs.getString("user_login"), rs.getString("user_pass"),
                            rs.getInt("user_id"));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}