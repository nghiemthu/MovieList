package fi.haagahelia.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.course.domain.Movie;
import fi.haagahelia.course.domain.MovieRepository;
import fi.haagahelia.course.domain.Genre;
import fi.haagahelia.course.domain.GenreRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@SpringBootApplication
public class MovieListApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieListApplication.class, args);
	}
		
	@Bean
	public CommandLineRunner MovieDemo(MovieRepository repository, GenreRepository grepository, UserRepository urepository) {
		return (args) -> {
			
			// Save some genres 
			grepository.save(new Genre("Action"));
			grepository.save(new Genre("Drama"));
			grepository.save(new Genre("Mystery"));
			
			// Save some movies
			repository.save(new Movie("Guardian Of The Galaxy Vol.2", 
					"https://img.gocdn.online/2017/04/28/poster/5a08e94ba02118f22dc30f298c603210-guardians-of-the-galaxy-vol-2.jpg", 
					8.3, 2017, 123, grepository.findByName("Action").get(0)));
			repository.save(new Movie("Before I fall", 
					"https://img.gocdn.online/2017/04/14/poster/5802ca094d8fa31175332f9bc02c6dca-before-i-fall.jpg", 
					6.5, 2017, 100, grepository.findByName("Drama").get(0)));
			repository.save(new Movie("Personal Shopper", 
					"https://img.gocdn.online/2017/04/14/poster/b0c21abe1026bc6f3b4a15d7c87ece8c-18-personal-shopper-2016.jpg", 
					6.8, 2017, 98, grepository.findByName("Mystery").get(0)));
			
			// Create users
			User user1 = new User("user", "123456", "user@gmail.com", "USER");
			User user2 = new User("admin", "1234567","user@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}
}
