package ru.great_larder.technical_assistant_android.domain;

public class Servers {
    String nameServer;
    String serverPOP;
    String serverIMAP;
    String serverSMTP;
    Integer portSSLPOP;
    Integer portSSLIMAP;
    Integer portSMTP;
    
    public Servers() {
    }
    
    public Servers(String nameServer, String serverPOP, String serverIMAP, String serverSMTP, Integer portSSLPOP, Integer portSSLIMAP, Integer portSMTP) {
        this.nameServer = nameServer;
        this.serverPOP = serverPOP;
        this.serverIMAP = serverIMAP;
        this.serverSMTP = serverSMTP;
        this.portSSLPOP = portSSLPOP;
        this.portSSLIMAP = portSSLIMAP;
        this.portSMTP = portSMTP;
    }
    
    public String getNameServer() {
        return nameServer;
    }
    
    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }
    
    public String getServerPOP() {
        return serverPOP;
    }
    
    public void setServerPOP(String serverPOP) {
        this.serverPOP = serverPOP;
    }
    
    public String getServerIMAP() {
        return serverIMAP;
    }
    
    public void setServerIMAP(String serverIMAP) {
        this.serverIMAP = serverIMAP;
    }
    
    public String getServerSMTP() {
        return serverSMTP;
    }
    
    public void setServerSMTP(String serverSMTP) {
        this.serverSMTP = serverSMTP;
    }
    
    public Integer getPortSSLPOP() {
        return portSSLPOP;
    }
    
    public void setPortSSLPOP(Integer portSSLPOP) {
        this.portSSLPOP = portSSLPOP;
    }
    
    public Integer getPortSSLIMAP() {
        return portSSLIMAP;
    }
    
    public void setPortSSLIMAP(Integer portSSLIMAP) {
        this.portSSLIMAP = portSSLIMAP;
    }
    
    public Integer getPortSMTP() {
        return portSMTP;
    }
    
    public void setPortSMTP(Integer portSMTP) {
        this.portSMTP = portSMTP;
    }
    
    @Override
    public String toString() {
        return "Servers{" +
            "nameServer='" + nameServer + '\'' +
            ", serverPOP='" + serverPOP + '\'' +
            ", serverIMAP='" + serverIMAP + '\'' +
            ", serverSMTP='" + serverSMTP + '\'' +
            ", portSSLPOP=" + portSSLPOP +
            ", portSSLIMAP=" + portSSLIMAP +
            ", portSMTP=" + portSMTP +
            '}';
    }
}
