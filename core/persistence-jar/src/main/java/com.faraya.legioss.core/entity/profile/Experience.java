package com.faraya.legioss.core.entity.profile;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 11:38 PM
 */
@Entity
@Table(name = "ph_experience")
public class Experience extends AbstractEntity implements IIdentifiable <Long>  {

    @Id
    @Column(name = "experience_id", nullable = false)
    @SequenceGenerator(name="experience_gen", sequenceName="experience_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="experience_gen")
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date endDate;
    private String institution;
    private String jobDesc;
    private String accomplishments;

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

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getAccomplishments() {
        return accomplishments;
    }

    public void setAccomplishments(String accomplishments) {
        this.accomplishments = accomplishments;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }
}
