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
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.org.linuxgrotto.model.Address;
import uk.org.linuxgrotto.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class UserIntegrationTest {

    @Autowired
    private UserService userService;

    @After
    public void tearDown() {

    }

    @Test
    public final void whenUserCreated_noExceptions() {
        User user = new User();
        user.setUserName("jgroth");
        user.setName("Johan Groth");
        user.setEmail("johangroth1@gmail.com");
        user.setSignupDate(LocalDate.now().toDate());

        Address address = new Address();
        address.setLine1("95 Buckingham Road");
        address.setPostCode("BN1 3RB");
        address.setCounty("East Sussex");
        address.setCountry("United Kingdom");
        user.setAddress(address);
        userService.create(user);
        assertNotNull(user.getId());
    }

    @Test
    public final void whenUserIsCreated_thenFound() {
        User user = new User();
        user.setUserName("jgroth");
        user.setName("Johan Groth");
        user.setEmail("johangroth1@gmail.com");
        user.setSignupDate(LocalDate.now().toDate());

        Address address = new Address();
        address.setLine1("95 Buckingham Road");
        address.setPostCode("BN1 3RB");
        address.setCounty("East Sussex");
        address.setCountry("United Kingdom");
        user.setAddress(address);

        userService.create(user);

        assertNotNull(user.getId());

        User found = userService.findOne(user.getId());
        assertNotNull(found);
        assertEquals(user, found);
        assertNotNull(found.getSignupDate());
        assertNotNull(found.getAddress());
        assertNotNull(found.getAddress().getId());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public final void whenSameUserIsCreatedTwice_thenDataException() {
        User user = new User();
        user.setUserName("jgroth");
        userService.create(user);
        userService.create(user);
    }
}
