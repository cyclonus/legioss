package com.faraya.legioss.core.entity.costing;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by fabrizzio on 9/15/15.
 *
 * http://www.javaworld.com/article/2077770/core-java/introduction-to-jsr-275--measures-and-units.html
 */
public class Unit {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
