package model.vo;

public class ClienteServico {
    private int idCliente;
    private int idServico;

    public ClienteServico() {
    }

    public ClienteServico(int idCliente, int idServico) {
        this.idCliente = idCliente;
        this.idServico = idServico;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
}