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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import uk.org.linuxgrotto.model.base.GalacticCinemaEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * User entity
 * Created by jgroth on 28/09/15.
 */
@Entity(name="userz")
public class Person extends GalacticCinemaEntity {

    private static final long serialVersionUID = 3016962615640549425L;

    @Column(unique = true)
    private String userName;

    @Min(6)
    private String password;

    private String name;

    @Email
    private String email;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private Address address;

    @Temporal(TemporalType.DATE)
    private Date signupDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Person person = (Person) obj;
        Boolean identityEquals = super.identityEquals(person);

        if (identityEquals == null) {
            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(userName, person.userName)
                    .append(name, person.userName)
                    .append(email, person.email)
                    .append(address, person.address)
                    .append(signupDate, person.signupDate)
                    .isEquals();
        }
        return identityEquals;
    }

    @Override
    public int hashCode() {
        if (super.identityHashCode() != -1) {
            return new HashCodeBuilder().append(super.identityHashCode()).toHashCode();
        }
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(userName)
                .append(name)
                .append(email)
                .append(address)
                .append(signupDate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
