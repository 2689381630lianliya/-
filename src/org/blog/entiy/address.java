package org.blog.entiy;

import org.junit.Test;

import java.io.Serializable;
import java.util.Random;

public class address implements Serializable {

    private  Integer id;
    private String adres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Override
    public String toString() {
        return "address{" +
                "id=" + id +
                ", adres='" + adres + '\'' +
                '}';
    }
@Test
    public void te (){
    Random r = new Random();
    for (int i=0;i<5;i++){
        int i1 = r.nextInt(35)+1;
        System.out.print(i1+" ");
    }
    for (int ii=0;ii<2;ii++){
        int i = r.nextInt(12)+1;
        System.out.print(i);
    }
    }

}
