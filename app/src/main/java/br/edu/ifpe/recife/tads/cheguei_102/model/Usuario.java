package br.edu.ifpe.recife.tads.cheguei_102.model;

public class Usuario {

    private String uid;
    private String apartamento;
    private String email;
    private String perfil;

    public Usuario() {
    }

    public Usuario(String uid, String apartamento, String email, String perfil) {

        this.uid = uid;
        this.apartamento = apartamento;
        this.email = email;
        this.perfil = perfil;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
