package bq.java8.annotation;

public class RepeatableAnnotation {

	public static void main(String[] args) {
		Filter[] annotations = BQFilter.class.getAnnotationsByType(Filter.class);
		for (int i = 0; i < annotations.length; i++) {
			System.out.println(annotations[i].value());
		}
	}
	
}

@Filter("b filter")
@Filter("q filter")
class BQFilter{
	
}
