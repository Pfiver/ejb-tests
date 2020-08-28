package ejbeans;

import util.TransactionStatus;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;

import static java.lang.Integer.toHexString;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class MyOtherBean {

    @Resource
    private EJBContext ejbContext;

    @Resource
    private TransactionSynchronizationRegistry tsr;

    @Inject
    private MyCdiBean myCdiBean;

    @EJB
    private MyThirdBean myThirdBean;

    public void fail() {

        tsr.registerInterposedSynchronization(new Synchronization() {
            public void beforeCompletion() {
                System.out.println("2 before completion");
            }
            public void afterCompletion(int status) {
                System.out.println("2 after completion: " + TransactionStatus.valueOf(status));
            }
        });

        System.out.println("TX: " + toHexString(tsr.getTransactionKey().hashCode()));
        System.out.println("TX state: " + TransactionStatus.valueOf(tsr.getTransactionStatus()));

        try {
            myCdiBean.fail();
        }
        catch (Exception e) {
            System.out.println("exception: " + e.getClass());
        }

        System.out.println("TX: " + toHexString(tsr.getTransactionKey().hashCode()));
        System.out.println("TX state: " + TransactionStatus.valueOf(tsr.getTransactionStatus()));

        try {
            myThirdBean.fail();
        }
        catch (Exception e) {
            System.out.println("exception: " + e.getClass());
        }

        System.out.println("TX: " + toHexString(tsr.getTransactionKey().hashCode()));
        System.out.println("TX state: " + TransactionStatus.valueOf(tsr.getTransactionStatus()));

        MyBean.rollbackStates.put("MyOtherBean#method1", ejbContext.getRollbackOnly());

        throw new CustomSystemException();
    }
}
