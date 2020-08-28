package util;

import javax.transaction.Status;
import java.util.stream.Stream;

public enum TransactionStatus {
    STATUS_ACTIVE(Status.STATUS_ACTIVE),
    STATUS_MARKED_ROLLBACK(Status.STATUS_MARKED_ROLLBACK),
    STATUS_PREPARED(Status.STATUS_PREPARED),
    STATUS_COMMITTED(Status.STATUS_COMMITTED),
    STATUS_ROLLEDBACK(Status.STATUS_ROLLEDBACK),
    STATUS_UNKNOWN(Status.STATUS_UNKNOWN),
    STATUS_NO_TRANSACTION(Status.STATUS_NO_TRANSACTION),
    STATUS_PREPARING(Status.STATUS_PREPARING),
    STATUS_COMMITTING(Status.STATUS_COMMITTING),
    STATUS_ROLLING_BACK(Status.STATUS_ROLLING_BACK),
    ;

    final int code;
    TransactionStatus(int code) { this.code = code; }
    public static TransactionStatus valueOf(int statusCode) {
        return Stream.of(values()).filter(ts -> ts.code == statusCode).findAny().get();
    }
}
