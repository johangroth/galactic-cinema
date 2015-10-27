package uk.org.linuxgrotto.signup;

import org.springframework.stereotype.Component;

/**
 * Created by jgroth on 27/10/15.
 */
@Component
public class Authentication {

    private String name;

    public String isUserAuthorised() {
        return name;
    }
}
