package ru.greatlarder.technicalassistant.services.check;

public interface CheckString {
    boolean checkingStringWithACondition(String value);
    boolean checkingForANumber(String value);
    boolean checkingForPhone(String value);
    boolean checkingMail(String value);
}
