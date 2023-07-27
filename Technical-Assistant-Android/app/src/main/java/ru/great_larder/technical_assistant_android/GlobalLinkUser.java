package ru.great_larder.technical_assistant_android;

import ru.great_larder.technical_assistant_android.domain.user.User;
import ru.great_larder.technical_assistant_android.services.HandlerUserListener;

public class GlobalLinkUser {
    public static User user;
    public static HandlerUserListener handlerUserListener;
    
    public static HandlerUserListener getHandlerUserListener() {
        return handlerUserListener;
    }
    
    public static void setHandlerUserListener(HandlerUserListener handlerUserListener) {
        GlobalLinkUser.handlerUserListener = handlerUserListener;
    }
    
    GlobalLinkUser(){
    
    }
    
    public static User getUser(){
        return user;
    }
    public static void setUser(User user){
        GlobalLinkUser.user = user;
    }
}
