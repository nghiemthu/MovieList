package fi.haagahelia.course.web;

import java.util.ArrayList;
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
	
	List<Movie> result = new ArrayList<Movie>();
	
	// Login Page
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
    
    // Show all movies
    @RequestMapping(value="/popular")
    public String popularMovie(Model model) {
    	List<Movie> newList = new ArrayList<Movie>();
    	List<Movie> allMovie = (List<Movie>) repository.findAll();
    
    	for(int i=0; i<allMovie.size(); i++){
    		if (allMovie.get(i).getImdb() > 8.0)
    			newList.add(allMovie.get(i));
    	}
    		
        model.addAttribute("movies", newList);
        return "movielist";
    }
	
	// Show all movies
    @RequestMapping(value="/movielist")
    public String movieList(Model model) {
    	result = (List<Movie>) repository.findAll();
        model.addAttribute("movies", result);
        return "search";
    }
    
 // Show all movies
    @RequestMapping(value="/search")
    public String searchMovie(Model model) {
    	model.addAttribute("movie", new Movie());
    	model.addAttribute("genres", crepository.findAll());
        return "searchMovie";
    }
    
   // Show all movies
    @RequestMapping(value="/hello")
    public String hello(Model model) {	
        model.addAttribute("movies", repository.findAll());
        return "movielist";
    }
    
  // Find by Imdb
    @RequestMapping(value = "/find/{imdb}", method = RequestMethod.GET)
    public String findIMDB(@PathVariable("imdb") double imdb, Model model) {
    	List<Movie> newList = new ArrayList<Movie>();
    	List<Movie> allMovie = (List<Movie>) repository.findAll();
    
    	for(int i=0; i<allMovie.size(); i++){
    		if (allMovie.get(i).getImdb() > 8.0)
    			newList.add(allMovie.get(i));
    	}
    		
        model.addAttribute("movies", newList);
        return "redirect:../movielist";
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