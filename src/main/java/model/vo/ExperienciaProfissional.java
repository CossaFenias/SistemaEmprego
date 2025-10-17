package model.vo;

import java.time.LocalDate;

public class ExperienciaProfissional {
    private int idExperiencia;
    private String cargo;
    private String instituicaoEmpresa;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private int idCandidato;

    public ExperienciaProfissional(int idExperiencia, String cargo, String instituicaoEmpresa, LocalDate dataInicio, LocalDate dataFim, String descricao, int idCandidato) {
        this.idExperiencia = idExperiencia;
        this.cargo = cargo;
        this.instituicaoEmpresa = instituicaoEmpresa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.idCandidato = idCandidato;
    }

    public int getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(int idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getInstituicaoEmpresa() {
        return instituicaoEmpresa;
    }

    public void setInstituicaoEmpresa(String instituicaoEmpresa) {
        this.instituicaoEmpresa = instituicaoEmpresa;
    }

    public String getDataInicio() {
        return dataInicio + "";
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim + "";
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}