package e.commerce.shopping.guide.common.tools;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializerUtils implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement arg0, Type arg1,
                            JsonDeserializationContext arg2) throws JsonParseException {

		// System.out.println("@@@" + arg0);

		if (arg0.getAsJsonPrimitive().toString().equals("\"\"")) {
			return null;
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = sdf.parse(arg0.toString().replace("\"", ""));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// new java.util.Date(arg0.getAsJsonPrimitive().getAsLong());

			return date;
		}
	}
}
