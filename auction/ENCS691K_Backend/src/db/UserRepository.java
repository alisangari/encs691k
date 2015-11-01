package db;

import model.User;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import contract.Constants;

public class UserRepository {
	private static MongoClient client = new MongoClient(Constants.HOST, Constants.PORT);
	private static MongoDatabase db = client.getDatabase(Constants.DB);
	private static MongoCollection<Document> coll = db
			.getCollection(Constants.COLLECTION);

	public static void Save(User user) {
		Document doc = new Document();

		doc.append("username", user.getUsername()).append("password",
				user.getPassword());

		coll.insertOne(doc);

	}

	public static User get(String username) {
		User user = new User();
		Document doc = new Document("username", username);

		FindIterable<Document> users = coll.find(doc);
		for (Document document : users) {
			String docId = (String) document.get("username");
			if (docId.equalsIgnoreCase(username)) {
				user.setUsername(docId);
				user.setPassword((String) document.get("password"));
			}
		}
		return user;
	}

	public static void delete(String username) {
		Document doc = new Document("username", username);
		coll.deleteMany(doc);
	}

}
