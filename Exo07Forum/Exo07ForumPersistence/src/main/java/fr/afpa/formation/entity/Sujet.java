package fr.afpa.formation.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sujet")
public class Sujet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, 
			targetEntity = fr.afpa.formation.entity.SujetContent.class, 
			cascade = {CascadeType.REFRESH, CascadeType.REMOVE }, 
			mappedBy = "sujet")
	private List<SujetContent> sujetContents;

	public Sujet(Long id, String name, List<SujetContent> sujetContents) {
		super();
		this.id = id;
		this.name = name;
		this.sujetContents = sujetContents;
	}

	
	public Sujet() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SujetContent> getSujetContents() {
		return sujetContents;
	}

	public void setSujetContents(List<SujetContent> sujetContents) {
		this.sujetContents = sujetContents;
	}
	
	public void add(SujetContent content) {
		sujetContents.add(content);
	}

	public void add(Commentaire commentaire) {
		SujetContent content = new SujetContent(commentaire.getId(), commentaire);
		sujetContents.add(content);
	}

	public void remove(SujetContent content) {
		for (SujetContent sujetContent : sujetContents) {

			if (content.getId().equals(sujetContent.getId())) {
				sujetContents.remove(content);
				return;
			}
		}

	}

	public void remove(Commentaire commentaire) {
		for (SujetContent sujetContent : sujetContents) {

			Commentaire com = sujetContent.getCommentaire();
			if (commentaire.getId().equals(com.getId())) {
				sujetContents.remove(sujetContent);
				return;
			}

		}

	}

}
