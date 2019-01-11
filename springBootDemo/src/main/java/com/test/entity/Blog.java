package com.test.entity;

import java.io.Serializable;


/**
 * 博客实体类
 * @author john
 *
 */
public class Blog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**唯一标识*/
	private String id;
	/**封面图片地址*/
	private String ImagePath;
	/**标题*/
	private String title;
	/**内容*/
	private String body;
	/**是否展示标识 0：展示 1：不展示*/
	private String isShow;
	/**创建日期*/
	private String createDate;
	/**所属人*/
	private String userId;
	/**点赞数*/
	private int fox;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getFox() {
		return fox;
	}
	public void setFox(int fox) {
		this.fox = fox;
	}
	public Blog() {
		super();
	}
	public Blog(String id, String imagePath, String title, String body, String isShow, String createDate, String userId,
			int fox) {
		super();
		this.id = id;
		ImagePath = imagePath;
		this.title = title;
		this.body = body;
		this.isShow = isShow;
		this.createDate = createDate;
		this.userId = userId;
		this.fox = fox;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", ImagePath=" + ImagePath + ", title=" + title + ", body=" + body + ", isShow="
				+ isShow + ", createDate=" + createDate + ", userId=" + userId + ", fox=" + fox + "]";
	}
	
	
	
}
