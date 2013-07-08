package net.faraya.legioss.core.entity;


import net.faraya.legioss.core.IIdentifiable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author faraya
 */

@MappedSuperclass
public abstract class AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    protected Date modifiedDate;

    @Version
    protected Integer version;

    @Column(name = "created_by")
    protected Long createdBy;

    @Column(name = "modified_by")
    protected Long modifiedBy;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * JPA Version fields used on concurrency
     * This should be incremented automatically by the framework
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}