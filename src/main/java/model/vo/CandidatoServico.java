package model.vo;

public class CandidatoServico {
    private int idCandidato;
    private int idServico;

    public CandidatoServico() {
    }

    public CandidatoServico(int idCandidato, int idServico) {
        this.idCandidato = idCandidato;
        this.idServico = idServico;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
}