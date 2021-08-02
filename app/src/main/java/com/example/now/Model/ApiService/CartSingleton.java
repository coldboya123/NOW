package com.example.now.Model.ApiService;

import android.annotation.SuppressLint;

import com.example.now.Model.Object.Food;
import com.example.now.Model.Object.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private final List<Food> foodList;
//    private final List<UserAddress> addressList;
    private static CartSingleton instance = null;
    private UserAddress address;

    public CartSingleton(){
        foodList = new ArrayList<>();
//        addressList = new ArrayList<>();
        address = new UserAddress();
    }

    public static CartSingleton getInstance(){
        if (instance == null){
            instance = new CartSingleton();
        }
        return instance;
    }

    public List<Food> getCart(){
        return foodList;
    }

    public void pushFood(Food food){
        boolean instance = false;
        if (foodList.size() == 0){
            foodList.add(food);
        }else {
            for (int i = 0; i < foodList.size(); i++) {
                if (food.getId().equals(foodList.get(i).getId())) {
                    foodList.get(i).setNumBuy(food.getNumBuy());
                    instance = true;
                    break;
                } else {
                    instance = false;
                }
            }
            if (!instance) {
                foodList.add(food);
            }
        }
    }

    public void removeFood(Food food){
        for (int i = 0; i < foodList.size(); i++) {
            if (food.getId().equals(foodList.get(i).getId())){
                if (foodList.get(i).getNumBuy() == 0){
                    foodList.remove(i);
                    break;
                }else {
                    foodList.get(i).setNumBuy(food.getNumBuy());
                }
            }
        }
    }

    public void clearCart(){
        foodList.clear();
    }

    public int getItemCount(){
        int count = 0;
        for (int i = 0; i < foodList.size(); i++) {
            count += foodList.get(i).getNumBuy();
        }
        return count;
    }

    public int getTotal(){
        int total = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getSpecialPrice().equals("-1")){
                total += Integer.parseInt(foodList.get(i).getPrice()) * foodList.get(i).getNumBuy();
            } else {
                total += Integer.parseInt(foodList.get(i).getSpecialPrice()) * foodList.get(i).getNumBuy();
            }
        }
        return total;
    }

    @SuppressLint("DefaultLocale")
    public String getTotalFormated(){
        return String.format("%1$,.0f", (float) getTotal()) + " đ";
    }

    public String getListFoodId(){
        String list_id = "";
        for (int i = 0; i < foodList.size(); i++) {
            list_id += foodList.get(i).getId() + "/";
        }
        return list_id;
    }

    public void setSelectedAddress(UserAddress address){
        this.address = address;
    }

    public UserAddress getSelectedAddress(){
        return this.address;
    }

//    public List<UserAddress> getAddressList(){
//        return this.addressList;
//    }
//
//    public void pushAddress(UserAddress userAddress){
//        addressList.add(userAddress);
//    }

//    public void pushListAddress(List<UserAddress> addressList){
//        this.addressList.addAll(addressList);
//    }
//
//    public void removeAddress(UserAddress userAddress){
//        for (int i = 0; i < this.addressList.size(); i++) {
//            if (this.addressList.get(i).getId().equals(userAddress.getId())){
//                this.addressList.remove(i);
//                break;
//            }
//        }
//    }
//
//    public UserAddress getSelectedAddress(){
//        for (int i = 0; i < addressList.size(); i++) {
//            if (addressList.get(i).getStatus().equals("1"))
//                return addressList.get(i);
//        }
//        return null;
//    }
//
//    public void setSelectedAddress(UserAddress address){
//        for (int i = 0; i < addressList.size(); i++) {
//            if (addressList.get(i).getStatus().equals("1")){
//                if (!addressList.get(i).getId().equals(address.getId())){
//                    for (int j = 0; j < addressList.size(); j++) {
//                        if (addressList.get(j).getId().equals(address.getId())){
//                            addressList.get(j).setStatus("1");
//                            addressList.get(i).setStatus("0");
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }

//    public void clearAddressList(){
//        this.addressList.clear();
//    }
}
