package ejbeans;

import javax.ejb.ApplicationException;

/**
 * An unchecked application Exception
 */
@ApplicationException
public class CustomApplicationRuntimeException extends RuntimeException {
}
