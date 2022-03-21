import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Article.class)

public class Article extends Item
{
	private int likesNr;

	Article(){}

	Article(String id, String title, String location, int likesNr)
	{
		super(id, title, location);
		this.likesNr = likesNr;
	}

	public int getLikesNr()
	{
		return likesNr;
	}

	public void setLikesNr(int likesNr)
	{
		this.likesNr = likesNr;
	}

	@Override
	public String toString()
	{
		return "Article{" +
				"likesNr=" + likesNr +
				", id='" + id + '\'' +
				", title='" + title + '\'' +
				", location='" + location + '\'' +
				'}';
	}
}
