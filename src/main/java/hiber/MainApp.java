package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Cache;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("toyota", 325));
      userService.add(user1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("nissan", 455));
      userService.add(user2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+ user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }

      User userFromCar = userService.getUserByCar("nissan", 455);
      System.out.println();
      System.out.println("User with car model "+userFromCar.getCar().getModel()+" and series "+userFromCar.getCar().getSeries());
      System.out.println();
      System.out.println("Id = "+userFromCar.getId());
      System.out.println("First Name = "+userFromCar.getFirstName());
      System.out.println("Last Name = "+userFromCar.getLastName());
      System.out.println("Email = "+userFromCar.getEmail());
      System.out.println("Car model = "+ userFromCar.getCar().getModel());
      System.out.println("Car series = "+userFromCar.getCar().getSeries());
      System.out.println();

      context.close();
   }
}
