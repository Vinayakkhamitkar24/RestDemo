package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.*;
import java.util.*;

public class Utils {

	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}
	
	public static String getGlobalProperties(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Automation\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public static String getJsonPath(Response res , String key)
	{
		String response = res.asString();
		JsonPath js = new JsonPath(response);
		return js.get(key);
		
	}
}
