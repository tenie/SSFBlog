package net.tenie.myblog.entity;
 
import java.io.Serializable;
import java.util.Date;

 
public class BlogComment implements Serializable {
    private static final long serialVersionUID = -1840831686851699943L;


    
    private Long id;  
    private Long postId;
    private Long parentId;
    private Integer commentLike;
    private Integer myselft;
    private Integer recordVersion;  
    private String name;
    private String comment; 
    private String url;
    private String email; 
    private Date createdAt;
    private Date updatedAt;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(Integer commentLike) {
		this.commentLike = commentLike;
	}
	public Integer getMyselft() {
		return myselft;
	}
	public void setMyselft(Integer myselft) {
		this.myselft = myselft;
	}
	public Integer getRecordVersion() {
		return recordVersion;
	}
	public void setRecordVersion(Integer recordVersion) {
		this.recordVersion = recordVersion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BlogComment [id=" + id + ", postId=" + postId + ", parentId=" + parentId + ", commentLike="
				+ commentLike + ", myselft=" + myselft + ", recordVersion=" + recordVersion + ", name=" + name
				+ ", comment=" + comment + ", url=" + url + ", email=" + email + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((commentLike == null) ? 0 : commentLike.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((myselft == null) ? 0 : myselft.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((postId == null) ? 0 : postId.hashCode());
		result = prime * result + ((recordVersion == null) ? 0 : recordVersion.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogComment other = (BlogComment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commentLike == null) {
			if (other.commentLike != null)
				return false;
		} else if (!commentLike.equals(other.commentLike))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (myselft == null) {
			if (other.myselft != null)
				return false;
		} else if (!myselft.equals(other.myselft))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (postId == null) {
			if (other.postId != null)
				return false;
		} else if (!postId.equals(other.postId))
			return false;
		if (recordVersion == null) {
			if (other.recordVersion != null)
				return false;
		} else if (!recordVersion.equals(other.recordVersion))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	} 
    
}
