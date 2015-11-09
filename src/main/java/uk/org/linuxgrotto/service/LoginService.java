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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.org.linuxgrotto.model.Person;
import uk.org.linuxgrotto.security.PasswordHash;
import uk.org.linuxgrotto.signup.IncorrectCredentialsException;
import uk.org.linuxgrotto.signup.LoginCredentials;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by jgroth on 27/10/15.
 */
@Service
public class LoginService {

    @Autowired
    private PersonService personService;

    public String performLogin(LoginCredentials loginCredentials) throws IncorrectCredentialsException, InvalidKeySpecException, NoSuchAlgorithmException {
        Person person = personService.findByUserName(loginCredentials.getUserName());
        if (person == null) {
            throw new IncorrectCredentialsException();
        }
        PasswordHash passwordHash = new PasswordHash();
        if (loginCredentials.getPassword() != null && passwordHash.validatePassword(loginCredentials.getPassword(), person.getPassword())) {
            return "success";
        }
        throw new IncorrectCredentialsException();
    }
}
