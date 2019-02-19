package fr.afpa.formation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "commentary")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	@Column(name = "text", nullable = false, length = 255)
	private String text;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_sujet_content")
//	private SujetContent sujetContent;

	public Commentaire(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public Commentaire() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

//	public SujetContent getSujetContent() {
//		return sujetContent;
//	}
//
//	public void setSujetContent(SujetContent sujetContent) {
//		this.sujetContent = sujetContent;
//	}

}
