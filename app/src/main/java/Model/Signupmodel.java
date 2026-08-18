package Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

//@Data
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class Signupmodel {

  private  String firstName ;
   private String email ;
   private String password ;
  private  String lastName ;
   private int credit ;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
