package model.vo;

public class EmpresaServico {
    private int idEmpresa;
    private int idServico;

    public EmpresaServico() {
    }

    public EmpresaServico(int idEmpresa, int idServico) {
        this.idEmpresa = idEmpresa;
        this.idServico = idServico;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
}