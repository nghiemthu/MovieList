package fi.haagahelia.course.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
    private String title;
    private String img;
    private double imdb;
    private int year;
    private long length;
   
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "genreid")
    public Genre genre;

    public Movie() {}

	public Movie(String title, String img, double imdb, int year,long length, Genre genre) {
		super();
		this.title = title;
		this.img = img;
		this.imdb = imdb;
		this.year = year;
		this.length = length;
		this.genre = genre;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getImdb() {
		return imdb;
	}

	public void setImdb(double imdb) {
		this.imdb = imdb;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		if (this.genre != null)
			return "Book [title=" + title + ", imdb=" + imdb + ", year=" + year + " genre =" + this.getGenre() + "]";		
		else
			return "Book [title=" + title + ", imdb=" + imdb + ", year=" + year + "]";	
	}
}
