package com.example.demo.beans;

public class image {
private int Image_Id;
private String Image_Name;
private Object Image;

    public Object getImage() {
        return Image;
    }

    public int getImage_Id() {
        return Image_Id;
    }

    public String getImage_Name() {
        return Image_Name;
    }

    public void setImage(Object image) {
        Image = image;
    }

    public void setImage_Id(int image_Id) {
        Image_Id = image_Id;
    }

    public void setImage_Name(String image_Name) {
        Image_Name = image_Name;
    }

    @Override
    public String toString() {
        return "Image [Image_id:"+Image_Id+",Image_Name:"+Image_Name+",Image]";
    }
}
