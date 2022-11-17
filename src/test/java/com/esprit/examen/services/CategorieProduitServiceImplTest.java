package com.esprit.examen.services;

import static org.junit.Assert.*;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import lombok.extern.slf4j.Slf4j;

import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategorieProduitServiceImplTest {



	@Autowired
	ICategorieProduitService categorieProduitRepository;
	@Test
	public void testretrieveAllCategorieProduits()  {
		assertNotNull(categorieProduitRepository.retrieveAllCategorieProduits());

	}
	@Test
	public void testaddCategorieProduit()  {
		Set<Produit> produits = null;
		produits.add(new Produit());
		CategorieProduit cp = new CategorieProduit(1L,"testcode","labelle",produits);
		assertNotNull(categorieProduitRepository.addCategorieProduit(cp));

	}


}
