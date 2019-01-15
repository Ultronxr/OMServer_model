public class Test {

    public static void main(String[] args) {

        try {
            System.out.println(Class.class.getClass().getResource("/").getFile().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}