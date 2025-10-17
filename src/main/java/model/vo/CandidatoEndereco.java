package model.vo;

public class CandidatoEndereco {
    private int idCandidato;
    private int idEndereco;

    public CandidatoEndereco() {
    }

    public CandidatoEndereco(int idCandidato, int idEndereco) {
        this.idCandidato = idCandidato;
        this.idEndereco = idEndereco;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}