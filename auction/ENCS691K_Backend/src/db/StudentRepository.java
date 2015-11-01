package db;

import java.util.List;
import java.util.Map;

import model.Student;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class StudentRepository {
	private MongoClient client = new MongoClient("localhost", 27017);
	private MongoDatabase db = client.getDatabase("mongo-sample2");
	private MongoCollection<Document> coll = db.getCollection("students");

	public void Save(Student student) {
		Document doc = new Document();

		doc.append("_id", student.getId()).append("Name", student.getName())
				.append("courses", student.getCourses())
				.append("frutes", student.getFavorite());

		this.coll.insertOne(doc);

	}

	public Student get(int id) {
		Student student = new Student();
		Document doc = new Document("_id", id);

		FindIterable<Document> students = coll.find(doc);
		for (Document document : students) {
			int docId = Integer.parseInt(document.get("_id").toString());
			if (docId == id) {
				student.setId(docId);
				student.setName(document.get("Name").toString());
				List<String> courses = (List<String>) document.get("courses");
				student.setCourses(courses);
				Map<String, String> fruites = (Map<String, String>) document
						.get("frutes");
				student.setFavorite(fruites);
			}
		}
		return student;
	}
	public void delet(Student student)
	{
		Document doc = new Document("_id", student.getId());
		coll.deleteMany(doc);
		
	
	}

}
