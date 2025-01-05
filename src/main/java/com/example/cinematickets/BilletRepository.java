package com.example.cinematickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BilletRepository {
    @Autowired
    private JdbcTemplate db;
    public void lagreBillet(Billet billet){
        String sql = "INSERT INTO Billet(Film, Antall, Fornavn, Etternavn, Telefon, Epost) VALUES(?,?,?,?,?,?)";
        db.update(sql, billet.getFilm(), billet.getAntall(), billet.getFornavn(), billet.getEtternavn(), billet.getTelefon(), billet.getEpost());
    }
    public List<Billet>hentAlleBilletter(){
        String sql = "SELECT*FROM Billet";
        List<Billet>hentAlle = db.query(sql, new  BeanPropertyRowMapper<>(Billet.class));
        return hentAlle;
    }
    public void slettAlleBilletter(){
        String sql = "Delete From Billet";
        db.update(sql);
    }
}
