package ejbeans;

import util.TransactionStatus;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Integer.toHexString;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class MyBean {

    public static final Map<String, Boolean> rollbackStates = new LinkedHashMap<>();

    @Resource
    private EJBContext ejbContext;

    @Resource
    private TransactionSynchronizationRegistry tsr;

    @EJB
    private MyOtherBean myOtherBean;

    public void method1() {

        tsr.registerInterposedSynchronization(new Synchronization() {
            public void beforeCompletion() {
                System.out.println("1 before completion");
            }
            public void afterCompletion(int status) {
                System.out.println("1 after completion: " + TransactionStatus.valueOf(status));
            }
        });

        System.out.println("TX: " + toHexString(tsr.getTransactionKey().hashCode()));
        System.out.println("TX state: " + TransactionStatus.valueOf(tsr.getTransactionStatus()));

        try {
            myOtherBean.fail();
        }
        catch (Exception e) {
            System.out.println("exception: " + e.getClass());
        }

        System.out.println("TX: " + toHexString(tsr.getTransactionKey().hashCode()));
        System.out.println("TX state: " + TransactionStatus.valueOf(tsr.getTransactionStatus()));

        rollbackStates.put("MyBean#method1", ejbContext.getRollbackOnly());
    }
}
