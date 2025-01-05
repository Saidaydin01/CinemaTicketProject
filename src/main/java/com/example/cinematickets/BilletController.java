package com.example.cinematickets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class BilletController {

    @Autowired
    private BilletRepository rep;

    @PostMapping("/lagre")
    public ResponseEntity<String> lagreBillet(@RequestBody Billet billett) {
        // Validate inputs
        if (billett.getFilm() == null || billett.getFilm().isEmpty()) {
            return ResponseEntity.badRequest().body("Film navn må oppgis.");
        }
        if (billett.getAntall() <= 0) {
            return ResponseEntity.badRequest().body("Antall må være større enn null.");
        }
        if (billett.getFornavn() == null || billett.getFornavn().isEmpty()) {
            return ResponseEntity.badRequest().body("Fornavn må oppgis.");
        }
        if (billett.getEtternavn() == null || billett.getEtternavn().isEmpty()) {
            return ResponseEntity.badRequest().body("Etternavn må oppgis.");
        }
        if (billett.getTelefon() == null || !billett.getTelefon().matches("\\+?\\d{8,}")) {
            return ResponseEntity.badRequest().body("Ugyldig telefonnummer.");
        }
        if (billett.getEpost() == null || !billett.getEpost().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        )) {
            return ResponseEntity.badRequest().body("Ugyldig epostadresse.");
        }

        //Save if All validations pass
        try {
            rep.lagreBillet(billett);
            return ResponseEntity.ok().body("Billet lagret.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("En feil oppstod ved lagring av billetten.");
        }
    }

    @GetMapping("/hentAlle")
    public List<Billet> hentAlle(){
        return rep.hentAlleBilletter();
    }

    @GetMapping("/slettAlle")
    public ResponseEntity<String> slettAlle(){
        try {
            rep.slettAlleBilletter();
            return ResponseEntity.ok().body("Alle billetter er slettet.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("En feil oppstod ved sletting.");
        }

    }

}
