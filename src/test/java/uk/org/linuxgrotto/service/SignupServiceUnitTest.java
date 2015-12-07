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
import uk.org.linuxgrotto.model.Person;
import uk.org.linuxgrotto.signup.IncorrectCredentialsException;
import uk.org.linuxgrotto.signup.LoginCredentials;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Integration test.
 *
 * Created by jgroth on 30/09/15.
 */

public class SignupServiceUnitTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private SignupService signupService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenPersonServiceIsGivenValidData_noException() throws Exception {
        LoginCredentials loginCredentials = new LoginCredentials();

        loginCredentials.setUserName("TESTUSERNAME");
        loginCredentials.setPassword("TEST_PASSWORD");

        when(personService.findByUserName(anyString())).thenReturn(null);
        String result = signupService.performSignup(loginCredentials);
        assertEquals("success", result);

    }

    @Test(expected = IncorrectCredentialsException.class)
    public void whenPersonServiceIsGivenInvalidData_Exception() throws IncorrectCredentialsException, InvalidKeySpecException, NoSuchAlgorithmException {
        LoginCredentials loginCredentials = new LoginCredentials();
        Person person = new Person();
        person.setUserName("TESTUSERNAME");

        when(personService.findByUserName(anyString())).thenReturn(person);
        // This should throw exception
        signupService.performSignup(loginCredentials);
    }
}
