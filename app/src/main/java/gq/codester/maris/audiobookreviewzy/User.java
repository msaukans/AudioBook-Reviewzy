package gq.codester.maris.audiobookreviewzy;

/**
 * Created by m on 15/09/17.
 */

public class User {

    String fname;
    String sname;
    String login;
    String password;
    String email;
    String dob;


    public User(String fname, String sname, String login, String password, String email, String dob) {
        this.fname = fname;
        this.sname = sname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }



    @Override
    public String toString() {
        return "User{" + "fname='" + fname + '\'' + ", sname='" + sname + '\'' +
                ", login='" + login + '\'' + ", password='" + password + '\'' +
                ", email='" + email + '\'' + ", dob='" + dob + '\'' + '}';
    }


}
