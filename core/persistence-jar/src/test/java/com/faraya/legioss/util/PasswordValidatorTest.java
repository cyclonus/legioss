package com.faraya.legioss.util;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PasswordValidatorTest {

    private PasswordValidator passwordValidator = new PasswordValidator();

    String[] valid = {
            "mkyong1A@",
            "mkYOn12$",
    };

    String[] invalid = {
            "mY1A@",
            "mkyong12@",
            "mkyoNg12*",
            "mkyonG$$",
            "MKYONG12$"
    };

    @Test
    public void ValidPasswordTest() {
        String[] password = valid;
        for (String temp : password) {
            boolean valid = passwordValidator.validate(temp);
            //System.out.println("Password is valid : " + temp + " , " + valid);
            assertEquals(true, valid);
        }

    }

    @Test
    public void InvalidPasswordTest() {
        String[] password = invalid;
        for (String temp : password) {
            boolean valid = passwordValidator.validate(temp);
            System.out.println("Password is valid : " + temp + " , " + valid);
            assertEquals(false, valid);
        }
    }
}