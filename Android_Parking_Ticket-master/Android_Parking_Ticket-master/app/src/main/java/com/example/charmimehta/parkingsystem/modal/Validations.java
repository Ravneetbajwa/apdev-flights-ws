package com.example.charmimehta.parkingsystem.modal;

public class Validations {


    public boolean emailValidation(String email)
    {
        email = email.trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern) && email.length() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean phoneNumberValidation(String no)
    {
        no = no.trim();

        String noPattern = "^[0-9]{10,13}$";
        if (no.matches(noPattern) && no.length() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

public boolean isEmptyField(String userName, String email, String psw ,String rePsw, String contact, String carNo)
{
    if(userName.isEmpty() && email.isEmpty() && psw.isEmpty() && rePsw.isEmpty() && contact.isEmpty() && carNo.isEmpty())
    {
        return false;
    }
    else {
        return true;
    }

}

}
