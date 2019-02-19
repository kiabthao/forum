package fr.afpa.formation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.afpa.formation.entity.Commentaire;
import fr.afpa.formation.entity.Sujet;
import fr.afpa.formation.entity.SujetContent;
import fr.afpa.formation.repository.CommentaireRepository;
import fr.afpa.formation.repository.SujetContentRepository;
import fr.afpa.formation.repository.SujetRepository;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	SujetRepository sujetRepository;
	
	@Autowired
	SujetContentRepository sujetContentRepository;
	
	@Autowired
	CommentaireRepository commentaireRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


//	@Override
	public void run(String... args) throws Exception {
//				init();
	}


	private void init() {
		Commentaire commentaire1 = new Commentaire(null, "Je suis le Com 1");
		Commentaire commentaire2 = new Commentaire(null, "Je suis le Com 2");
		Commentaire commentaire3 = new Commentaire(null, "Je suis le Com 3");
		Commentaire commentaire4 = new Commentaire(null, "Je suis le Com 4");

		commentaireRepository.save(commentaire1);
		commentaireRepository.save(commentaire2);
		commentaireRepository.save(commentaire3);
		commentaireRepository.save(commentaire4);

		SujetContent sujetContent1 = new SujetContent(null, commentaire1);
		SujetContent sujetContent2 = new SujetContent(null, commentaire2);
		SujetContent sujetContent3 = new SujetContent(null, commentaire3);
		SujetContent sujetContent4 = new SujetContent(null, commentaire4);

		sujetContentRepository.save(sujetContent1);
		sujetContentRepository.save(sujetContent2);
		sujetContentRepository.save(sujetContent3);
		sujetContentRepository.save(sujetContent4);
		
		List<SujetContent> sujetContentsSujet1 = new ArrayList<SujetContent>();
		sujetContentsSujet1.add(sujetContent1);
		sujetContentsSujet1.add(sujetContent2);
		
		List<SujetContent> sujetContentsSujet2 = new ArrayList<SujetContent>();
		sujetContentsSujet2.add(sujetContent3);
		
		List<SujetContent> sujetContentsSujet3 = new ArrayList<SujetContent>();
		sujetContentsSujet3.add(sujetContent4);

		Sujet sujet1 = new Sujet(null, "Sujet 1", sujetContentsSujet1);
		Sujet sujet2 = new Sujet(null, "Sujet 2", sujetContentsSujet2);
		Sujet sujet3 = new Sujet(null, "Sujet 3", sujetContentsSujet3);

		sujetRepository.save(sujet1);
		sujetRepository.save(sujet2);
		sujetRepository.save(sujet3);

//		commentaire1.setSujetContent(sujetContent1);
//		commentaire2.setSujetContent(sujetContent2);		
//		commentaire3.setSujetContent(sujetContent3);		
//		commentaire4.setSujetContent(sujetContent4);
//		
//		commentaireRepository.save(commentaire1);
//		commentaireRepository.save(commentaire2);
//		commentaireRepository.save(commentaire3);
//		commentaireRepository.save(commentaire4);

		sujetContent1.setSujet(sujet1);
		sujetContent2.setSujet(sujet1);

		sujetContent3.setSujet(sujet2);

		sujetContent4.setSujet(sujet3);
		
		sujetContentRepository.save(sujetContent1);
		sujetContentRepository.save(sujetContent2);
		sujetContentRepository.save(sujetContent3);
		sujetContentRepository.save(sujetContent4);
		
		
	}
}
