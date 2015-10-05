package com.faraya.legioss.core.entity.costing;

import com.faraya.legioss.core.entity.AbstractEntity;
import com.faraya.legioss.core.entity.common.Business;

import javax.persistence.*;
import javax.measure.quantity.Quantity;
import java.math.BigDecimal;

/**
 * Created by fabrizzio on 9/15/15.
 *
 * http://www.javaworld.com/article/2077770/core-java/introduction-to-jsr-275--measures-and-units.html
 */
public class PieceworkUnit extends AbstractEntity{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @JoinColumn(name = "business_id", nullable = false)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Business business;

    @Override
    public boolean isTransient() {
        return (id == null);
    }
}
