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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.RecursiveToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //see http://stackoverflow.com/questions/24994440/no-serializer-found-for-class-org-hibernate-proxy-pojo-javassist-javassist
public abstract class BaseEntityUUID implements Serializable
{
    /**
	 *
	 */
	private static final long serialVersionUID = 8608953068007538072L;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",
	  strategy = "uuid")
    @Column(name = "id", updatable = false, nullable = false)
    private String id = null;

    @Version
    @Column(name = "version")
    private int version = 0;



    @Transient
    private String validationId;

    protected void copy(final BaseEntityUUID source)
    {
        this.id = source.id;
        this.version = source.version;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof BaseEntityUUID))
        {
            return false;
        }
        final BaseEntityUUID other = (BaseEntityUUID) obj;
        if (this.id != null && other.id != null)
        {
            if (this.getClass().equals(other.getClass()) && this.id == other.id)
            {
                return true;
            }
        }
        return false;
    }

    public String getId()
    {
        return this.id;
    }

    @Deprecated
    public void setId(final String id)
    {
        this.id = id;
    }

    public int getVersion()
    {
        return this.version;
    }

    @SuppressWarnings("unused")
    private void setVersion(final int version)
    {
        this.version = version;
    }

	@Override
	public String toString() {
        return ToStringBuilder.reflectionToString(this, RecursiveToStringStyle.JSON_STYLE);
    }

    public String getValidationId() {
        return validationId;
    }

    public void setValidationId(String validationId) {
        this.validationId = validationId;
    }

    @JsonIgnoreProperties
    private LocalDateTime changeTime = null;

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }


}
