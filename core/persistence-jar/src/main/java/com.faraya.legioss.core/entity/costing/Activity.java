package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Business;

import javax.persistence.*;


/**
 *
 * Created by fabrizzio on 9/3/15.
 */

@Entity
@Table(name = "activity",
        indexes =  {

        }
)
public class Activity extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    private String i18eKey;

    private boolean active;

    @JoinColumn(name = "catalog_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private ActivityTree activityTree;

    @JoinColumn(name = "business_id", nullable = true,  insertable = false, updatable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Business business;

    public Activity() {
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

    public String getI18eKey() {
        return i18eKey;
    }

    public void setI18eKey(String i18eKey) {
        this.i18eKey = i18eKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ActivityTree getActivityTree() {
        return activityTree;
    }

    public void setActivityTree(ActivityTree activityTree) {
        this.activityTree = activityTree;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }


}
