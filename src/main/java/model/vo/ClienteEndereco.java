package model.vo;

public class ClienteEndereco {
    private int idCliente;
    private int idEndereco;

    public ClienteEndereco() {
    }

    public ClienteEndereco(int idCliente, int idEndereco) {
        this.idCliente = idCliente;
        this.idEndereco = idEndereco;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
}