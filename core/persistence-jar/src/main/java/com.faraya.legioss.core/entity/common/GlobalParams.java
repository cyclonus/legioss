package com.faraya.legioss.core.entity.common;

/**
 *
 * Created by fabrizzio on 12/27/15.
 */
public enum GlobalParams {


    HOURS_STANDARD_SHIFT("PAYROLL");

    private String group;

    GlobalParams(String group) {
        this.group = group;
    }

    public String group() {
        return this.group;
    }
}
