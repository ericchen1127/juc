package reference;

/**
 * @author eric
 */
public class ReferenceModel {

    /**
     * override finalize is not recommended, here just for test
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.getClass().getName() + " is finalized");
    }
}
