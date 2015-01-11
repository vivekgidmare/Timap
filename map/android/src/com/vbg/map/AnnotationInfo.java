package com.vbg.map;

public class AnnotationInfo {
	private String header;
	private String timing;
	private Boolean isFirstType;
	private String nameText;
	private String isSundayClosed;
	private int rating;
	private String ratigHeader;
	private String defaultImage;
	private String selectedImage;
	/**
	 * 
	 * @param header
	 * @param name
	 * @param timing
	 * @param isFirstType
	 * @param sundayClosed
	 * @param rating
	 */
	public AnnotationInfo(String header, String name, String timing,
			Boolean isFirstType, String sundayClosed, int rating,String ratingHeader,String defaultImage,String selectedImage) {
		super();
		this.header = header;
		this.timing = timing;
		this.isFirstType = isFirstType;
		this.nameText = name;
		this.isSundayClosed = sundayClosed;
		this.rating = rating;
		this.ratigHeader=ratingHeader;
		this.defaultImage=defaultImage;
		this.selectedImage=selectedImage;
	}

	public String getNameText() {
		return nameText;
	}

	public void setNameText(String nameText) {
		this.nameText = nameText;
	}

	public String getIsSundayClosed() {
		return isSundayClosed;
	}

	public void setIsSundayClosed(String isSundayClosed) {
		this.isSundayClosed = isSundayClosed;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Boolean getisFirstType() {
		return isFirstType;
	}

	public void setisFirstType(Boolean isFirstType) {
		this.isFirstType = isFirstType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getRatigHeader() {
		return ratigHeader;
	}

	public void setRatigHeader(String ratigHeader) {
		this.ratigHeader = ratigHeader;
	}

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public String getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(String selectedImage) {
		this.selectedImage = selectedImage;
	}
	
	
}
