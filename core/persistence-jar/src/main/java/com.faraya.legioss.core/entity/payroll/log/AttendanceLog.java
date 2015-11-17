package com.faraya.legioss.core.entity.payroll.log;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.payroll.Employee;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * Created by fabrizzio on 11/6/15.
 */

@Entity
@Table(name = "attendance_log")
public class AttendanceLog extends AbstractEntity implements IIdentifiable<Long> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    @Column(name = "check_in")
    private Date checkIn;

    @Temporal(TemporalType.TIME)
    @Column(name = "check_out")
    private Date checkOut;

    //absentDay, sickDay, ppt, holiday

    @JoinColumn(name = "employee_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    @Column(name = "employee_id", nullable = false, updatable = false, insertable = false)
    private Long employeeId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isTransient() {
        return false;
    }


}
