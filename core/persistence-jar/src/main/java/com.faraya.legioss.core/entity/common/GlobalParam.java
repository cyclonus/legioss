package com.faraya.legioss.core.entity.common;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

@Entity
@Table(name = "global_param",
        indexes =  {
                @Index(name = "name", unique = true, columnList = "name,suite")
        }
)
public class GlobalParam extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true,length=50)
    private String name;

    @Column(name = "value", nullable = false, length=100)
    private String value;

    @Enumerated(EnumType.STRING)
    private ParamType type;

    @Column(name = "suite", nullable = false, length=50)
    private String suite;

    @JoinColumn(name = "business_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Business business;

    @Column(name = "business_id", nullable = true)
    private Long businessId;

    public GlobalParam() {
    }

    public GlobalParam(String name, String value, ParamType type, String suite, Business business) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.suite = suite;
        this.business = business;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ParamType getType() {
        return type;
    }

    public void setType(ParamType type) {
        this.type = type;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalParam that = (GlobalParam) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!value.equals(that.value)) return false;
        if (type != that.type) return false;
        if (!suite.equals(that.suite)) return false;
        return business.equals(that.business);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + suite.hashCode();
        result = 31 * result + business.hashCode();
        return result;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }
}
