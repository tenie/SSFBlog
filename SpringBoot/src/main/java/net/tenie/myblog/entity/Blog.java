package net.tenie.myblog.entity;
 

import java.io.Serializable;
import java.util.Date;

 
public class Blog implements Serializable {
    private static final long serialVersionUID = -1840831686851699943L;
 
    private Long id;  
    private String postTitle;
    private String postSubtitle;
    private Date time;
    private String postContent;
    private Long textLength;  
    private Integer readQuantity;
    private Integer comment; 
    private Integer postLike;
    private Integer top;
    private Integer showContent;
    private Date createdAt;
    private Date updatedAt;
    private Integer recordVersion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostSubtitle() {
		return postSubtitle;
	}
	public void setPostSubtitle(String postSubtitle) {
		this.postSubtitle = postSubtitle;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Long getTextLength() {
		return textLength;
	}
	public void setTextLength(Long textLength) {
		this.textLength = textLength;
	}
	public Integer getReadQuantity() {
		return readQuantity;
	}
	public void setReadQuantity(Integer readQuantity) {
		this.readQuantity = readQuantity;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public Integer getPostLike() {
		return postLike;
	}
	public void setPostLike(Integer postLike) {
		this.postLike = postLike;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Integer getShowContent() {
		return showContent;
	}
	public void setShowContent(Integer showContent) {
		this.showContent = showContent;
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
	public Integer getRecordVersion() {
		return recordVersion;
	}
	public void setRecordVersion(Integer recordVersion) {
		this.recordVersion = recordVersion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", postTitle=" + postTitle + ", postSubtitle=" + postSubtitle + ", time=" + time
				+ ", postContent=" + postContent + ", textLength=" + textLength + ", readQuantity=" + readQuantity
				+ ", comment=" + comment + ", postLike=" + postLike + ", top=" + top + ", showContent=" + showContent
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", recordVersion=" + recordVersion + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((postContent == null) ? 0 : postContent.hashCode());
		result = prime * result + ((postLike == null) ? 0 : postLike.hashCode());
		result = prime * result + ((postSubtitle == null) ? 0 : postSubtitle.hashCode());
		result = prime * result + ((postTitle == null) ? 0 : postTitle.hashCode());
		result = prime * result + ((readQuantity == null) ? 0 : readQuantity.hashCode());
		result = prime * result + ((recordVersion == null) ? 0 : recordVersion.hashCode());
		result = prime * result + ((showContent == null) ? 0 : showContent.hashCode());
		result = prime * result + ((textLength == null) ? 0 : textLength.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((top == null) ? 0 : top.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		Blog other = (Blog) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (postContent == null) {
			if (other.postContent != null)
				return false;
		} else if (!postContent.equals(other.postContent))
			return false;
		if (postLike == null) {
			if (other.postLike != null)
				return false;
		} else if (!postLike.equals(other.postLike))
			return false;
		if (postSubtitle == null) {
			if (other.postSubtitle != null)
				return false;
		} else if (!postSubtitle.equals(other.postSubtitle))
			return false;
		if (postTitle == null) {
			if (other.postTitle != null)
				return false;
		} else if (!postTitle.equals(other.postTitle))
			return false;
		if (readQuantity == null) {
			if (other.readQuantity != null)
				return false;
		} else if (!readQuantity.equals(other.readQuantity))
			return false;
		if (recordVersion == null) {
			if (other.recordVersion != null)
				return false;
		} else if (!recordVersion.equals(other.recordVersion))
			return false;
		if (showContent == null) {
			if (other.showContent != null)
				return false;
		} else if (!showContent.equals(other.showContent))
			return false;
		if (textLength == null) {
			if (other.textLength != null)
				return false;
		} else if (!textLength.equals(other.textLength))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (top == null) {
			if (other.top != null)
				return false;
		} else if (!top.equals(other.top))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	} 
    
}
