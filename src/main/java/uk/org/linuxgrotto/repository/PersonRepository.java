package uk.org.linuxgrotto.repository;
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

import uk.org.linuxgrotto.model.Person;
import uk.org.linuxgrotto.repository.base.BaseRepository;

/**
 * Created by jgroth on 07/10/15.
 */
public interface PersonRepository extends BaseRepository<Person> {

    Person findByEmail(String email);
    Person findByUserName(String userName);
}
