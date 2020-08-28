package ejbeans;

import javax.ejb.ApplicationException;

/**
 * An unchecked exception, which is
 * annotated to be a rollbacking application exception
 * and thus exhibits the same semantics as a system exception
 */
@ApplicationException(rollback = true)
public class CustomRollbackingApplicationRuntimeException extends RuntimeException {
}
