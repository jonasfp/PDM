package br.edu.ifpe.recife.tads.cheguei_102.model;

public class Usuario {

    private String uid;
    private String nomeUsuario;
    private String email;
    private String perfil;

    public Usuario() {
    }

    public Usuario(String uid, String nomeUsuario, String email, String perfil) {
        this.uid = uid;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.perfil = perfil;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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
