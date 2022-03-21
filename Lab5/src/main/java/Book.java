import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Book.class)

public class Book extends Item
{
	private String genre;

	Book(){}

	Book(String id, String title, String location, String genre)
	{
		super(id, title, location);
		this.genre = genre;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	@Override
	public String toString()
	{
		return "Book{" +
				"genre='" + genre + '\'' +
				", id='" + id + '\'' +
				", title='" + title + '\'' +
				", location='" + location + '\'' +
				'}';
	}
}
