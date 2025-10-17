package model.vo;

import java.time.LocalDate;

public class Vaga {
    private int idVaga;
    private String titulo;
    private String estado;
    private String requisitos;
    private LocalDate dataPublicacao;
    private String descricao;
    private String actividades;
    private String regime;
    private LocalDate dataEncerramento;
    private int idEmpresa;

    public Vaga(int idVaga, String titulo, String estado, String requisitos, LocalDate dataPublicacao, String descricao, String actividades, String regime, LocalDate dataEncerramento, int idEmpresa) {
        this.idVaga = idVaga;
        this.titulo = titulo;
        this.estado = estado;
        this.requisitos = requisitos;
        this.dataPublicacao = dataPublicacao;
        this.descricao = descricao;
        this.actividades = actividades;
        this.regime = regime;
        this.dataEncerramento = dataEncerramento;
        this.idEmpresa = idEmpresa;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getDataPublicacao() {
        return dataPublicacao + "";
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getDataEncerramento() {
        return dataEncerramento + "";
    }

    public void setDataEncerramento(LocalDate dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}