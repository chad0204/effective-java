import org.junit.Test;

public class AunitExample1 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    public void methodOneTest() {
        System.out.println("1111");
        methodOne().equals("This is methodOne");
    }

}
