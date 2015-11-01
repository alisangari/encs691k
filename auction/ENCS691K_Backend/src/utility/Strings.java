package utility;

public class Strings {

	public static boolean isNull(String str) {
		if(str == null)
			return true;
		if(str.trim()=="")
			return true;
		return false;
	}
}
