package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.
                getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            try {
                String[] digits = ConsoleHelper.getValidTwoDigits(currencyCode);
                int banknotes = Integer.parseInt(digits[0]);
                int quantity = Integer.parseInt(digits[1]);
                currencyManipulator.addAmount(banknotes, quantity);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), banknotes*quantity, currencyCode));
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}
