package com.banque.Banque_Digitale_Api.dto;

import java.util.List;

public class HistoriqueCompteDto {
    private String compteId;
    private Double solde;
    private int pageCourante;
    private int totalPage;
    private int taile;
    private List<OperationDto> operationDtos;

    public String getCompteId() {
        return compteId;
    }

    public void setCompteId(String compteId) {
        this.compteId = compteId;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public int getPageCourante() {
        return pageCourante;
    }

    public void setPageCourante(int pageCourante) {
        this.pageCourante = pageCourante;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTaile() {
        return taile;
    }

    public void setTaile(int taile) {
        this.taile = taile;
    }

    public List<OperationDto> getOperationDtos() {
        return operationDtos;
    }

    public void setOperationDtos(List<OperationDto> operationDtos) {
        this.operationDtos = operationDtos;
    }
}
