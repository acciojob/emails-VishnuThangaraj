package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(this.password.equals(oldPassword) && newPassword.length() >= 8){
            // check the newPassword
            boolean uppercase = false, lowercase = false, digit = false, speicalCharacter = false;
            for(char letter : newPassword.toCharArray()) {
                if(letter >= 'a' && letter <= 'z')
                    lowercase = true;
                else if(letter >= 'A' && letter <= 'Z')
                    uppercase = true;
                else if(letter >= '0' && letter <= '9')
                    digit = true;
                else
                    speicalCharacter = true;
            }

            if(uppercase && lowercase && digit && speicalCharacter){
                this.password = newPassword;
            }
        }
    }
}
