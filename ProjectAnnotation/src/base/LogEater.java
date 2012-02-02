package base;

import java.lang.reflect.Method;
import java.text.Annotation;

public class LogEater {

	@LogMe(spicy="chilly")
	public void eatMe() {
		System.out.println("ahh i am  being chewed upon");
	}

	public static void main(String[] args) {
		LogEater le = new LogEater();

		le.eatMe();

		//System.out.println(an.toString());
		try {
			Method method = le.getClass().getMethod("eatMe", null);
			LogMe ann = method.getAnnotation(LogMe.class);

			System.out.println(ann.spicy());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
