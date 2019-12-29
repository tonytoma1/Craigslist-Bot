package com.web.scraper;

/*
 * The craigslist advertisement that describes the ads description, title and much more. 
 */
public class Advertisement {
	// The title of the advertisement.
	private String title;
	// The description of the advertisement.
	private String description;
	
	/*
	 * Sets the advertisment's title and description to the value of the argument.
	 * @param title - the title of the advertisement.
	 * @param description - the description of the advertisement.
	 */
	public Advertisement(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	/*
	 * Retrieve the advertisement title.
	 * @return String.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/*
	 * Initializes the title to the value of the argument.
	 * @param title - the title of the advertisement.
	 * @return void.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/*
	 * Retrieve the advertisement description.
	 * @return String.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/*
	 * Sets the advertisements description to the value of the argument.
	 * @param description - the description of the advertisement.
	 * @return void.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
