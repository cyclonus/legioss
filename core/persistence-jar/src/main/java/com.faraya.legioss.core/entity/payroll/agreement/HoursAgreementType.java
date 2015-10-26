package com.faraya.legioss.core.entity.payroll.agreement;

/**
 *
 * Created by fabrizzio on 10/25/15.
 */

public enum HoursAgreementType {


    REGULAR("REGULAR"),OVERTIME("OVERTIME"),OVERNIGHT("OVERNIGHT"),WEEKEND("WEEKEND"),HOLIDAY("HOLIDAY");

    private final String type;

    HoursAgreementType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
