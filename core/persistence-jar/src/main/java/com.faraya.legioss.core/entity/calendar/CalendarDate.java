package com.faraya.legioss.core.entity.calendar;

import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * Created by fabrizzio on 10/31/15.
 */

@Entity
@Table(name = "calendar_date",
        indexes =  {
                @Index(name = "name-idx", unique = true, columnList = "name"),
                @Index(name = "name-and-date-idx-", unique = true, columnList = "name,date")
        }
)
public class CalendarDate extends AbstractEntity implements IIdentifiable<Long>{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "date", nullable = false, unique = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    private Type type;

    public CalendarDate() {
    }

    public CalendarDate(String name, Date date, Type type) {
        this.name = name;
        this.date = date;
        this.type = type;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean isTransient() {
        return (id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarDate that = (CalendarDate) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        if (!date.equals(that.date)) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CalendarDate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", type=" + type +
                '}';
    }
}
