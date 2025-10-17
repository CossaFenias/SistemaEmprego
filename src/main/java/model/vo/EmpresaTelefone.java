package model.vo;

public class EmpresaTelefone {
    private int idEmpresa;
    private String telefone;

    public EmpresaTelefone() {
    }

    public EmpresaTelefone(int idEmpresa, String telefone) {
        this.idEmpresa = idEmpresa;
        this.telefone = telefone;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}