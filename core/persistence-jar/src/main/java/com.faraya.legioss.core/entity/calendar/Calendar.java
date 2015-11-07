package com.faraya.legioss.core.entity.calendar;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Business;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */


@Entity
@Table(name = "calendar",
        indexes =  {
                @Index(name = "name-business", unique = true, columnList = "name,business_id")
        }
)
public class Calendar extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @JoinColumn(name = "business_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Business business;

    @Column(name = "business_id", nullable = true, insertable = false, updatable = false)
    private Long businessId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CalendarDate> calendarDates;

    public Calendar() {
    }

    public Calendar(String name, Business business) {
        this.name = name;
        this.business = business;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<CalendarDate> getCalendarDates() {
        if(calendarDates == null){
           calendarDates = new HashSet<>();
        }
        return calendarDates;
    }

    public void setCalendarDates(Set<CalendarDate> calendarDates) {
        this.calendarDates = calendarDates;
    }

    public void addCalendarDate(CalendarDate calendarDate){
        getCalendarDates().add(calendarDate);
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

}
