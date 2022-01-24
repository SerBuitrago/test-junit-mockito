package org.serbuitrago;

import java.util.Arrays;
import java.util.List;

import org.serbuitrago.model.Quiz;

public class Data {
	public static final List<Quiz> DATA_LIST_QUIZ = Arrays.asList(new Quiz(1L, "Matematica"), new Quiz(2L, "Religion"),
			new Quiz(3L, "Etica"), new Quiz(4L, "Social"), new Quiz(5L, "Ingles"), new Quiz(6L, "Informatica"),
			new Quiz(7L, "Estadistica"), new Quiz(8L, "Filosofia"), new Quiz(9L, "Tecnologia"),
			new Quiz(10L, "Lengua Castellena"), new Quiz(11L, "Frances"), new Quiz(12L, "Italiano"));

	public static final List<Quiz> DATA_LIST_QUIZ_ID_NULL = Arrays.asList(new Quiz(null, "Matematica"),
			new Quiz(null, "Religion"), new Quiz(null, "Etica"), new Quiz(null, "Social"), new Quiz(null, "Ingles"),
			new Quiz(null, "Informatica"), new Quiz(null, "Estadistica"), new Quiz(null, "Filosofia"),
			new Quiz(null, "Tecnologia"), new Quiz(null, "Lengua Castellena"), new Quiz(null, "Frances"),
			new Quiz(null, "Italiano"));

	public static final List<String> DATA_LIST_QUESTION = Arrays.asList("Suma", "Resta", "Derivadas", "Integrales",
			"Ecuaciones diferenciales");

	public static final Quiz DATA_QUIZ = new Quiz(null, "Calculo Vectorial", DATA_LIST_QUESTION);

}
