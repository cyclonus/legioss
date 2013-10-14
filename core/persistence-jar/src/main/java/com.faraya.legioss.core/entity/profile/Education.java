package com.faraya.legioss.core.entity.profile;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 11:27 PM
 */
@Entity
@Table(name = "ph_education")
public class Education extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "education_id", nullable = false)
    @SequenceGenerator(name="education_gen", sequenceName="education_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="education_gen")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String major;
    private String degree;
    private Date startDate;
    private Date endDate;
    private String institution;
    private String desc;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }


}
