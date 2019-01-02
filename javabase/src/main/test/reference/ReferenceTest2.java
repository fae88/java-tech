package reference;

public class ReferenceTest2 {

    public static void main(String[] args) {


        User user = new ReferenceTest2.User();
        user.setName("yajun");
        changeUser(user);
        System.out.println(user.getName());



    }

    public static void changeUser(User user) {

        user.setName("fufan");
    }


    static class User {

        String name ;
        String gender;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
