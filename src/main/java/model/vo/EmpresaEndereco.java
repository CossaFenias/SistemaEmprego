package model.vo;

public class EmpresaEndereco {
    private int idEmpresa;
    private int idEndereco;

    public EmpresaEndereco(int idEmpresa, int idEndereco) {
        this.idEmpresa = idEmpresa;
        this.idEndereco = idEndereco;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}