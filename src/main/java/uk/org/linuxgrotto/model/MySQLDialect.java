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

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Created by jgroth on 29/09/15.
 */
public class MySQLDialect  extends MySQL5InnoDBDialect {

        private static final String BOOLEAN_COLUMN_DEFINITION = "bit default ";
        public static final String BOOLEAN_DEFAULT_TRUE = BOOLEAN_COLUMN_DEFINITION + "true";
        public static final String BOOLEAN_DEFAULT_FALSE = BOOLEAN_COLUMN_DEFINITION + "false";

        public static final String INT_DEFAULT_ZERO = "int(11) default 0";

        /**
         * Manually set the table type string so that we get our explicit engine, charset and collation
         */
        @Override
        public String getTableTypeString() {
                return " DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci";
        }
}
