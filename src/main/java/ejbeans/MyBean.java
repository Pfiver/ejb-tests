package ejbeans;

import javax.ejb.Stateless;

@Stateless
public class MyBean {
    public int addNumbers(int numberA, int numberB) {
        return numberA + numberB;
    }
}
