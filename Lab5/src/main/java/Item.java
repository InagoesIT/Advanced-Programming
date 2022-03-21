import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property="type")
@JsonSubTypes(
		{
				@JsonSubTypes.Type(value = Book.class, name = "book"),
				@JsonSubTypes.Type(value = Article.class, name = "article")
		})

public abstract class Item implements Serializable
{
	protected String id;
	protected String title;
	protected String location;
	protected Map<String, Object> tags;

	public Item(){}

	protected Item(String id, String title, String location)
	{
		this.id = id;
		this.title = title;
		this.location = location;
		tags = new HashMap<>();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		if (id == null || id.trim().equals(""))
			throw new IllegalArgumentException(
					"Id should not be empty.");

		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		if (title == null || title.trim().equals("")) {
			throw new IllegalArgumentException(
					"Title should not be empty.");
		}
		if (!title.matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException(
					"Title should only contain characters or spaces: " + title);
		}
		this.title = title;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		if (location == null || location.trim().equals("")) {
			throw new IllegalArgumentException(
					"Location should not be empty.");
		}

		this.location = location;
	}

	public Object getTag(String key, Object obj)
	{
		return tags.get(key);
	}

	public void addTag(String key, Object obj)
	{
		tags.put(key, obj);
	}

	@Override
	public boolean equals(Object object)
	{
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;
		if (object == this)
			return true;
		return Objects.equals(((Book) object).getId(), id);
	}

	public Map<String, Object> getTags()
	{
		return tags;
	}
}
