package model.vo;

public class FormacaoAcademica {
    private int idFormacao;
    private String curso;
    private String instituicao;
    private String nivel;
    private int anoFormacao;
    private int idCandidato;

    public FormacaoAcademica(int idFormacao, String curso, String instituicao, String nivel, int anoFormacao, int idCandidato) {
        this.idFormacao = idFormacao;
        this.curso = curso;
        this.instituicao = instituicao;
        this.nivel = nivel;
        this.anoFormacao = anoFormacao;
        this.idCandidato = idCandidato;
    }

    public int getIdFormacao() {
        return idFormacao;
    }

    public void setIdFormacao(int idFormacao) {
        this.idFormacao = idFormacao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getAnoFormacao() {
        return anoFormacao;
    }

    public void setAnoFormacao(int anoFormacao) {
        this.anoFormacao = anoFormacao;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}