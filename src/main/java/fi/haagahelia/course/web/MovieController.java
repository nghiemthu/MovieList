package fi.haagahelia.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.GenreRepository;
import fi.haagahelia.course.domain.Movie;
import fi.haagahelia.course.domain.MovieRepository;


@Controller
public class MovieController {
	
	@Autowired
	private MovieRepository repository; 

	@Autowired
	private GenreRepository crepository; 
	
	// Login Page
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	// Show all movies
    @RequestMapping(value="/movielist")
    public String movieList(Model model) {	
        model.addAttribute("movies", repository.findAll());
        return "movielist";
    }
    
    // RESTful service to get all movies
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> movieListRest() {	
        return (List<Movie>) repository.findAll();
    }    

	// RESTful service to get movie by id
    @RequestMapping(value="/movie/{id}", method = RequestMethod.GET)
    public @ResponseBody Movie findMovieRest(@PathVariable("id") Long movieId) {	
    	return repository.findOne(movieId);
    } 
     
    // Add new movie
    @RequestMapping(value = "/add")
    public String addMovie(Model model){
    	model.addAttribute("movie", new Movie());
    	model.addAttribute("genres", crepository.findAll());
        return "addmovie";
    }     
    
    // Save new movie
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Movie movie){
        repository.save(movie);
        return "redirect:movielist";
    }    

    // Delete movie
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") long id, Model model) {
    	repository.delete(id);
        return "redirect:../movielist";
    }     
}
