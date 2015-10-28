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

/**
 * Integration test.
 *
 * Created by jgroth on 30/09/15.
 */

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uk.org.linuxgrotto.model.Address;
import uk.org.linuxgrotto.model.Person;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@WebAppConfiguration
public class PersonIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public final void whenUserCreated_noExceptions() {
        Person person = new Person();
        person.setUserName("jgroth");
        person.setName("Johan Groth");
        person.setEmail("johangroth1@gmail.com");
        person.setSignupDate(LocalDate.now().toDate());

        Address address = new Address();
        address.setLine1("95 Buckingham Road");
        address.setPostCode("BN1 3RB");
        address.setCounty("East Sussex");
        address.setCountry("United Kingdom");
        person.setAddress(address);
        userService.create(person);
        assertNotNull(person.getId());
    }

    /*
    @Test
    public final void whenUserIsCreated_thenFound() {
        Person person = new Person();
        person.setUserName("jgroth");
        person.setName("Johan Groth");
        person.setEmail("johangroth1@gmail.com");
        person.setSignupDate(LocalDate.now().toDate());

        Address address = new Address();
        address.setLine1("95 Buckingham Road");
        address.setPostCode("BN1 3RB");
        address.setCounty("East Sussex");
        address.setCountry("United Kingdom");
        person.setAddress(address);

        userService.create(person);

        assertNotNull(person.getId());

        Person found = userService.findOne(person.getId());
        assertNotNull(found);
        assertEquals(person, found);
        assertEquals("johangroth1@gmail.com", found.getEmail());
        assertEquals("jgroth", found.getUserName());
        assertNotNull(found.getSignupDate());
        assertNotNull(found.getAddress());
        assertNotNull(found.getAddress().getId());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public final void whenSameUserIsCreatedTwice_thenDataException() {
        Person person = new Person();
        person.setUserName("jgroth");
        userService.create(person);
        userService.create(person);
    }

    @Test
    public final void whenCreatedUserUpdated_NoExceptions() {
        Person person = new Person();
        person.setUserName("jgroth");
        person.setEmail("johangroth1@gmail.com");
        Person personToUpdate = userService.findByEmail("johangroth1@gmail.com");
        assertNotNull(personToUpdate);
        assertEquals(person, personToUpdate);
        personToUpdate.setEmail("johan.groth@crunch.co.uk");
        personToUpdate = userService.update(personToUpdate);
        assertNotNull(personToUpdate);
        assertEquals("johan.groth@crunch.co.uk", personToUpdate.getEmail());
    } */
}
