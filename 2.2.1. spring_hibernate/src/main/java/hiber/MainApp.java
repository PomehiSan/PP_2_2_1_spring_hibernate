package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User(new Car("Nissan", 2), "User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(new Car("Jeep", 33), "User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(new Car("Lada", 666), "User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User(new Car("Jaguar", 7), "User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());;
      }

      User myUser = userService.getUserByCar("Nissan", 2);
      System.out.println(myUser);

      context.close();
   }
}
