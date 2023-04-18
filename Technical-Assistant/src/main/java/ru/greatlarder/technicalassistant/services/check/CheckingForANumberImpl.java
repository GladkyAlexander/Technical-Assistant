package ru.greatlarder.technicalassistant.services.check;

public class CheckingForANumberImpl implements CheckForANumber{
    @Override
    public boolean checkingForANumber(String value) {
        return value.chars().allMatch(Character::isDigit);
    }
}
