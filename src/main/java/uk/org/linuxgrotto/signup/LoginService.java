package uk.org.linuxgrotto.signup;
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

import org.springframework.stereotype.Service;

/**
 * Created by jgroth on 27/10/15.
 */
@Service
public class LoginService {

    public String performLogin(LoginCredentials loginCredentials) throws IncorrectCredentialsException {
        if (loginCredentials.getUserName() != null && loginCredentials.getUserName().trim().equalsIgnoreCase("alba")
                && loginCredentials.getPassword() != null && loginCredentials.getPassword().trim().equalsIgnoreCase("pass")) {
            return "success";
        }
        throw new IncorrectCredentialsException();
    }
}
