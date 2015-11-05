package uk.org.linuxgrotto.model.base;
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

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Created by jgroth on 28/09/15.
 */
@MappedSuperclass
public abstract class GalacticCinemaEntity extends GalacticAbstractPersistable<Long> {

    private static final long serialVersionUID = 2468429844954725039L;

    @Version
    @Column(columnDefinition = GalacticMySQLDialect.INT_DEFAULT_ZERO, nullable = false)
    protected int optLock = 0;

    /**
     * Returns whether a persistent entity has the same identity as another based on the ID field.
     * <p/>
     * Subclasses may check this return value before doing value based equals checks because the ID can be considered authoritative at establishing identity if
     * set.
     *
     * @return <code>true</code> if they both have an ID set and they the IDs are equal, <code>false</code> if both set and different otherwise null
     */
    protected Boolean identityEquals(GalacticCinemaEntity entity) {
        if (isNew() || entity.isNew()) {
            return null;
        }

        if (!getId().equals(entity.getId())) {
            return false;
        }

        if (!(getVersion() == entity.getVersion())) {
            return false;
        }

        return true;
    }

    public int getVersion() {
        return optLock;
    }

    /**
     * Return the hash code based on the identity of this entity.
     * <p/>
     * Subclasses must check this return value before doing value based hash code generation because the ID can be considered authoritative at establishing
     * identity if set.
     *
     * @return the hash code based on the identity of the entity, or <code>-1</code> if it is not set.
     */
    public int identityHashCode() {
        Long id = getId();
        if (id == null) {
            return -1;
        } else {
            return new HashCodeBuilder(17, 37).append(id).append(optLock).toHashCode();
        }
    }

    public int identityCompareToCode(GalacticCinemaEntity otherComparisonObject) {
        Long id = getId();
        if (id == null) {
            return -1;
        } else {
            return new CompareToBuilder().append(id, otherComparisonObject.getId()).append(optLock, otherComparisonObject.getVersion()).toComparison();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
