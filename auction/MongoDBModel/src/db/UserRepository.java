package db;

import model.User;

import org.bson.Document;

import utility.Strings;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import contract.Constants;
import contract.ReturnMessage;

public class UserRepository {
	private static MongoClient client = new MongoClient(Constants.HOST,
			Constants.PORT);
	private static MongoDatabase db = client.getDatabase(Constants.DB);
	private static MongoCollection<Document> coll = db
			.getCollection(Constants.COLLECTION);

	public static ReturnMessage Save(User user) {
		Document doc = new Document();
		ReturnMessage msg = new ReturnMessage();
		User existingUser = get(user.getUsername());
		if (!Strings.isNull(existingUser.getUsername())) {
			msg.duplicateExists();
			return msg;
		}
		doc.append("username", user.getUsername()).append("password",
				user.getPassword());
		coll.insertOne(doc);
		msg.ok();
		return msg;
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

	public static boolean delete(String username) {
		Document doc = new Document("username", username);
		DeleteResult res = coll.deleteOne(doc);
		return res.getDeletedCount() > 0;
	}

	public static boolean close(String username, String password) {
		User user = get(username);
		if (password.equalsIgnoreCase(user.getPassword())) {
			return delete(username);
		}
		return false;
	}

}
