package org.serbuitrago.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Quiz {
	private Long id;
	private String name;
	private List<String> quiestions;

	public Quiz() {
		this(0L, null, new ArrayList<>());
	}

	public Quiz(Long id, String name) {
		this(id, name, new ArrayList<>());
	}
}
