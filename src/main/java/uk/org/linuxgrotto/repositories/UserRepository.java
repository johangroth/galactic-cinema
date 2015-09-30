package uk.org.linuxgrotto.repositories;
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

import org.springframework.stereotype.Repository;
import uk.org.linuxgrotto.model.AbstractJpaDao;
import uk.org.linuxgrotto.model.User;
import uk.org.linuxgrotto.model.UserDao;

/**
 * Repository for User.
 *
 * Created by jgroth on 29/09/15.
 */
@Repository
public class UserRepository extends AbstractJpaDao<User> implements UserDao {

    public UserRepository() {
        super();
        setClazz(User.class);
    }

}
