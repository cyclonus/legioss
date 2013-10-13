package net.faraya.legioss.core.entity.profile;

import net.faraya.legioss.core.entity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Professional profile
 * User: fabrizzio
 * Date: 10/10/13
 * Time: 11:28 PM
 */

@Entity
@Table(name = "ph_profile")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
public class Profile extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", fetch = FetchType.EAGER)
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", fetch = FetchType.EAGER)
    private Set<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", fetch = FetchType.EAGER)
    private Set<Experience> experiences;

    public Set<Address> getAddresses() {
        if(addresses == null){
           addresses = new HashSet<Address>();
        }
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Education> getEducations() {
        if(educations == null){
           educations = new HashSet<Education>();
        }
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Experience> getExperiences() {
        if(experiences == null){
           experiences = new HashSet<Experience>();
        }
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }
}
