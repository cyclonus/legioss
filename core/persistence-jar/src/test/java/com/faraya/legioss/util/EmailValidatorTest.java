package com.faraya.legioss.util;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * Created by fabrizzio on 9/4/15.
 */
public class EmailValidatorTest {

    String[] valid = {
            "me@gmail.com",
            "me@me.co.cr",
            "me@yahoo.com",
            "me-100@me.com",
            "me-100@me.net",
            "me+100@me.net",
            "me@1.net",
            "me@g-mail.net"
    };


    String[] invalid = {
            "me@.gmail.com",
            "me@.co.cr",
            "me@%yahoo.com",
            "me..me@me.com",
            "me.@me.net",
            "me@100@me.net",
           // "me-@lol.net"
    };


    String[] mkYongValidInput = {
            "faraya@fabsoft.com",
            "mkyong@yahoo.com",
            "mkyong-100@yahoo.com",
            "mkyong.100@yahoo.com",
            "mkyong111@mkyong.com",
            "mkyong-100@mkyong.net",
            "mkyong.100@mkyong.com.au",
            "mkyong@1.com",
            "mkyong@gmail.com.com",
            "mkyong+100@gmail.com",
            "mkyong-100@yahoo-test.com"
    };


   String[] mkYonginvalidInput = {
           "mkyong",
           "mkyong@.com.my",
           "mkyong123@gmail.a",
           "mkyong123@.com",
           "mkyong123@.com.com",
           ".mkyong@mkyong.com",
           "mkyong()*@gmail.com",
           "mkyong@%*.com",
           "mkyong..2002@gmail.com",
           "mkyong.@gmail.com",
           "mkyong@mkyong@gmail.com",
           "mkyong@gmail.com.1a"
   };

    EmailValidator validator = new EmailValidator();

    @Test
    public void testValidEmails(){
        for(String e:mkYongValidInput){
          assertTrue(e + " is invalid ", validator.validate(e));
        }
    }

    @Test
    public void testInvalidEmails(){
        for(String e:mkYonginvalidInput){
            assertFalse( e+ " should be invalid ", validator.validate(e));
        }
    }

}
