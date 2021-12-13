package com.example.rentcars;

public class Cars {
    private String markCar;
    private String modelCar;
    private String numCar;
    private String colorCar;
    private String costCar;
    private String Image;

    public Cars() {
    }

    public Cars(String markCar, String modelCar, String numCar, String colorCar, String costCar
            ,String iamge) {
        this.markCar = markCar;
        this.modelCar = modelCar;
        this.numCar = numCar;
        this.colorCar = colorCar;
        this.costCar = costCar;
       Image = iamge;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMarkCar() {
        return markCar;
    }

    public void setMarkCar(String markCar) {
        this.markCar = markCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getNumCar() {
        return numCar;
    }

    public void setNumCar(String numCar) {
        this.numCar = numCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public String getCostCar() {
        return costCar;
    }

    public void setCostCar(String costCar) {
        this.costCar = costCar;
    }
}

