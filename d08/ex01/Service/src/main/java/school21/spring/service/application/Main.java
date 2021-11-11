package school21.spring.service.application;
import school21.spring.service.models.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        //UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
		UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
		usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
		System.out.println(usersRepository.findAll());
		
		usersRepository.save(new User(6L, "abc"));
		System.out.println(usersRepository.findAll());
		usersRepository.update(new User(1L, "gg"));
		System.out.println(usersRepository.findAll());
		usersRepository.delete(4L);
		System.out.println(usersRepository.findAll());
		System.out.println(usersRepository.findById(1L));
		System.out.println(usersRepository.findByEmail("user2").get());
		
		
    }
}