package test;

import ejbeans.MyBean;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;

import static org.junit.Assert.assertEquals;

public class EjbTest {

    @Test
    public void testEjb() throws Exception {

        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MyBean instance = (MyBean) container.getContext().lookup("java:global/classes/MyBean");

        int result = instance.addNumbers(1, 2);
        container.close();

        System.out.println("MyBean.addNumbers ===> " + result);
        assertEquals(3, result);
    }
}
