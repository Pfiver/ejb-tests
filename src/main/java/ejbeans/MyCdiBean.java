package ejbeans;

public class MyCdiBean {

    public void fail() {
        throw new CustomSystemException();
    }
}
