package com.example.now.Model.ApiService;

import com.example.now.Model.Object.Food;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartSingleton {
    private List<Food> list;
    private static CartSingleton instance = null;

    public CartSingleton(){
        list = new ArrayList<>();
    }

    public static CartSingleton getInstance(){
        if (instance == null){
            instance = new CartSingleton();
        }
        return instance;
    }

    public List<Food> getCart(){
        return list;
    }

    public void pushFood(Food food){
        boolean instance = false;
        if (list.size() == 0){
            list.add(food);
        }else {
            for (int i = 0; i < list.size(); i++) {
                if (food.getId().equals(list.get(i).getId())) {
                    list.get(i).setNumBuy(food.getNumBuy());
                    instance = true;
                    break;
                } else {
                    instance = false;
                }
            }
            if (!instance) {
                list.add(food);
            }
        }
    }

    public void removeFood(Food food){
        for (int i = 0; i < list.size(); i++) {
            if (food.getId().equals(list.get(i).getId())){
                if (list.get(i).getNumBuy() == 0){
                    list.remove(i);
                }else {
                    list.get(i).setNumBuy(food.getNumBuy());
                }
            }
        }
    }

    public void clearCart(){
        list.clear();
    }

    public int getItemCount(){
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            count += list.get(i).getNumBuy();
        }
        return count;
    }

    public int getTotal(){
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSpecialPrice().equals("-1")){
                total += Integer.parseInt(list.get(i).getPrice()) * list.get(i).getNumBuy();
            } else {
                total += Integer.parseInt(list.get(i).getSpecialPrice()) * list.get(i).getNumBuy();
            }
        }
        return total;
    }

    public String getTotalFormated(){
        return String.format("%1$,.0f", (float) getTotal()) + " đ";
    }

    public String getListFoodId(){
        String list_id = "";
        for (int i = 0; i < list.size(); i++) {
            list_id += list.get(i).getId() + "/";
        }
        return list_id;
    }
}
