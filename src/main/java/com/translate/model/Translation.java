package com.translate.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Translation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Source source;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "translator_id", referencedColumnName = "id")
    private UserEntity translator;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;
	
	private String text;
	
	public Long getId() { return id; }
	public Source getSource() { return source; }
	public UserEntity getTranslator() { return translator; }
	public Language getLanguage() { return language; }
	public String getText() { return text; }
	
	public void setId(Long id) { this.id = id; }
	public void setType(Source source) { this.source = source; }
	public void setAuthor(UserEntity translator) { this.translator = translator; }
	public void setLanguage(Language language) { this.language = language; }
	public void setText(String text) { this.text = text; }
}
