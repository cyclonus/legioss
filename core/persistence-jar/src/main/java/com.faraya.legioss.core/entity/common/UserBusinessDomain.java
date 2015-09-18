package com.faraya.legioss.core.entity.common;


import com.faraya.legioss.core.IIdentifiable;
import com.faraya.legioss.core.entity.security.User;
import com.faraya.legioss.core.entity.security.UserDomain;
import javax.persistence.*;

/**
 *
 * Created by fabrizzio on 9/13/15.
 */
@Entity
@Table(name = "user_business",
        indexes =  {

        }
)
public class UserBusinessDomain extends UserDomain <Business> implements IIdentifiable <Long> {

        public UserBusinessDomain() {
        }

        public UserBusinessDomain(User user,Business domain) {
            super(user,domain);
        }

        @Override
        @JoinColumn(name = "business_id", nullable = false)
        public Business getDomain() {
                return super.getDomain();
        }
}
