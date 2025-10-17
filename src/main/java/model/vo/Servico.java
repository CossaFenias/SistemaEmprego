package model.vo;

public class Servico {
    private int idServico;
    private String nomeServico;
    private String areaServico;
    private String descricao;

    public Servico(int idServico, String nomeServico, String areaServico, String descricao) {
        this.idServico = idServico;
        this.nomeServico = nomeServico;
        this.areaServico = areaServico;
        this.descricao = descricao;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getAreaServico() {
        return areaServico;
    }

    public void setAreaServico(String areaServico) {
        this.areaServico = areaServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}