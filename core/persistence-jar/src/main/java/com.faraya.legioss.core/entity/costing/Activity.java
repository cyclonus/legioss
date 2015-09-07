package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import javax.persistence.*;


/**
 * Created by fabrizzio on 9/3/15.
 */
public class Activity extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String i18eKey;

    private boolean active;

    @JoinColumn(name = "catalog_id", nullable = false)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private ActivityTree activityTree;

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
