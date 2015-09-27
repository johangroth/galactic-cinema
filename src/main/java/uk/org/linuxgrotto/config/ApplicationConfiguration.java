package uk.org.linuxgrotto.config;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgroth on 10/08/15.
 */
@Configuration
@ComponentScan(basePackages = "uk.org.linuxgrotto")
public class ApplicationConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);
	private static final String PROPERTIES_FILE = "application.properties";
	private static final String PROPERTIES_URL = "file:///opt/services/galactic-cinema/" + PROPERTIES_FILE;

    /**
     * This configuration is needed for @Value annotation to work with ${...} place holders.
     * The properties file in the project/war folder will take precedence before the default one ('opt' folder),
     * should they both declare the same properties.
     * @return The property configurer.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
    	PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

    	pspc.setIgnoreResourceNotFound(true);
    	pspc.setIgnoreUnresolvablePlaceholders(true);

    	List<AbstractFileResolvingResource> resourceList = new ArrayList<>();

    	ClassPathResource classPathResource = new ClassPathResource(PROPERTIES_FILE);
    	resourceList.add(classPathResource);
    	UrlResource urlResource;
    	try {
    		urlResource = new UrlResource(PROPERTIES_URL);
    		resourceList.add(urlResource);
    	} catch (MalformedURLException e) {
    		log.error("The url {0} is malformed", PROPERTIES_URL, e);
    	}

    	Resource[] resources = resourceList.toArray(new Resource[resourceList.size()]);
    	pspc.setLocations(resources);

    	return pspc;
    }

}
