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

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.dbunit.Assertion;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.test.context.transaction.AfterTransaction;

import javax.sql.DataSource;
import java.lang.reflect.Method;

/**
 * Created by jgroth on 02/12/15.
 */
@TestExecutionListeners( DbUnitTestExecutionListener.class )
public class AbstractDbunitTransactionalJUnit4SpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    /** DBUnit tester */
    private IDatabaseTester databaseTester;

    /** expected dataset file name */
    private String afterDatasetFileName;

    /** method to execute after the test transaction is completed — verification */
    @AfterTransaction
    public void assertAfterTransaction() throws Exception {
        if (databaseTester == null || afterDatasetFileName == null) {
            return;
        }
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        IDataSet expectedDataSet = new XmlDataSet(ClassLoader.getSystemResourceAsStream(afterDatasetFileName));
        Assertion.assertEquals(expectedDataSet, databaseDataSet);
        databaseTester.onTearDown();
    }

    private static class DbunitTestExecutionListener extends AbstractTestExecutionListener {

        /** method to execute before the test method — initialisation */
        public void beforeTestMethod(TestContext testContext) throws Exception {
            AbstractDbunitTransactionalJUnit4SpringContextTests testInstance = (AbstractDbunitTransactionalJUnit4SpringContextTests) testContext.getTestInstance();
            Method method = testContext.getTestMethod();

            DbunitDataSets annotation = method.getAnnotation(DbunitDataSets.class);
            if (annotation == null) {
                return;
            }

            DataSource dataSource = testContext.getApplicationContext().getBean(DataSource.class);
            IDatabaseTester databaseTester = new DataSourceDatabaseTester(dataSource);
            databaseTester.setDataSet(new XmlDataSet(ClassLoader.getSystemResourceAsStream(annotation.before())));
            databaseTester.onSetup();
            testInstance.databaseTester = databaseTester;
            testInstance.afterDatasetFileName = annotation.after();
        }
    }
}
