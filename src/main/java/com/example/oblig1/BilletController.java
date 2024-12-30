package com.example.oblig1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BilletController {

    @Autowired
    private BilletRepository rep;

    @PostMapping("/lagre")
    public void lagreBillet(Billet billett){
        rep.lagreBillet(billett);}

    @GetMapping("/hentAlle")
    public List<Billet> hentAlle(){
        return rep.hentAlleBilletter();
    }

    @GetMapping("/slettAlle")
    public void slettAlle(){
        rep.slettAlleBilletter();
    }

}
