package model.vo;

import java.time.LocalDate;

public class Identificacao {
    private int idCandidato;
    private String numeroBI;
    private LocalDate dataEmissaoBI;
    private String nuit;

    public Identificacao(int idCandidato, String numeroBI, LocalDate dataEmissaoBI, String nuit) {
        this.idCandidato = idCandidato;
        this.numeroBI = numeroBI;
        this.dataEmissaoBI = dataEmissaoBI;
        this.nuit = nuit;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNumeroBI() {
        return numeroBI;
    }

    public void setNumeroBI(String numeroBI) {
        this.numeroBI = numeroBI;
    }

    public String getDataEmissaoBI() {
        return dataEmissaoBI + "";
    }

    public void setDataEmissaoBI(LocalDate dataEmissaoBI) {
        this.dataEmissaoBI = dataEmissaoBI;
    }

    public String getNuit() {
        return nuit;
    }

    public void setNuit(String nuit) {
        this.nuit = nuit;
    }
}