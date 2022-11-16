package com.esprit.examen.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.TpAchatProjectApplication;
import com.esprit.examen.entities.Facture;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = TpAchatProjectApplication.class)
class FactureServiceImplTest {

	@Autowired
    private IFactureService FactureService;

	@Test 
	@Order(1)
    public void testAddFacture() {
        Facture factureAdded = FactureService.addFacture(Facture.builder().idFacture(1L).montantRemise(2f).montantFacture(10f).archivee(false).build());
        Assertions.assertEquals(10f, factureAdded.getMontantFacture());
        Assertions.assertEquals(2f, factureAdded.getMontantRemise());
        Assertions.assertNotNull(factureAdded);
    }

    @Test
    @Order(2)
    public void testGetFacture() {
    	Facture factureAdded = FactureService.addFacture(Facture.builder().idFacture(1L).montantRemise(2f).montantFacture(10f).archivee(false).build());
        Assertions.assertNotNull(factureAdded.getIdFacture());
        
        Facture factureRetrieved = FactureService.retrieveFacture(factureAdded.getIdFacture());
        Assertions.assertNotNull(factureAdded.getIdFacture());
        Assertions.assertEquals(10f, factureRetrieved.getMontantFacture());
        Assertions.assertEquals(2f, factureRetrieved.getMontantRemise());
        
    }


    @Test
    @Order(3)
    public void testCancelFacture() {
    	Facture factureAdded = FactureService.addFacture(Facture.builder().idFacture(1L).montantRemise(2f).montantFacture(10f).archivee(false).build());
        Assertions.assertNotNull(factureAdded.getIdFacture());
        
        
        Facture factureRetrieved = FactureService.retrieveFacture(factureAdded.getIdFacture());
        FactureService.cancelFacture(factureRetrieved.getIdFacture());
        Assertions.assertTrue(factureRetrieved.getArchivee());
    }
}