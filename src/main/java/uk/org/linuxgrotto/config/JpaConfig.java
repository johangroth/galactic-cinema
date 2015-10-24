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

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by jgroth on 08/10/15.
 */
@Configuration
@EnableJpaRepositories(basePackages={"uk.org.linuxgrotto"})
@EnableTransactionManagement
public class JpaConfig implements DisposableBean {

    private EmbeddedDatabase ed;

    @Bean(name="hsqlInMemory")
    public EmbeddedDatabase hsqlInMemory() {

        if ( this.ed == null ) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            this.ed = builder.setType(EmbeddedDatabaseType.HSQL).build();
        }

        return this.ed;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();

        lcemfb.setDataSource(this.hsqlInMemory());
        lcemfb.setPackagesToScan("uk.org.linuxgrotto");

        lcemfb.setPersistenceUnitName("MyPU");

        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        lcemfb.setJpaVendorAdapter(va);

        Properties ps = new Properties();
        ps.put("hibernate.dialect", "uk.org.linuxgrotto.model.GalacticMySQLDialect");
        ps.put("hibernate.hbm2ddl.auto", "update");
        ps.put("hibernate.ejb.naming_strategy", "uk.org.linuxgrotto.model.GalacticNamingStrategy");
        lcemfb.setJpaProperties(ps);

        lcemfb.afterPropertiesSet();

        return lcemfb;

    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager tm = new JpaTransactionManager();

        tm.setEntityManagerFactory(this.entityManagerFactory().getObject() );

        return tm;

    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Override
    public void destroy() {

        if ( this.ed != null ) {
            this.ed.shutdown();
        }

    }

}
