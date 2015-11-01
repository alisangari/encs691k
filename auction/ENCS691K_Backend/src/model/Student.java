package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Student {
	private int id;
	private String name;
	private List<String> courses = new ArrayList<>();
	private List<String> credits = new ArrayList<>();
	private Map<String, String> favorite = new HashMap<String, String>();
		
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Student(){
		
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	public List<String> getCredits() {
		return credits;
	}
	public void setCredits(List<String> credits) {
		this.credits = credits;
	}

	/**
	 *this method could add a new course to the list of course which are list of sting. 
	 * @param cours student course
	 */
	public void addCouse(String cours){
		this.courses.add(cours);
	}

	public Map<String, String> getFavorite() {
		return favorite;
	}

	public void setFavorite(Map<String, String> favorite) {
		this.favorite = favorite;
	}
	public void addFrutes(String priority, String name){
		this.favorite.put(priority, name);
	}
	

}
