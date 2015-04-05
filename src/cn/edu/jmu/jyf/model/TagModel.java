package cn.edu.jmu.jyf.model;

import cn.edu.jmu.jyf.bean.Tag;

public class TagModel {
	private Integer tagId;
	private String tagName;
	private String image;

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TagModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TagModel(Integer tagId, String tagName, String image) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.image = image;
	}

	public TagModel(Tag tag) {
		this.setTagId(tag.getTagId());
		this.setTagName(tag.getTagName());
		this.setImage(tag.getImage());
	}
}
