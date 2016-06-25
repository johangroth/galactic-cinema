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

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uk.org.linuxgrotto.persistence.model.Address;
import uk.org.linuxgrotto.persistence.model.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jgroth on 02/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-config/test-context.xml")
@WebAppConfiguration
@ActiveProfiles("integration-test")
public class PersonIT extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger log = LoggerFactory.getLogger(PersonIT.class);

    @Autowired
    private PersonService personService;

    @Test
    public final void whenPersonIsCreated_noExceptions() {
        Person person = new Person();
        person.setUserName("johang");
        person.setName("Johan Groth");
        person.setEmail("johangroth1@gmail.com");
        person.setSignupDate(LocalDate.now().toDate());

        Address address = new Address();
        address.setLine1("97 Buckingham Road");
        address.setPostCode("BN1 3RB");
        address.setCounty("East Sussex");
        address.setCountry("United Kingdom");
        person.setAddress(address);
        personService.create(person);
        assertNotNull(person.getId());
        log.info("person is {} ", person.toString());
    }

    @Test
    public final void whenPersonIsCreated_thenFound() {
        Person person = new Person();
        person.setUserName("johang");
        person.setName("Johan Groth");
        person.setEmail("johangroth1@gmail.com");
        person.setSignupDate(LocalDate.now().toDate());

        Address address = new Address();
        address.setLine1("95 Buckingham Road");
        address.setPostCode("BN1 3RB");
        address.setCounty("East Sussex");
        address.setCountry("United Kingdom");
        person.setAddress(address);

        personService.create(person);

        assertNotNull(person.getId());

        Person found = personService.findOne(person.getId());
        assertNotNull(found);
        assertEquals(person, found);
        assertEquals("johangroth1@gmail.com", found.getEmail());
        assertEquals("johang", found.getUserName());
        assertNotNull(found.getSignupDate());
        assertNotNull(found.getAddress());
        assertNotNull(found.getAddress().getId());
        log.info("person is {} ", person.toString());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public final void whenSamePersonIsCreatedTwice_thenDataException() {
        Person person = new Person();
        person.setUserName("johang");
        personService.create(person);
        log.info("just created person");
        person = new Person();
        person.setUserName("johang");
        personService.create(person);
        log.info("should not be reached");
    }

    @Test
    public final void whenCreatedPersonUpdated_NoExceptions() {
        Person person = new Person();
        person.setUserName("johang");
        person.setEmail("johangroth1@gmail.com");
        personService.create(person);
        Person personToUpdate = personService.findByEmail("johangroth1@gmail.com");
        assertNotNull(personToUpdate);
        assertEquals(person, personToUpdate);
        personToUpdate.setEmail("johan.groth@crunch.co.uk");
        personToUpdate = personService.update(personToUpdate);
        assertNotNull(personToUpdate);
        assertEquals("johan.groth@crunch.co.uk", personToUpdate.getEmail());
        log.info("person is {} ", person.toString());
    }

}
