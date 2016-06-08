/*******************************************************************************
 * Copyright (C) 2012  Oregami.org, Germany http://www.oregami.org
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of version 3 or any later version of the
 * 	GNU Affero General Public License as published by the Free Software
 * 	Foundation.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.
 *
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.oregami.entities;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Audited
@Table(name = "GameRelease") //because "Release" is a reserved word in MySQL)
public class Release extends BaseEntityUUID {

    private String description;

    public Release() {}

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn
    private Set<ReleaseRegion> releaseRegionList = new HashSet<>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ReleaseRegion> getReleaseRegionList() {
        return releaseRegionList;
    }

    public void setReleaseRegionList(Set<ReleaseRegion> releaseRegionList) {
        this.releaseRegionList = releaseRegionList;
    }
}
