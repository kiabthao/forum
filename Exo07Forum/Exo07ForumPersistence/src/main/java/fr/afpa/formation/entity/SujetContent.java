package fr.afpa.formation.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sujet_content")
public class SujetContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id", nullable = false, length = 25)
	private Long id;
	
	
    @OneToOne(fetch = FetchType.EAGER,cascade = { CascadeType.REFRESH,CascadeType.REMOVE })
    @JoinColumn(name = "id_commentaire")
	private Commentaire commentaire;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "id_sujet")
    private Sujet sujet;
	
	public SujetContent(Long id, Commentaire commentaire) {
		super();
		this.id = id;
		this.commentaire = commentaire;
	}
	
	public SujetContent() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Commentaire getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(Commentaire commentaire) {
		this.commentaire = commentaire;
	}
	public Sujet getSujet() {
		return sujet;
	}
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	
}
