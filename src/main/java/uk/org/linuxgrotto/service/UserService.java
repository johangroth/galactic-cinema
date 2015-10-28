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
import uk.org.linuxgrotto.repository.UserRepository;

import java.util.List;

/**
 * Service to access user.
 * Created by jgroth on 30/09/15.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(final Person person) {
        userRepository.save(person);
    }

    public Person findOne(final long id) {
        return userRepository.findOne(id);
    }

    public List<Person> findAll() {
        return userRepository.findAll();
    }

    public Person findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Person findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Person update(Person person) {
        return userRepository.save(person);
    }

}
