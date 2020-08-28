package test;

import ejbeans.MyBean;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EjbTest {
    
    @EJB(name = "MyBean")
    private MyBean myBean;

    @Test
    public void testEjb() throws Exception {

        // turn off openejb log output
        Class.forName(LogManager.class.getName());
        System.setProperty("java.util.logging.config.file", "");

        // start an embedded container and call the test entry point
        try (EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer()) {
            container.getContext().bind("inject", this);
            myBean.method1();
        }

        // print results
        new LinkedList<>(MyBean.rollbackStates.entrySet()).descendingIterator().forEachRemaining(entry -> {
            String key = entry.getKey(), value = entry.getValue().toString();
            System.out.println(key + " -> " + value);
        });
    }
}


// auch cool:
//
//      <groupId>org.apache.tomee</groupId>
//      <artifactId>openejb-core</artifactId>
//
// @RunWith(org.apache.openejb.junit.ApplicationComposer.class)
// @org.apache.openejb.testing.Configuration
// @org.apache.openejb.testing.Module
// @org.apache.openejb.testing.Jars
// etc.
