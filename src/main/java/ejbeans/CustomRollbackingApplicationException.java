package ejbeans;

import javax.ejb.ApplicationException;

/**
 * A checked exception with custom, configured to exhibit
 * "rollbacking" semantic by annotating it with the (optional)
 * {@code @ApplicationException} interface and
 * overriding the default "false" value of the "rollback" argument
 */
@ApplicationException(rollback = true)
public class CustomRollbackingApplicationException extends Exception {}
