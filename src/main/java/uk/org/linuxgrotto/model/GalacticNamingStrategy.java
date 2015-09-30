package uk.org.linuxgrotto.model;
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

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Naming strategy to add _id to foreign keys.
 *
 * Created by jgroth on 30/09/15.
 */
public class GalacticNamingStrategy extends ImprovedNamingStrategy {
    private static final long serialVersionUID = 3082992621330399204L;

        @Override
        public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
                return super.foreignKeyColumnName(propertyName + "_id", propertyEntityName, propertyTableName, referencedColumnName);
        }

}
