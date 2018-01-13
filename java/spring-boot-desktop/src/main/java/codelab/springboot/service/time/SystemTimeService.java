package codelab.springboot.service.time;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * An implementation of {@link TimeService} that makes use of system specific {@link Date} class.
 */
@Component
public class SystemTimeService implements TimeService {
    @Override
    public Date getTime() {
        return new Date();
    }
}
