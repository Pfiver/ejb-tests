package ejbeans;

import util.TransactionStatus;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class MyThirdBean {

    @Resource
    private TransactionSynchronizationRegistry tsr;

    public void fail() {
        tsr.registerInterposedSynchronization(new Synchronization() {
            public void beforeCompletion() {
                System.out.println("3 before completion");
            }
            public void afterCompletion(int status) {
                System.out.println("3 after completion: " + TransactionStatus.valueOf(status));
            }
        });
        throw new CustomSystemException();
    }
}
