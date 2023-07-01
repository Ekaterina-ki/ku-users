import java.time.LocalDateTime;
import java.util.List;

public class Main {

    /*public static void main(String[] args) throws Exception {
        UserRespository userRespository = new UserRespository();
        User user = new User();
        user.setName("Java 1");
        user.setUserName("Usernane 1");
        user.setSurname("Surname 1");
        user.setAge(18);
        user.setPassword("dad");
        user.setInsertedAtUtc(LocalDateTime.now());
        userRespository.save(user);
    }*/


    public static void main(String[] args) throws Exception {
        //UserRespository userRespository = new UserRespository();
        //userRespository.DeleteById(7L);


        //UserRespository userRespository = new UserRespository();
        //userRespository.UpdateById(8L,"Kotya");

//        UserRespository userRespository = new UserRespository();
//        List<User> users = userRespository.findByAll();
//        for (User user : users) {
//            System.out.println(userRespository.findByAll());
//        }
//    }
//}
      UserRespository userRespository = new UserRespository();
      User user = userRespository.findById(1L);
      System.out.println(user);
}
}
