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

import javax.persistence.Entity;

/**
 * Created by jgroth on 28/09/15.
 */
@Entity
public class Address extends GalacticCinemaEntity {

    private static final long serialVersionUID = 6716634666036105590L;

    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String line5;
    private String postCode;
    private String county;
    private String country;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getLine5() {
        return line5;
    }

    public void setLine5(String line5) {
        this.line5 = line5;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User == false) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Address address = (Address) obj;
        Boolean identityEquals = super.identityEquals(address);

        if (identityEquals == null) {
            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(line1, address.line1)
                    .append(line2, address.line2)
                    .append(line3, address.line3)
                    .append(line4, address.line4)
                    .append(line5, address.line5)
                    .append(postCode, address.postCode)
                    .append(county, address.county)
                    .append(country, address.country)
                    .isEquals();
        }
        return identityEquals;
    }

    @Override
    public int hashCode() {
        if (super.identityHashCode() != -1) {
            return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
        }

        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(line1)
                .append(line2)
                .append(line3)
                .append(line4)
                .append(line5)
                .append(postCode)
                .append(county)
                .append(country)
                .toHashCode();

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
