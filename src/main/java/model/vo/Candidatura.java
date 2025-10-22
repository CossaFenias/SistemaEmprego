package model.vo;

import java.time.LocalDate;

public class Candidatura {
    private int idCandidatura;
    private int idCandidato;
    private int idVaga;
    private LocalDate dataCandidatura;
    private String status;

    public Candidatura() {}

    public Candidatura(int idCandidatura, int idCandidato, int idVaga, LocalDate dataCandidatura, String status) {
        this.idCandidatura = idCandidatura;
        this.idCandidato = idCandidato;
        this.idVaga = idVaga;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDate dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}