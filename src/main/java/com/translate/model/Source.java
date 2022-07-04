package com.translate.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Source {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;
	
	private String text;

	public Long getId() { return id; }
	public Type getType() { return type; }
	public Author getAuthor() { return author; }
	public Language getLanguage() { return language; }
	public String getText() { return text; }
	
	public void setId(Long id) { this.id = id; }
	public void setType(Type type) { this.type = type; }
	public void setAuthor(Author author) { this.author = author; }
	public void setLanguage(Language language) { this.language = language; }
	public void setText(String text) { this.text = text; }
}
