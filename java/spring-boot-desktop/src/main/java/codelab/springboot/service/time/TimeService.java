package codelab.springboot.service.time;

import java.util.Date;

/**
 * Supports date and time access operations.
 * The abstraction helps to decouple an implementation from system-specific calls.
 */
public interface TimeService {
    /**
     * Returns current time.
     */
    Date getTime();
}
