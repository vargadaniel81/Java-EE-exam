/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbeans;

import comparators.ContactNameComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Contact;
import pojos.Phone;

/**
 *
 * @author Daniel Varga - vargadaniel81@gmail.com
 */
@ManagedBean
@SessionScoped
public class Listazo {

    private List<Contact> kontaktok;
    private List<Phone> telefonszamok;
    private Contact kivalasztottKontakt;
    private Phone kivalasztottTelefonszamok;
    private List<String> tipusok;
    private String telefonszamTipusa;
    private String telefonszam;
    private String kereses;

    public Listazo() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        kontaktok = session.createQuery("FROM Contact").list();
        session.close();
        Collections.sort(kontaktok, new ContactNameComparator());

        tipusok = new ArrayList<>();
        tipusok.add("home");
        tipusok.add("homeoffice");
        tipusok.add("mobil");
    }

    public void kontaktKivalaszt(Contact con) {
        kivalasztottKontakt = con;
        telefonszamok = new ArrayList<>(con.getPhones());

    }

    public String ujKontakt() {
        kivalasztottKontakt = new Contact();

        return "kontakt";
    }

    public String kontaktSzerkeszt(Contact con) {
        kivalasztottKontakt = con;

        return "kontakt";
    }

    public String kontaktMent() {
        boolean uj = kivalasztottKontakt.getId() == null;

        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(kivalasztottKontakt);
        session.getTransaction().commit();
        session.close();

        if (uj) {
            kontaktok.add(kivalasztottKontakt);
        }

        return "index";
    }

    public String telefonUj() {
        kivalasztottTelefonszamok = new Phone(kivalasztottKontakt, telefonszam, telefonszamTipusa);

        return "telefon";
    }

    public String telefonSzerkeszt(Phone tel) {
        kivalasztottTelefonszamok = tel;

        return "telefon";
    }

    public String telefonMent() {
        boolean uj = kivalasztottTelefonszamok.getId() == null;

        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(kivalasztottTelefonszamok);
        session.getTransaction().commit();
        session.close();

        if (uj) {
            telefonszamok.add(kivalasztottTelefonszamok);
        }
        return "index";
    }

    public void telefonTorol(Phone tel) {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(tel);
        session.getTransaction().commit();
        session.close();

        telefonszamok.remove(tel);
    }

    public String keresesNev() {
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        Query nev = session.createQuery("FROM Contact");

        if (kereses.equals("")) {

        } else {

        }
        session.close();

        return "index";
    }

    public List<Contact> getKontaktok() {
        return kontaktok;
    }

    public void setKontaktok(List<Contact> kontaktok) {
        this.kontaktok = kontaktok;
    }

    public List<Phone> getTelefonszamok() {
        return telefonszamok;
    }

    public void setTelefonszamok(List<Phone> telefonszamok) {
        this.telefonszamok = telefonszamok;
    }

    public Contact getKivalasztottKontakt() {
        return kivalasztottKontakt;
    }

    public void setKivalasztottKontakt(Contact kivalasztottKontakt) {
        this.kivalasztottKontakt = kivalasztottKontakt;
    }

    public Phone getKivalasztottTelefonszamok() {
        return kivalasztottTelefonszamok;
    }

    public void setKivalasztottTelefonszamok(Phone kivalasztottTelefonszamok) {
        this.kivalasztottTelefonszamok = kivalasztottTelefonszamok;
    }

    public String getTelefonszamTipusa() {
        return telefonszamTipusa;
    }

    public void setTelefonszamTipusa(String telefonszamTipusa) {
        this.telefonszamTipusa = telefonszamTipusa;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getKereses() {
        return kereses;
    }

    public void setKereses(String kereses) {
        this.kereses = kereses;
    }

    public List<String> getTipusok() {
        return tipusok;
    }

    public void setTipusok(List<String> tipusok) {
        this.tipusok = tipusok;
    }

}
