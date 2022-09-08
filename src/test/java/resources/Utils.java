package resources;

import java.awt.print.Printable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils
{

	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		if (req==null) 
		{
			//create time stamp
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
			String formatDateTime = LocalDateTime.now().format(formatter);
			String logFileName = "log_" + formatDateTime;
			//Create File stream
			PrintStream logStream = new PrintStream(new FileOutputStream(logFileName));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(logStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(logStream)).setContentType(ContentType.JSON).build();
			//return req;
		}
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		System.out.println(System.getProperty("user.dir"));
		Properties prop =new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	
	}
	
	public String getResponseKeyValue(Response response, String key)
	{
		String resp=response.asString();
		JsonPath jp=new JsonPath(resp);
		return jp.getString(key);
	}
}
