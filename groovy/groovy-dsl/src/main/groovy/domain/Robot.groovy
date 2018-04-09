package domain

import groovy.transform.AutoClone
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@AutoClone
@Canonical
@EqualsAndHashCode
class Robot {
    int x, y

    void setLocation(int x, int y) {
        this.x = x
        this.y = y
    }
}
