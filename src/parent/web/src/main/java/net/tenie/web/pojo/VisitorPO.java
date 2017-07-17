package net.tenie.web.pojo;

import org.hibernate.validator.constraints.Length;

import net.tenie.web.validate.Email;
import net.tenie.web.validate.Phone;
import net.tenie.web.validate.Required;

public class VisitorPO {
	
	private Integer id;
	@Required
	@Length(max=30)
	private String name;
	@Required 
	@Email
	private String email; 
	
	private String url;
	
	@Length(max=300)
	private String comment;
	
	private Integer parentId;
	
	private Integer postId;
	
	@Length(max=300)
	private String message;
	@Phone
	private String phone;
	
	
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
