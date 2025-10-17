package model.vo;

public class CandidatoTelefone {
    private int idCandidato;
    private String telefone;

    public CandidatoTelefone() {
    }

    public CandidatoTelefone(int idCandidato, String telefone) {
        this.idCandidato = idCandidato;
        this.telefone = telefone;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}