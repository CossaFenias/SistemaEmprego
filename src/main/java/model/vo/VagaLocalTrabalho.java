package model.vo;

public class VagaLocalTrabalho {
    private int idVaga;
    private int idEndereco;

    public VagaLocalTrabalho() {
    }

    public VagaLocalTrabalho(int idVaga, int idEndereco) {
        this.idVaga = idVaga;
        this.idEndereco = idEndereco;
    }

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}