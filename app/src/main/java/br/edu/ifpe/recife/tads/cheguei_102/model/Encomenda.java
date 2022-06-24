package br.edu.ifpe.recife.tads.cheguei_102.model;

public class Encomenda {
    private String uid;
    private String dataDeEntrega;
    private String horaDaEntrega;
    private String dataDeRetirada;
    private String horaDaRetirada;
    private boolean entregue;
    private byte[] imgEncomenda;
    private String profileUrl;
    private String apartamento;

    public Encomenda() {
    }

    public Encomenda(String uid, String dataDeEntrega, String horaDaEntrega, String profileUrl, String apartamento) {
        this.uid = uid;
        this.dataDeEntrega = dataDeEntrega;
        this.horaDaEntrega = horaDaEntrega;
        this.profileUrl = profileUrl;
        this.apartamento = apartamento;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(String dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public String getHoraDaEntrega() {
        return horaDaEntrega;
    }

    public void setHoraDaEntrega(String horaDaEntrega) {
        this.horaDaEntrega = horaDaEntrega;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }
}
