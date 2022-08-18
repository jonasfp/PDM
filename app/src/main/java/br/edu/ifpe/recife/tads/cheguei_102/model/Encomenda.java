package br.edu.ifpe.recife.tads.cheguei_102.model;

import java.util.Date;

public class Encomenda {

    private String entregadorId;
    private String uid;
    private Date dataDeEntrega;
    private Date horaDaEntrega;
    private Date dataDeRetirada;
    private Date horaDaRetirada;
    private boolean entregue;
    private byte[] imgEncomenda;
    private String imagemUrl;
    private String apartamento;

    public Encomenda() {
    }

    public Encomenda(String uid, String entregadorId, Date dataDeEntrega, Date horaDaEntrega, String imagemUrl, String apartamento) {
        this.uid = uid;
        this.entregadorId = entregadorId;
        this.dataDeEntrega = dataDeEntrega;
        this.horaDaEntrega = horaDaEntrega;
        this.imagemUrl = imagemUrl;
        this.apartamento = apartamento;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEntregadorId() {
        return entregadorId;
    }

    public void setEntregadorId(String entregadorId) {
        this.entregadorId = entregadorId;
    }

    public Date getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(Date dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public Date getHoraDaEntrega() {
        return horaDaEntrega;
    }

    public void setHoraDaEntrega(Date horaDaEntrega) {
        this.horaDaEntrega = horaDaEntrega;
    }

    public Date getDataDeRetirada() {
        return dataDeRetirada;
    }

    public void setDataDeRetirada(Date dataDeRetirada) {
        this.dataDeRetirada = dataDeRetirada;
    }

    public Date getHoraDaRetirada() {
        return horaDaRetirada;
    }

    public void setHoraDaRetirada(Date horaDaRetirada) {
        this.horaDaRetirada = horaDaRetirada;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public byte[] getImgEncomenda() {
        return imgEncomenda;
    }

    public void setImgEncomenda(byte[] imgEncomenda) {
        this.imgEncomenda = imgEncomenda;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }
}
