package ru.greatlarder.technicalassistant.services.check;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckingStringImpl implements CheckString {
    @Override
    public boolean checkingStringWithACondition(String value) {
        return value.matches("^\\w+$");
    }
    
    @Override
    public boolean checkingForANumber(String value) {
        return value.chars().allMatch(Character::isDigit);
    }
    
    @Override
    public boolean checkingForPhone(String phone) {
        String regex = "^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$";
        return phone != null && phone.matches(regex);
    }
    
    @Override
    public boolean checkingMail(String value) {
        
        Pattern pattern = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
        
    }
}
