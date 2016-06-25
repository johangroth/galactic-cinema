package uk.org.linuxgrotto.service;
/*
 * galactic-cinema
 * Copyright 2015 Johan Groth
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.org.linuxgrotto.persistence.model.Person;
import uk.org.linuxgrotto.signup.IncorrectCredentialsException;
import uk.org.linuxgrotto.signup.LoginCredentials;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by jgroth on 07/12/15.
 */
public class LoginServiceUnitTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private LoginService loginService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenLoginWithValidData_noExceptions() throws IncorrectCredentialsException, InvalidKeySpecException, NoSuchAlgorithmException {
        LoginCredentials loginCredentials = new LoginCredentials();

        loginCredentials.setUserName("TESTUSERNAME");
        loginCredentials.setPassword("mickey");
        Person person = new Person();
        person.setUserName("TESTUSERNAME");
        person.setPassword("1000:BZTzAvctlKNDxahVpK/edcwFH83in56C:1KggQ8OiwnDgjo6TcWteHfP1k7cfdPBL32l32S6C256GUTxkXPBvLhWZBfXxjDs06J1O8r39+TeQAMWeqo7UIw==");

        when(personService.findByUserName(anyString())).thenReturn(person);
        String result = loginService.performLogin(loginCredentials);
        assertEquals("success", result);
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void whenLoginWithInvalidUser_Exception() throws Exception {
        LoginCredentials loginCredentials = new LoginCredentials();

        loginCredentials.setUserName("TESTUSERNAME");
        loginCredentials.setPassword("TEST_PASSWORD");

        when(personService.findByUserName(anyString())).thenReturn(null);
        loginService.performLogin(loginCredentials);
    }

    @Test(expected = IncorrectCredentialsException.class)
    public void whenLoginWithInvalidPassword_Exception() throws Exception {
        LoginCredentials loginCredentials = new LoginCredentials();

        loginCredentials.setUserName("TESTUSERNAME");
        loginCredentials.setPassword("mickey1");
        Person person = new Person();
        person.setUserName("TESTUSERNAME");
        person.setPassword("1000:BZTzAvctlKNDxahVpK/edcwFH83in56C:1KggQ8OiwnDgjo6TcWteHfP1k7cfdPBL32l32S6C256GUTxkXPBvLhWZBfXxjDs06J1O8r39+TeQAMWeqo7UIw==");

        when(personService.findByUserName(anyString())).thenReturn(person);
        loginService.performLogin(loginCredentials);
    }
}
