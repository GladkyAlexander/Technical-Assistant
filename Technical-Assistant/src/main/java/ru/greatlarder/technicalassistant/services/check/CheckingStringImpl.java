package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.services.check.CheckString;

public class CheckingStringImpl implements CheckString {
    @Override
    public boolean chekingStringWithACondition(String value) {
        return value.matches("^\\w+$");
    }
}
